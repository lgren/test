package base;

import cn.hutool.core.util.ReUtil;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 * @author lgren
 * @since 2020-12-03 3:23 下午
 */
public class ReTest {
    @Test
    public void base1() {
        // 被测试内容
        String str = "CSDN是中国一个网站\n" +
                "CSDN是个垃圾网站\n" +
                "CSDN是个超级无聊的网站\n" +
                "CSDN是个技术网站\n" +
                "CSDN是程序员的大本营";

        // 正则表达式
        String reg = "(?!.*?(垃圾|无聊))(?=.*?CSDN+)(?=.*?网站+).+";

        //修改上方正则表达式和测试内容点击运行就可以在线进行测试， 也可想根据需要修改下方代码

        Matcher m1 = Pattern.compile(reg).matcher(str);
        String lines = "";
        int count = 0;
        while (m1.find()) {
            lines += m1.group() + "\n";
            count++;
        }
        if (count > 0) {
            System.out.println("找到" + count + "个相匹配结果：");
            System.out.println(lines);
        } else {
            System.out.println("未找到相匹配结果");
        }
    }
    @Test
    public void base2() {
        // 被测试内容
        String str = "CSDN是中国一个网站\n" +
                "CSDN是个垃圾网站\n" +
                "CSDN是个超级无聊的网站\n" +
                "CSDN是个技术网站\n" +
                "CSDN是程序员的大本营";

        // 正则表达式
        String reg = "(?!.*?(垃圾|无聊))(?=.*?CSDN+)(?=.*?网站+).+";

        //修改上方正则表达式和测试内容点击运行就可以在线进行测试， 也可想根据需要修改下方代码

        Matcher m1 = Pattern.compile(reg, Pattern.DOTALL).matcher(str);
        String lines = "";
        int count = 0;
        while (m1.find()) {
            lines += m1.group() + "\n";
            count++;
        }
        if (count > 0) {
            System.out.println("找到" + count + "个相匹配结果：");
            System.out.println(lines);
        } else {
            System.out.println("未找到相匹配结果");
        }
    }

    @Test
    public void hutool() {
        List<String> list = ReUtil.findAllGroup0(
                Pattern.compile("(?!.*?(垃圾|无聊))(?=.*?CSDN+)(?=.*?网站+).+"),
                "CSDN是中国一个网站\n" +
                        "CSDN是个垃圾网站\n" +
                        "CSDN是个超级无聊的网站\n" +
                        "CSDN是个技术网站\n" +
                        "CSDN是程序员的大本营");
        System.out.println(list);
    }
}
