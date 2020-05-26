package other;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 * @author lgren
 * @since 2020-05-08 5:49 下午
 */
public class InterestingTest {
    static int maxIndex = 50;    //控制输出的进度条宽度

    public static void main(String[] args) {
        begin();
    }

    private static void begin() {
        StringBuilder kg = new StringBuilder();
        for (int i = 0; i < maxIndex; i++) {
            kg.append(" ");
        }
        System.out.print("安装中:00%[>" + kg.toString() + "]");
        int c = 0;
        while (c < 101) {
            printCurrentNum(c++);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void focusGoto() {
        for (int i = maxIndex + 6; i > 0; i--) {
            System.out.print('\b');
        }
    }

    private static void printCurrentNum(int i) {
        String num = "000" + i;
        num = num.substring(num.length() - 3);
        StringBuffer s = new StringBuffer(num + "%[");
        focusGoto();
        int prec = (i * 100) / 100;
        for (int index = 0; index < maxIndex; index++) {
            int c = (index * 100) / maxIndex;
            if (c < prec) {
                s.append("■");
            } else {
                s.append(" ");
            }
        }
        s.append("]");
        System.out.print(s.toString());
    }
    // public static void main(String[] args) throws InterruptedException {
    //     System.out.print("Progress:");
    //     int size = 3;
    //     int realSize = 4;
    //     for (int i = 1; i <= 100; i++) {
    //         if (i != 1) {
    //             for (int j = 0; j < realSize; j++) {
    //                 System.out.print("\b");
    //             }
    //         }
    //         System.out.print(String.format("% " + size + "d%%", i));
    //         TimeUnit.MILLISECONDS.sleep(100);
    //     }
    //     System.out.println();
    // }
}
