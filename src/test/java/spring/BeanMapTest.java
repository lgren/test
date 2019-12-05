package spring;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.cglib.beans.BeanMap;
import pojo.Person;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * beanMap测试
 * @author lgren
 * @since 2019-11-29 16:14
 */
public class BeanMapTest {
    @Test
    public void name1() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Person person = new Person(1L, "test", new Date());
        // bean转map
        BeanMap beanMap = BeanMap.create(person);

        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("realName", "你猜");
        map.put("birthday", new Date());
        map.put("intArr", new Integer[]{1, 2, 3, 4, 5});
        // map转bean
        Person person1 = new Person();
        BeanMap.create(person1).putAll(map);

        // BeanUtils.populate(person1, map);
        // BeanUtils.getProperty(person1, "id");
        System.out.println();
    }
}
