package number;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * 两数相除保留小数
 * @author lgren
 * @since 2019-11-28 16:51
 */
public class 两数相除保留小数 {
    @Test
    public void name1() {
        long l1 = 20;
        long l2 = 60;
        BigDecimal bigDecimal = new BigDecimal((float) l1 * 100 / (l1 + l2))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println();
    }
}
