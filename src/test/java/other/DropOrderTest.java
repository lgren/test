package other;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.lgren.util.LgrenUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO
 * @author lgren
 * @since 2019-12-27 09:52
 */
public class DropOrderTest {

    private static final List<Map<String, Object>> list = Lists.newArrayList(
            LgrenUtil.newHashMap("position", 1)
            ,LgrenUtil.newHashMap("position", 2)
            ,LgrenUtil.newHashMap("position", 3)
            ,LgrenUtil.newHashMap("position", 4)
            ,LgrenUtil.newHashMap("position", 5)
            ,LgrenUtil.newHashMap("position", 6)
    );

    @Test
    public void name1() {

    }
    private void dropType1(Map<String, Object> thisNode, Map<String, Object>targetNode, String moveType) {

    }
}
