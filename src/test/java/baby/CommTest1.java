package baby;

import java.util.*;

/**
 * 一共公用的测试
 * @author lgren
 * @since 2019-12-28 21:55
 */
public class CommTest1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] strArr = str.split(",");
            Map<Integer, String> map = new TreeMap<>((a, b) -> b-a);
            for (int i = 0; i < strArr.length; i++) {
                int nowNum = Integer.valueOf(strArr[i]);
                String prevStr= map.get(nowNum);
                if (prevStr == null) {
                    map.put(nowNum, String.valueOf(i));
                } else {
                    map.put(nowNum, prevStr + "," + String.valueOf(i));
                }
            }
            Collection<String> valueColl = map.values();
            StringBuilder result = new StringBuilder();
            for (String valStr : valueColl) {
                if (result.length() == 0) {
                    result = new StringBuilder(valStr);
                } else {
                    result.append(",").append(valStr);
                }
            }
            System.out.println(result.toString());
        }

    }

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
                if (arr[j] < arr[j - 1]) {
                    int lv = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = lv;
                }
            }
        }
    }
}
