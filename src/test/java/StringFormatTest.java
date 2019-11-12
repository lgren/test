import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Optional.ofNullable;

/**
 * 使用的模板测试等
 * @author Lgren
 * @create 2018-10-11 10:07
 **/
public class StringFormatTest {
    @Test
    public void StringFormat格式化() {
        // String类型
        System.out.println("-----------------------String---------------------------");
        System.out.println(String.format("这是%s,这是%s,这是%s", "一", "二", "三"));// 基础用法 ::这是一,这是二,这是三
        System.out.println(String.format("字母a的大写是：%c", 'A'));// ::字母a的大写是：A

        // 数字格式化
        System.out.println("-----------------------数字---------------------------");
        System.out.println(String.format("%,d", 99899999));// 千分位 ::99,899,999
        System.out.printf("%+(,d %n", -9999999);// 如果是负数则为(9,999,999)
        System.out.printf("%05d%n",31); //    31 与下边3个数字对其  2. %05d%n 补上0 00015 3. %-5d%n 左对齐 右边有空格
        System.out.printf("% 5d%n",185);//   185 与相邻3个数字对其  2. %05d%n 补上0 00185 3. %-5d%n 左对齐 右边有空格
        System.out.printf("% 5d%n",155);//   155 与上边3个数字对其  2. %05d%n 补上0 00155 3. %-5d%n 左对齐 右边有空格
        System.out.printf("%03d%n",1);//   155 与上边3个数字对其  2. %05d%n 补上0 00155 3. %-5d%n 左对齐 右边有空格
        System.out.printf("%5.2f%n",155.435);// 155.44 浮点型数字格式化
//        System.out.printf("%d%n",123456789);
        System.out.printf("%,.3f%n",123456789.94456);
        System.out.printf("整数:%2$d 浮点数:%1$.2f%n",155.435, 123456789);// 155.44 浮点型数字格式化 1$ 2$是索引哪一个

        // 时间格式化
        System.out.println("-----------------------时间---------------------------");
        Date nowDate = new Date();
        System.out.println(String.format("%tT", nowDate));// 时间格式化 HH:mm:ss
        System.out.println(String.format("%tR", nowDate));// 时间格式化 HH:mm
        System.out.println(String.format("%tr", nowDate));// 时间格式化 hh:mm:ss PM格式（12时制）
        System.out.println(String.format("%tF", nowDate));// 时间格式化 YYYY-MM-dd
        System.out.println(String.format("%tF %<tT", nowDate));// 时间格式化 YYYY-MM-dd HH:mm:ss %<tT "<"代表用上一个值

        System.out.println(String.format(Locale.US, "英文月份简称：%tb 全称：%<tB", new Date()));
        System.out.println(String.format(Locale.CHINA, "中文月份简称：%tb 全称：%<tB", new Date()));
        System.out.println(String.format(Locale.JAPAN, "日文月份简称：%tb 全称：%<tB", new Date()));
        System.out.println(String.format(Locale.US, "英文星期简称：%ta 全称：%<tA", new Date()));
        System.out.println(String.format(Locale.CHINA, "中文星期简称：%ta 全称：%<tA", new Date()));
        System.out.println(String.format(Locale.JAPAN, "日文星期简称：%ta 全称：%<tA", LocalDate.of(2018, 10, 7))); // 周日
        System.out.println(String.format(Locale.JAPAN, "日文星期简称：%ta 全称：%<tA", LocalDate.of(2018, 10, 8)));
        System.out.println(String.format(Locale.JAPAN, "日文星期简称：%ta 全称：%<tA", LocalDate.of(2018, 10, 9)));
        System.out.println(String.format(Locale.JAPAN, "日文星期简称：%ta 全称：%<tA", LocalDate.of(2018, 10, 10)));
        System.out.println(String.format(Locale.JAPAN, "日文星期简称：%ta 全称：%<tA", LocalDate.of(2018, 10, 11)));
        System.out.println(String.format(Locale.JAPAN, "日文星期简称：%ta 全称：%<tA", LocalDate.of(2018, 10, 12)));
        System.out.println(String.format(Locale.JAPAN, "日文星期简称：%ta 全称：%<tA", LocalDate.of(2018, 10, 13)));


    }

    @Test
    public void 长数字代码显示() {
        System.out.println(9_0000L);
    }

    @Test
    public void 时间测试() {
        LocalDateTime dt = LocalDateTime.now();
        LocalDate d = dt.toLocalDate();
        LocalTime t = dt.toLocalTime();
        System.out.printf("%tF %tT\n", d, t);// 2018-10-12 11:46:40
        System.out.println(dt + "\n" + d + "\n" + t);
        // 2018-10-12T11:46:40.390
        // 2018-10-12
        // 11:46:40.390
    }

    @Test
    public void List测试() {
        //language=JSON
        String test = "{\"one\": 1, \"two\": 2}";
//        ofNullable("String").orElseThrow();
        String value = "18273645241";
        String omitPhone = value.substring(0, 3) + "****" + value.substring(7);
        System.out.println(omitPhone);
    }

    @Test
    public void 数字转中文测试() {
        System.out.println(String.format(Locale.CHINA, "中文月份简称：%tb 全称：%<tB", new Date()));
        System.out.println(String.format(Locale.CHINA, "中文星期简称：%ta 全称：%<tA", new Date()));

        System.out.println(String.format(Locale.CHINA, "中文星期简称：%d 全称：%<d", 1));

        System.out.printf("%05d%n",31); //    15 与下边3个数字对其  2. %05d%n 补上0 00015 3. %-5d%n 左对齐 右边有空格
        System.out.printf("%05d%n",35435431);

    }

    @Test
    public void 手机截取测试() {
        String value = "18273645241";
        Pattern pattern = Pattern.compile("(\\d{3})\\d{4}(\\d{4})");
        pattern.split(value);
        Matcher m = pattern.matcher(value);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));// This order was placed for QT3000! OK?
            System.out.println("Found value: " + m.group(1));// This order was placed for QT
            System.out.println("Found value: " + m.group(2));// 3000
        } else {
            System.out.println("NO MATCH");
        }
        System.out.println(value.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
    }
}
