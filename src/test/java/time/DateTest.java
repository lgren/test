package time;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * apache时间工具类测试
 * @author lgren
 * @since 2019-09-26 11:20
 */
public class DateTest {
    @Test
    public void timeTest() {
        Date date = new Date();
        date = DateUtils.setYears(date, 2018);
        date = DateUtils.setMonths(date, 6);
        date = DateUtils.setDays(date, 21);
        date = DateUtils.setHours(date, 10);
        date = DateUtils.setMinutes(date, 31);
        date = DateUtils.addMinutes(date, -30);
        date = DateUtils.addSeconds(date, -1);
        // System.out.println(LgrenUtil.getInterval(date, "跟进"));
        System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
