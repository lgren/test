package base;

import org.junit.Test;

/**
 * TODO
 * @author lgren
 * @since 2020-06-29 10:50 上午
 */
public class CharTest {
    static final char[][] TEXT = new char[64][];
    static {
        // TEXT['<'] = "&lt;".toCharArray();// 60
        // TEXT['>'] = "&gt;".toCharArray();// 62
        // TEXT['&'] = "&amp;".toCharArray();// 38
        // TEXT['"'] = "&quot;".toCharArray();// 34
        // TEXT['\''] = "&#039;".toCharArray();// 39 单引号 ('&apos;' doesn't work - it is not by the w3 specs)
        // TEXT[10] = "</br>".toCharArray();
        // TEXT[13] = "</br>".toCharArray();
        TEXT['<'] = new char[]{'&', 'l', 't', ';'};// &lt;  60
        TEXT['>'] = new char[]{'&', 'g', 't', ';'};// &gt;  62
        TEXT['&'] = new char[]{'&', 'a', 'm', 'p', ';'};// &amp;  38
        TEXT['"'] = new char[]{'&', 'q', 'u', 'o', 't', ';'};// &quot;  34
        TEXT['\''] = new char[]{'&', '#', '0', '3', '9', ';'};// &#039;  39// 单引号 ('&apos;' doesn't work - it is not by the w3 specs)
        // TEXT[10] = new char[]{'<', '/', 'b', 'r', '>'};// 换行  10
        // TEXT[13] = new char[]{'<', '/', 'b', 'r', '>'};// 回车  13
    }

    @Test
    public void test1() {
        System.out.println("<: " + (int)'<');// &lt;  60
        System.out.println(">: " + (int)'>');// &gt;  62
        System.out.println("&: " + (int)'&');// &amp;  38
        System.out.println("\": " + (int)'"');// &quot;  34
        System.out.println("': " + (int)'\'');// &#039;  39// 单引号 ('&apos;' doesn't work - it is not by the w3 specs)
        System.out.println("10: " + (char)10);// 换行  10
        System.out.println("13: " + (char)13);// 回车  13
        System.out.println("over");
    }

    @Test
    public void test2() {
        System.out.println(TEXT['>']);
        char[] chars = TEXT['9'];
        StringBuilder sb = new StringBuilder();
        sb.append(chars);
        System.out.println(sb.toString());
    }
}
