package time;

import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * java8时间测试
 * @author lgren
 * @since 2019-10-16 13:46
 */
public class DateTimeTest {
    @Test
    public void name() {
        String beginStr = "200101";
        String endStr = "200212";
        int i = betweenMonth(beginStr, endStr);
        List<String> strings = listMonth(beginStr, endStr);
        System.out.println();
    }

    public int betweenMonth(String beginStr, String endStr) {
        int beginYear = Integer.valueOf(beginStr.substring(0, 4));
        int beginMonth = Integer.valueOf(beginStr.substring(4, 6));
        int endYear = Integer.valueOf(endStr.substring(0, 4));
        int endMonth = Integer.valueOf(endStr.substring(4, 6));
        return (endYear - beginYear) * 12 + endMonth - beginMonth + 1;
    }

    public List<String> listMonth(String beginStr, String endStr) {
        List<String> result = new ArrayList<>();
        int beginYear = Integer.valueOf(beginStr.substring(0, 4));
        int beginMonth = Integer.valueOf(beginStr.substring(4, 6));
        int endYear = Integer.valueOf(endStr.substring(0, 4));
        int endMonth = Integer.valueOf(endStr.substring(4, 6));


        int monthDiff = (endYear - beginYear) * 12 + endMonth - beginMonth + 1;
        int yearTemp = beginYear;
        int monthTemp = beginMonth;
        for (int i = 1; i <= monthDiff; i++, monthTemp++) {
            if (monthTemp > 12) {
                yearTemp++;
                monthTemp = 1;
            }
            result.add(String.format("%04d%02d", yearTemp, monthTemp));
        }
        return result;
    }
}
