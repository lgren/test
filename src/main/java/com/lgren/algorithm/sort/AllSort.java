package com.lgren.algorithm.sort;

import java.util.Arrays;

/**
 * 所有排序算法
 * https://www.cnblogs.com/onepixel/articles/7674659.html
 * @author lgren
 * @create 2019-04-02 9:26 AM
 **/
public class AllSort {
    /**
     * 冒泡算法
     * 思路:
     * 双循环, 最外层循环确定排序好的部分
     * 里边循环从1开始向前1位比较, 如果 < 前1位则与前一位交换位置, 直到最后
     * @create 2019/4/2 10:14 AM
     * @author Lgren
     */
    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 1; j < len - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    Common.swap(arr, j - 1, j);
                }
            }
        }
    }

    /**
     * 选择排序
     * 思路:
     * 双循环, 最外层确定排序好的部分
     * 里边循环从排好的序号的下1个开始直到最后, 找到最小值, 然后将拍好的下1个序号与最小值的序号替换
     * @create 2019/4/2 10:35 AM
     * @author Lgren
     */
    public static void selectionSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int minPos = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minPos]) {
                    minPos = j;
                }
            }
            Common.swap(arr, i, minPos);
        }
    }

    /**
     * 插入排序
     * @create 2019/4/2 10:35 AM
     * @author Lgren
     */
    public static void insertionSort(int[] arr) {
        int len = arr.length;
        for (int i = 1, prevI, checkV; i < len; i++) {
            prevI = i - 1;
            checkV = arr[i];
            while (prevI >= 0 && arr[prevI] > checkV) {
                arr[prevI + 1] = arr[prevI];
                prevI--;
            }
            arr[prevI + 1] = checkV;
        }
    }

    /**
     * 希尔排序 第一个突破n²的排序算法
     * @create 2019/4/2 10:35 AM
     * @author Lgren
     */
    public static void shellSort(int[] arr) {
        int len = arr.length;
        int gap = len;
        while ((gap = (int) Math.floor(gap / 2)) > 0) {
            for (int i = gap, prevI, checkV; i < len; i++) {
                prevI = i - gap;
                checkV = arr[i];
                while (prevI >= 0 && arr[prevI] > checkV) {
                    arr[prevI + gap] = arr[prevI];
                    prevI -= gap;
                }
                arr[prevI + gap] = checkV;
            }
        }
    }

    /**
     * 归并排序 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用
     * @create 2019/4/4 3:44 PM
     * @author Lgren
     */
    public static void mergeSort(int[] arr) {
        int[] sortArr = mergeSort1(arr);
        for (int i = 0; i < sortArr.length; i++) {
            arr[i] = sortArr[i];
        }
    }

    /**
     * 归并排序 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用
     * @create 2019/4/4 3:44 PM
     * @author Lgren
     */
    public static int[] mergeSort1(int[] arr) {
        int len = arr.length;
        if (len < 2) return arr;
        int leftLen = (int) Math.floor(len / 2);
        int rightLen = len - leftLen;
        int[] left = new int[leftLen];
        int[] right = new int[rightLen];
        System.arraycopy(arr, 0, left, 0, leftLen);
        System.arraycopy(arr, leftLen, right, 0, rightLen);
        return merge(mergeSort1(left), mergeSort1(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int lLen = left.length;
        int rLen = right.length;
        int allLen = lLen + rLen;
        int[] resultArr = new int[allLen];

        int li = 0, ri = 0;
        while (li + ri < allLen) {
            if (li == lLen) {
                resultArr[li + ri] = right[ri];
                ri++;
            } else if (ri == rLen) {
                resultArr[ri + li] = left[li];
                li++;
            } else if (left[li] < right[ri]) {
                resultArr[li + ri] = left[li];
                li++;
            } else {
                resultArr[li + ri] = right[ri];
                ri++;
            }
        }

        return resultArr;
    }

    /**
     * 快速排序
     * @create 2019/4/4 4:01 PM
     * @author Lgren
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int low = l;
        int high = r;
        int key = arr[low];
        while (low < high) {
            while (high > low && arr[high] >= key) --high;
            arr[low] = arr[high];
            while (low < high && arr[low] < key) ++low;
            arr[high] = arr[low];
        }
        arr[low] = key;
        quickSort(arr, l, low - 1);
        quickSort(arr, low + 1, r);
    }

    public static void main(String[] args) {
        int[] arr1 = {1};
        int[] arr2 = {2};
        System.out.println(Arrays.toString(merge(arr1, arr2)));
    }
}
