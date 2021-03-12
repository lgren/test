package hutool;

import cn.hutool.script.ScriptUtil;
import org.junit.Test;

/**
 * TODO
 * @author lgren
 * @since 2020-12-25 2:10 下午
 */
public class ScriptTest {
    @Test
    public void test1() {
        System.out.println(ScriptUtil.eval("{name1: '你好呀'}"));
    }
}
