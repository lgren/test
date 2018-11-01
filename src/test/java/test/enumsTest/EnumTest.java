package test.enumsTest;

import com.alibaba.fastjson.JSONObject;
import com.lgren.enums.TestEnum;
import com.lgren.ohers.TaskAndMsgEnum;
import org.junit.Test;
import pojo.Person;

import static java.util.Optional.ofNullable;

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
