package baby;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 一共公用的测试
 * @author lgren
 * @since 2019-12-28 21:55
 */
public class CommTest {

    @Test
    public void name1() {
        String[] split = "dsadsa,dsadsa.dsadsdas/asdsada\\sdsadsa".split("[,./\\\\]");
        System.out.println(Arrays.toString(split));
    }

    @Test
    public void name2() {
        String test = "hdjk你sadas你dsadsad你sadas";
        Pattern p = Pattern.compile("你");
        Matcher m = p.matcher(test);
        if (m.find()) {
            System.out.println();
        } else {
            System.out.println("NO MATCH");
        }

    }
}
