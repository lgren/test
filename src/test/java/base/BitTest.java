package base;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * TODO
 * @author lgren
 * @since 2020-07-07 3:43 下午
 */
public class BitTest {
    @Test
    public void name1() {
        print(1<<2); // 1 x 2的2次方 = 4
        print(3<<2);// 3 x 2的2次方 = 12
    }

    @Test
    public void name2() {
        print(-1<<2);// -1 x 2的2次方 = 4
        print(-1);// -1 x 2的2次方 = 4
        print(1);// -1 x 2的2次方 = 4
        print(-1 ^ 1);// -1 x 2的2次方 = 4
        print(-3<<2);// -3 x 2的2次方 = -12

    }

    @Test
    public void name() {
        Instant instant = Instant.ofEpochMilli(1420041600000L).atOffset(ZoneOffset.ofHours(8)).toInstant();
        long now = 1594109619635L;
        // Instant.ofEpochMilli(1594109619635L).atZone(ZoneId.systemDefault())
        long t2015 = 1420041600000L;// 2015-01-01
        // Instant.ofEpochMilli(1420041600000L).atZone(ZoneId.systemDefault())
        System.out.println(Instant.now().toEpochMilli());

    }

    private void print(long i) {
        System.out.printf("%-4s -> %64s\n", i, Long.toBinaryString(i));
    }
}
