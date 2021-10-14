package frame.hutool;

import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpUtil;
import com.google.common.base.Stopwatch;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * TODO
 * @author lgren
 * @since 2020-09-08 10:40 上午
 */
public class RxsgImgDownload {
    // @Test
    // public void download() {
    //     // HttpUtil.downloadFile("http://game.ali.cdn.ledu.com/rxsg/1.16.0/res/things/armor/8101408.png",// 装备地址
    //     //         "/Users/lgren/Downloads/0test");
    //
    //     // HttpUtil.downloadFile("http://game.ali.cdn.ledu.com/rxsg/1.20.0/res/face/player_male_3.jpg",// 登陆界面玩家地址 female
    //     //         "/Users/lgren/Downloads/0test");
    //     hero("boby", 0, 100);
    // }
    //

    public static void main(String[] args) {
        RxsgImgDownload rxsgImgDownload = new RxsgImgDownload();

        // rxsgImgDownload.heroBoy(0, 10000);
        // rxsgImgDownload.heroGirl(0, 12);

        // rxsgImgDownload.armor(0, 410_0572);
        rxsgImgDownload.armor(0, 1000_0000);

    }

    private Set<String> heroBoy(int begin, int end) {
        return downloadBase(i -> String.format("http://game.ali.cdn.ledu.com/rxsg/1.20.0/res/images/hero/face/hero_boy_%d.jpg", i),
                i -> "/Users/lgren/Downloads/0test/hero/", begin, end);
    }

    private Set<String> heroGirl(int begin, int end) {
        return downloadBase(i -> String.format("http://game.ali.cdn.ledu.com/rxsg/1.20.0/res/images/hero/face/hero_girl_%d.jpg", i),
                i -> "/Users/lgren/Downloads/0test/hero/", begin, end);
    }

    private Set<String> armor(int begin, int end) {
        return downloadBase(i -> String.format("http://game.ali.cdn.ledu.com/rxsg/1.16.0/res/things/armor/%s.png", i),
                i -> "/Users/lgren/Downloads/0test/armor/", begin, end);
    }

    private Set<String> downloadBase(Function<Integer, String> urlFunc, Function<Integer, String> savePathFunc, int begin, int end) {
        Stopwatch watch = Stopwatch.createStarted();
        Set<String> errorList = new ConcurrentHashSet<>();
        int all = end - begin;
        AtomicInteger num = new AtomicInteger();

        ExecutorService exe = Executors.newFixedThreadPool(1000);
        IntStream.range(begin, end).forEach(i -> exe.submit(() -> {
            String url = urlFunc.apply(i);
            try {
                HttpUtil.downloadFile(url, savePathFunc.apply(i));
            } catch (HttpException e) {
                errorList.add(url);
            } finally {
                synchronized (this) {
                    progress(num.incrementAndGet(), all);
                }
            }
        }));
        exe.shutdown();
        try {
            exe.awaitTermination(10, TimeUnit.MINUTES);
            System.out.println(num.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n用时: " + watch.toString());
        return errorList;
    }

    private void progress(int now, int all) {
        for (int j = 0; j < 6; j++) {
            System.out.print("\b");
        }
        double nowD = now / (double) all;
        int calcSpaceNum = (int) nowD / 10;
        System.out.printf("%3.2f%s", nowD, calcSpaceNum == 0 ? "  " : calcSpaceNum == 10 ? "" : " ");
    }
}
