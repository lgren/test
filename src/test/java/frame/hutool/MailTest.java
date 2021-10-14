package frame.hutool;

import cn.hutool.extra.mail.MailUtil;
import org.junit.Test;

/**
 * TODO
 * @author lgren
 * @since 2020-10-28 3:27 下午
 */
public class MailTest {
    @Test
    public void send() {
        MailUtil.send("625552409@qq.com", "这是一个测试", "这是测试内容", false);
    }
}
