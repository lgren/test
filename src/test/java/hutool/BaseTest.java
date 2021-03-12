package hutool;

import cn.hutool.core.bean.BeanUtil;
import json.EntityBase;
import json.TestEntity;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * hutool基础测试
 * @author lgren
 * @since 2020-09-07 4:42 下午
 */
public class BaseTest {
    private static final Map<Class<?>, Map<String, PropertyDescriptor>> FIELD_MAP = new HashMap<>(22);
    @Test
    public void beanUtil() {
        Map<String, PropertyDescriptor> fieldMap = FIELD_MAP.computeIfAbsent(TestEntity.class,
                c -> BeanUtil.getPropertyDescriptorMap(c, true));
        System.out.println();
    }
}
