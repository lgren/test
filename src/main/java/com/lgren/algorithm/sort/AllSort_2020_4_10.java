package com.lgren.algorithm.sort;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 所有排序算法
 * https://www.cnblogs.com/onepixel/articles/7674659.html
 * @author lgren
 * @since 2020-04-10 1:29 下午
 **/
public class AllSort_2020_4_10 {
    /** 冒泡排序 将最大值/最小值一一比较最终排序完成 */
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {// 最后一位数不用对比
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    Common.swap(arr, j, j + 1);
                }
            }
        }
    }

    /** 选择排序 找到最大值/最小值排到最前或最后 */
    public static void selectSort(int[] arr) {
        for (int i = arr.length - 1, mi = 0; i > 0; i--, mi = 0) {
            for (int j = 1; j <= i; j++) {
                if (arr[j] > arr[mi]) {
                    mi = j;
                }
            }
            Common.swap(arr, mi, i);
        }
    }

    /** 插入排序 提出当前值和前边/后面对比 大于/小于则互换位置 如果小于/大于则停止 进行下一轮比较 */
    public static void insertSort(int[] arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            int j = i;
            while (j < arr.length - 1 && arr[j] > arr[j + 1]) {
                Common.swap(arr, j, j + 1);
                j++;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(Common.verify(AllSort_2020_4_10::bubbleSort));
        System.out.println(Common.verify(AllSort_2020_4_10::selectSort));
        System.out.println(Common.verify(AllSort_2020_4_10::insertSort));

    }
}
