package test.enumsTest;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pojo.Person;


/**
 * TODO
 * @author Lgren
 * @create 2018-08-28 10:41
 **/
public class EnumTest {
    @Test
    public void test1() {
        Person x = JSONObject.parseObject(null, Person.class);
        System.out.println(x);
    }
}
