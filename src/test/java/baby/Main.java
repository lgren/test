package baby;

import java.text.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int other = 0;
        Map<Long, Integer> map = new HashMap<>();
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.length() == 0) {
                break;
            }
            long timeYMD = sdfYMD.parse(str).getTime();
            Integer prevTime = map.get(timeYMD);
            Integer timeSSSInt = Integer.valueOf(str.substring(str.lastIndexOf(".") + 1));
            if (prevTime == null) {
                map.put(timeYMD, timeSSSInt);
            } else {
                if (Objects.equals(timeSSSInt, prevTime)) {
                    other++;
                }
                map.put(timeYMD, timeSSSInt < prevTime ? timeSSSInt : prevTime);
            }
        }
        System.out.println(map.size() + other);
    }
}



/*
1992-08-20 12:12:12.001
1992-08-20 12:12:12.001
1992-08-20 12:12:12.002
1992-08-20 12:12:12.002
1992-08-20 12:12:12.003
1992-08-21 12:12:12.001
1992-08-22 12:12:12.001
输入年月日时分秒
多少人抢到票

同一秒内时间不同选最早的
相同时间选择多个

输入多行字符串
输出整数
 */
