package life.gp;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Objects;
import lombok.SneakyThrows;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static life.gp.DataSourceEnum.*;

public class CommonTest {
    @Test
    public void core() {
        DataSourceEnum dataSource = XueQiu;
        List<IBaseItem> baseList = dataSource.getList("sha,sza");
        List<IBaseItem> tempList = baseList;
        // 涨幅 3-5%
        List<IBaseItem> filter_percent = filter(tempList, o -> o.getPercent() >= 3 && o.getPercent() <= 5);
        tempList = filter_percent;

        // 换手率 5-10%
        List<IBaseItem> filter_turnoverRatio = filter(tempList, o -> o.getTurnoverRatio() >= 5 && o.getTurnoverRatio() <= 10);
        tempList = filter_turnoverRatio;

        // 流通市值50-200E
        List<IBaseItem> filter_nmcW = filter(tempList, (o -> o.getNmcW() >= 50_0000 && o.getNmcW() <= 200_0000));
        tempList = filter_nmcW;

        // 量比小于1的全部剔除 此步骤放最后, 因为部分网站获取的数据没有量比, 需要再处理
        if (Objects.equal(dataSource, XinLang)) {
            tempList.forEach(o -> o.setVolumeRatio(getVolumeRatio(o.getSymbol())));
        }
        List<IBaseItem> filter_volumeRatio = filter(tempList, (o -> o.getVolumeRatio() != null && o.getVolumeRatio() >= 1));
        tempList = filter_volumeRatio;

        tempList.forEach(o -> System.out.println(o.getSymbol() + "\t" + o.getName() + "\t" + o.getCurrent()));
        generateHtml(tempList, true);
    }
    /*
    5. 成交量持续放大留下, 像台阶式更好. 成交量一高一低的, 不稳定的剔除
       分时: http://image.sinajs.cn/newchart/min/n/sh600163.gif
       日 K: http://image.sinajs.cn/newchart/daily/n/sh600163.gif
       周 K: http://image.sinajs.cn/newchart/weekly/n/sh600163.gif
       月 K: http://image.sinajs.cn/newchart/monthly/n/sh600163.gif

    6. 看个股的K线形态，短期看5/10/20日均线，搭配60日均线多头向上发散就是最好的形态. 把一些K线上方没有任何压力的留下，这样冲高也会更加轻松，做大概率的事件
       如果K线形态显示在重要的均线下方，则说明近期个股的走势是冲高回落，上方的套牢盘压力过高，处于成交密集区，这种的继续进行剔除.

    7. 分时图来判断强势股的特征，能够跑赢大盘的都是属于逆势上涨的，强者恒强的市场，只有选取强势股才能把收益做到最大，最好能搭配当下热点题材板块，这样支撑就更加有力度
       比如近期火热的锂电，小金属原材料，有色顺周期股等等
       把剩下的优质股叠加上证指数的分时图，个股价格的走势，必须是全天在分时图价格的上方，这样的表示个股的涨幅较好，市场的气氛充足，在车上的人都可以吃到一波盈利，次日的冲高会更加的有力度。
     */

    /**
     * 数据筛选简化
     */
    private <T> List<T> filter(List<T> list, Predicate<T> func) {
        return list.stream().filter(func).collect(Collectors.toList());
    }

    private void generateHtml(List<IBaseItem> list, boolean open) {
        String nowStr = DTF.format(LocalDateTime.now());
        String filePathname = MessageFormat.format("/Users/lgren/Life/股票/{0}.html", nowStr);
        String html = "<!DOCTYPE html><html lang=\"cn\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>{0}</title></head><body>{1}</body></html>";
        String item = "<div style=\"display: flex;\"><div style=\"width: 100px;\"><div>{1}</div><div>{0}</div><div>{2}</div></div><div><img src=\"http://image.sinajs.cn/newchart/min/n/{0}.gif\" alt=\"时\" style=\"width: 50%;\"><img src=\"http://image.sinajs.cn/newchart/daily/n/{0}.gif\" alt=\"日K\" style=\"width: 50%;\"></div></div>";
        String content = MessageFormat.format(html, nowStr, list.stream().map(o -> MessageFormat.format(item, o.getSymbol().toLowerCase(), o.getName(), o.getCurrent())).collect(Collectors.joining()));
        FileUtil.writeUtf8String(content, filePathname);
        System.out.println(filePathname);
        // MailUtil.send("625552409@qq.com", "这是一个测试", content, true);
        if (open) {
            open(filePathname);
        }
    }

    @SneakyThrows
    private void open(String pathname) {
        String osName = System.getProperty("os.name");
        if (osName != null) {
            if (osName.contains("Mac")) {
                Runtime.getRuntime().exec("open " + pathname);
            } else if (osName.contains("Windows")) {
                Runtime.getRuntime().exec("cmd /c start " + pathname);
            } else {
                System.out.println("文件输出目录:" + pathname);
            }
        }
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
    }


    @Test
    public void test1() {
        List<IBaseItem> listXinLang = XinLang.getList(TYPE_ALL);
        List<IBaseItem> listXueQiu = XueQiu.getList(TYPE_ALL);
        System.out.println();
    }

    @Test
    public void test2() {
        // 雪球网
        // https://xueqiu.com/hq#type=sha&exchange=CN&firstName=%E6%B2%AA%E6%B7%B1%E8%82%A1%E5%B8%82&secondName=%E6%8E%92%E8%A1%8C&market=CN&order=desc&order_by=percent&plate=%E6%B2%AAA%E6%B6%A8%E5%B9%85%E6%A6%9C
        // 区分大小写
        String code = "SZ300633";
        HttpUtil.get("https://xueqiu.com/");


        double x = getVolumeRatio(code);
        System.out.println(x);
    }

}
