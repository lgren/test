package com.lgren.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO
 * @author lgren
 * @since 2020-04-10 1:13 下午
 */
public class SomeArrMergeSort {
    private int[] getArr(int length) {
        int[] arr = new int[length];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(0, 1000);
        }
        return arr;
    }

    @Test
    public void sort() {
        int[] arr1 = getArr(5);
        int[] arr2 = getArr(5);
        int[] arr3 = getArr(5);
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        Arrays.sort(arr3);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        int[] result = new int[arr1.length + arr2.length + arr3.length];
    }

}
