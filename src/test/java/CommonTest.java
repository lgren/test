import cn.hutool.core.io.FileUtil;
import com.google.common.collect.Lists;
import com.lgren.util.LgrenUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


public class CommonTest {
    @Test
    public void test5() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEachOrdered(System.out::println);
    }

    @Test
    public void test7() {
        System.out.println(new DecimalFormat(",###").format(432.232432D));
        ThreadLocalRandom random = ThreadLocalRandom.current();
    }

    @Test
    public void test10() {
        int i = 10;
        flag1:
        while (i > 0) {
            int j = 10;
            while (j > 0) {
                System.out.println(j);
                --j;
                if (Objects.equals(j, 6)) {
                    break flag1;
                }
            }
            System.out.println(i);
            --i;
        }
    }

    @Test
    public void gcdTest() {
        System.out.println(gcd(45, 55));
        /*
        45 55
        55 45
        45 10
        10 5
        5 0
         */
    }

    // 求最大公约数
    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    @Test
    public void name() {
        String timeStr = "20200416";
        char[] formatStr = new char[11];
        formatStr[4] = '年';
        formatStr[7] = '月';
        formatStr[10] = '日';
        for (int i = 0, ti = 0; i < timeStr.length(); i++, ti++) {
            if (ti == 4 || ti == 7 || ti == 10) {
                ti++;
            }
            formatStr[ti] = timeStr.charAt(i);
        }
        new String(formatStr);

        System.out.println(timeStr.substring(0, 4) + "年"
                + timeStr.substring(4, 6) + "月"
                + timeStr.substring(6) + "日");
    }

    @Test
    public void name1() {
        StringUtils.capitalize("testTest");
        StringUtils.capitalize("test_test");
    }

    @Test
    public void name2() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        Spliterators.spliterator(list, 0);
    }

    @Test
    public void name3() {
        System.out.println(Integer.toBinaryString(0x00000001));
        System.out.println(Integer.toBinaryString(0x00000002));
        System.out.println(Integer.toBinaryString(0x00000004));
        System.out.println(Integer.toBinaryString(0x00000008));
        System.out.println(Integer.toBinaryString(0x00000010));
        System.out.println(Integer.toBinaryString(0x00000020));
        System.out.println(Integer.toBinaryString(0x00000040));
        System.out.println(Integer.toBinaryString(0x00000080));
    }

    @Test
    public void feature() {
        int view = 0x00000001;
        int add = 0x00000002;
        int remove = 0x00000004;
        int edit = 0x00000008;

        int one = view;
        int two = view | edit;
        int three = view | add | remove;
        int four = view | add | remove | edit;
        System.out.println();
    }


    public static void main(String[] args) {
        List<String> list1 = Lists.newArrayList("1", "2", "3", "4", "5", "6");
        List<Integer> list2 = Lists.newArrayList(4, 5, 6, 7, 8);
        LgrenUtil.diffHandle(list1, list2,
                (l, r) -> Objects.equals(l, String.valueOf(r)),
                l -> System.out.printf("这个是左边多的: %s\n", l),
                r -> System.out.printf("这个是右边多的: %s\n", r),
                (l, r) -> System.out.printf("这个是相同的: 左-%s 右-%s\n", l, r)
        );
    }

    @Test
    public void name4() {
        System.out.println("Nihaoya".equalsIgnoreCase(null));
    }

    @Test
    public void name5() {
        // // 格式解析
        // DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        // // 数据库查询的时间
        // TemporalAccessor parse = df.parse("2020-10-13 16:05:00");
        LocalDateTime now = LocalDateTime.now();
        LocalDate nowDate = now.toLocalDate();
        // 昨天19:30
        LocalDateTime yesterday = LocalDateTime.of(nowDate.minusDays(1L), LocalTime.of(19, 30));
        // 今天9:00
        LocalDateTime today = LocalDateTime.of(nowDate, LocalTime.of(9, 0));
        System.out.println(now.isAfter(yesterday) && now.isBefore(today));
        System.out.println();

    }


    @Test
    public void name6() {
        test(null);
    }

    private void test(Integer... intArr) {
        System.out.println(Arrays.toString(intArr));
    }


    @Test
    public void name7() {
        System.out.println(~(-1 << 31));
        System.out.println(Integer.MAX_VALUE);

    }

    // @Test
    // public void name8() {
    //     typeTestMethod(Lists.newArrayList(new Person1(), new Person1()));
    //     typeTestMethod(Lists.newArrayList(new Person2(), new Person2()));
    //     typeTestMethod(Lists.newArrayList(new Person12(), new Person12()));
    // }
    //
    //
    // private <T extends Person & ITest & ITest2> void typeTestMethod(final Collection<T> values){
    //     System.out.println(values);
    // }
    //
    // class Person1 extends Person implements ITest {}
    //
    // class Person2 extends Person implements ITest2 {}
    //
    // class Person12 extends Person implements ITest, ITest2 {}


    @Test
    public void name8() {
        byte[] bytes = FileUtil.readBytes("/Users/lgren/Downloads/com.baidu.searchbox/files/com.baidu.searchbox.novel/download_chapter_cache/0d6adcddf1bc06fbbb19eb82b3f9e636/4345120507/1568683669");
        System.out.println();
    }

    @Test
    public void name9() {
        int b = 127;
        b = b++;
        System.out.println(b);
    }

    @Test
    public void name10() {
        int b = 127;
        int b1 = b++;
        System.out.println(b);
        System.out.println(b1);
    }

    @Test
    public void name11() {
        int b = 127;
        b = ++b;
        System.out.println(b);
    }

    @Test
    public void name12() {
        System.out.println(UUID.randomUUID().toString());
    }
}
// i++
// 第一步将原来的值赋值给一个var1
// 第二步将i+1赋值给另一个var2
// 第三步将var1给外部计算 如果
//
