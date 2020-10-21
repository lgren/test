package base.source;

import org.junit.Test;

import java.util.HashMap;

/**
 * TODO
 * @author lgren
 * @since 2020-07-08 9:15 上午
 */
public class HashMapTest {

    @Test
    public void main() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 2);
        hashMap.get(1);
    }

    @Test
    public void common() {
        System.out.println(tableSizeFor(16));
    }

    private int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
    }
}
