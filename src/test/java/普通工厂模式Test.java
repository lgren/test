import com.google.common.base.Optional;
import com.lgren.简单工厂模式.a_普通.SendFactory;
import com.lgren.简单工厂模式.a_普通.Sender;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class 普通工厂模式Test {

    @Test
    public void test1() {
        SendFactory sendFactory = new SendFactory();
        Optional<Sender> sender = (Optional<Sender>) sendFactory.product("");
    }

    @Test
    public void test2() {
        ArrayList al = new ArrayList();
        al.add(0, 8);
        al.add(1, 10);
        al.add(2, 3);
        al.add(3, 20);
        al.add(4, 30);
        al.add(2, 70); //在第三个元素的位置加入一个元素
        for (int i = 0; i < al.size(); i++) {
            System.out.print(al.get(i) + " ");
        }

    }

    @Test
    public void test3() {
        Map<String, Object> map = new HashMap<>();
        map.put("one", "这是one");
        map.put("two", "这是two");
        map.put("tree", "这是tree");
        map.put("four", "这是four");
        map.forEach((k, v) -> System.out.println(k + "->" + v));
        System.out.println(map.get("five"));

    }

    @Test
    public void timeTest() {
        Date date = new Date();
//        date = DateUtils.setYears(date,2018);
//        date = DateUtils.setMonths(date,6);
//        date = DateUtils.setDays(date,21);
//        date = DateUtils.setHours(date,10);
//        date = DateUtils.setMinutes(date,31);
        date = DateUtils.addMinutes(date,-30);
//        date = DateUtils.addSeconds(date,-1);
        System.out.println(getInterval(date,"跟进"));
        System.out.println(DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }

    public static String getInterval(Date date, String type) {
        if (date == null) {
            return "无";
        }
        // 定义最终返回的结果字符串。
        String interval = "";
        type = StringUtils.isEmpty(type) ? "" : type;
        long millisecond = new Date().getTime() - date.getTime();

        long second = millisecond / 1000;

        if (second <= 0) {
            second = 0;
        }
        if (second >= 0 && second <= 5 * 60) {//大于0分钟 小于5分钟
            interval = "刚刚";
        } else if (second > 5 * 60 && second <= 30 * 60) {//大于5分钟 小于30分钟
            long minute = second / 60;
            interval = minute + "分钟前";
        }else if (second > 30 * 60 && second <= 60 * 60) {//大于30分钟 小于1小时
            interval = 30 + "分钟前";
        } else if (second > 60 * 60 && second <= 60 * 60 * 24) {//大于1小时 小于24小时
            long hour = (second / 60) / 60;
            interval = hour + "小时前";
        } else if (second > 60 * 60 * 24 && second <= 60 * 60 * 24 * 2) {//大于1天 小于2天
            interval = "昨天";
        } else if (second > 60 * 60 * 24 * 2 && second <= 60 * 60 * 24 * 3) {//大于2天 小于 3天
            interval = "前天";
        } else if (second > 60 * 60 * 24 * 3 && second <= 60 * 60 * 24 * 7) {//大于3天 小于 7天
            long day = (second / 60) / 60 / 24;
            interval = day + "天前";
        } else if (second > 60 * 60 * 24 * 7 && second <= 60 * 60 * 24 * 15) {//大于7天 小于 15天
            interval = "一周前";
        } else if (second > 60 * 60 * 24 * 15 && second <= 60 * 60 * 24 * 30) {//大于15天 小于 30天
            interval = "半个月前";
        } else if (second > 60 * 60 * 24 * 30 && second <= 60 * 60 * 24 * 183) {//大于30天 小于 6个月
            long month = (second / 60) / 60 / 24 / 30;
            interval = month + "个月前";
        } else {
            interval = "近半年未";
        }
        return interval + type;
    }

    public static String getInterval(String dateStr, String type) {
        Date date = null;
        try {
            date = DateUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getInterval(date, type);
    }
}
