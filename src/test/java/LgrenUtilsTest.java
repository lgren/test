import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.lgren.util.LgrenUtil;
import org.junit.Test;
import pojo.Person;
import pojo.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * 专为 LgrenUtil {@link com.lgren.util.LgrenUtil}
 * @author Lgren
 * @create 2018-11-22 16:55
 **/
public class LgrenUtilsTest {
    @Test
    public void getAllListTest() {
//        ArrayList<Integer> list = Lists.newArrayList(1, 2, 4, 6, 76, 34, 32, 43, 45, 45, 3);
//        LgrenUtil.partition(list, 10);

//        System.out.println(JSON.toJSONString(new Person().setInsertTime(LocalDateTime.now()), SerializerFeature.WriteDateUseDateFormat));
        System.out.println(JSON.toJSONString(new Student(new Person().setInsertTime(LocalDateTime.now())), SerializerFeature.WriteDateUseDateFormat));
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
