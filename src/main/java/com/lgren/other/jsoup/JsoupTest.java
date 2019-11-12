package com.lgren.other.jsoup;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * TODO
 * @author lgren
 * @since 2019-10-25 16:30
 */
public class JsoupTest {
    @Test
    public void test1() {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println();
    }

    @Test
    public void testUrl() throws IOException {
        Document doc = Jsoup.connect("https://www.baidu.com").get();
        String title = doc.title();
        System.out.println();
    }

    @Test
    public void testFile() throws IOException {
        File input = new File("/Users/lgren/Project/JavaIdeaSpace/test/src/main/resource/html1/zsk.html");
        Document doc = Jsoup.parse(input, "UTF-8");
        System.out.println();
    }
}
