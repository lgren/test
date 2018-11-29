package test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 * @author Lgren
 * @create 2018-11-13 14:13
 **/
public class 正则表达式测试 {
    @Test
    public void test1() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));// This order was placed for QT3000! OK?
            System.out.println("Found value: " + m.group(1));// This order was placed for QT
            System.out.println("Found value: " + m.group(2));// 3000
            System.out.println("Found value: " + m.group(3));// ! OK?
        } else {
            System.out.println("NO MATCH");
        }
    }
}
