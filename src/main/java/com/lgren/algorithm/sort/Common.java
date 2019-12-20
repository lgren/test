package com.lgren.algorithm.sort;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 公用变量
 * @author lgren
 * @create 2019-04-02 9:27 AM
 **/
public class Common {

    public static void swap(int[] arr, int l, int r) {
        int lv = arr[l];
        arr[l] = arr[r];
        arr[r] = lv;
    }

    @Test
    public void verification() {
        isTrue(AllSort::bubbleSort, "冒泡");
        // isTrue(AllSort::selectionSort, "选择");
        // isTrue(AllSort::insertionSort, "插入");
        // isTrue(AllSort::shellSort, "希尔");
        // isTrue(AllSort::mergeSort, "归元");
        // isTrue(AllSort::quickSort, "快速");
    }

    @Test
    public void speed() {
        int maxLen = 10_0000;
        // useTime(AllSort::bubbleSort, maxLen, "冒泡排序");
        // useTime(AllSort::selectionSort, maxLen, "选择排序");
        // useTime(AllSort::insertionSort, maxLen, "插入排序");
        useTime(AllSort::shellSort, "希尔排序", maxLen);
        useTime(AllSort::mergeSort, "归元排序", maxLen);
        useTime(AllSort::quickSort, "快速排序", maxLen);
        useTime(Arrays::sort, "Dual-Pivot", maxLen);
    }

    private final static int[] arr = {2, 1, 7, 4, 6, 5, 8, 3, 2, 5, 4, 3, 6, 7, 8,
            5, 2, 16, 17, 18, 28, 2, 818, 173, 61, 61, 18};

    private int[] getArr() {
        int[] arr = new int[Common.arr.length];
        System.arraycopy(Common.arr, 0, arr, 0, Common.arr.length);
        return arr;
    }

    private int[] getRandomArr(int len) {
        int[] arr = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

    private void isTrue(Consumer<int[]> sortMethod, String name) {
        int[] arrTrue = getArr();
        int[] sortArr = getArr();
        Arrays.sort(arrTrue);
        sortMethod.accept(sortArr);
        // AllSort.mergeSort(sortArr);
        System.out.println(Arrays.equals(arrTrue, sortArr) + ":" + name);
    }

    private void useTime(Consumer<int[]> sortMethod, String name, int maxLen) {
        int[] arr = getRandomArr(maxLen);
        Stopwatch watch = Stopwatch.createStarted();
        sortMethod.accept(arr);
        System.out.println(name + ":" + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }


}
