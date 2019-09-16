package jyyh;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * TODO
 * @create 2019-06-21 12:18
 * @since lgren
 */
public class ZhiShiKuTest {
    @Test
    public void name1() throws Exception {
        File file = new File("/Users/lgren/Desktop/Base64Util.java");
        InputStream input = new FileInputStream(file);
        byte[] bytes = new byte[input.available()];
        String str = Base64Util.encodeBase64(bytes);
        System.out.println(str);

    }
}
