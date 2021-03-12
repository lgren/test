package base;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * TODO
 * @author lgren
 * @since 2020-11-30 4:26 下午
 */
public class I18nTest {
    @Test
    public void name() {
        ResourceBundle zh = ResourceBundle.getBundle("message", Locale.CHINA);
        ResourceBundle us = ResourceBundle.getBundle("message", Locale.US);
        System.out.println(zh.getString("my.message"));
        System.out.println(us.getString("my.message"));
    }
}
