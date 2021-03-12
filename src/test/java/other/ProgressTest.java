package other;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 * @author lgren
 * @since 2020-10-23 11:14 上午
 */
public class ProgressTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            TimeUnit.MILLISECONDS.sleep(5);
            progress(i, 1000);
        }
    }
    private static void progress(int now, int all) {
        for (int j = 0; j < 6; j++) {
            System.out.print("\b");
        }
        double nowD = now / (double) all;
        int calcSpaceNum = (int) nowD / 10;
        System.out.printf("%3.2f%s", nowD, calcSpaceNum == 0 ? "  " : calcSpaceNum == 10 ? "" : " ");
    }
}
