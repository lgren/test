import com.google.common.collect.Lists;
import com.lgren.util.LgrenUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;


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
                r -> System.out.printf("这个是左边多的: %s\n", r),
                (l, r) -> System.out.printf("这个是相同的: 左-%s 右-%s\n", l, r)
        );
    }

    @Test
    public void name4() {

    }
}
