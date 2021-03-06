package json;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

/**
 * FastJson测试
 * @author lgren
 * @since 2020-08-31 5:32 下午
 */
public class FastJsonTest {
    @Test
    public void format() {
        TestEntity entity = new TestEntity();
        entity.setId(1);
        entity.setAge(21);
        entity.setName("张三");
        System.out.println(JSON.toJSONString(entity, true));
    }

    @Test
    public void partFieldFormat() {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(TestEntity.class, "id", "age");
        TestEntity entity = new TestEntity();
        entity.setId(1);
        entity.setAge(21);
        entity.setName("张三");
        System.out.println(JSON.toJSONString(entity, filter, SerializerFeature.PrettyFormat));
    }

    @Test
    public void myPartFieldFormat() {
        boolean classValue = Optional.ofNullable((Boolean)AnnotationUtil.getAnnotationValue(TestEntity.class, EsFormat.class))
                .orElse(false);
        if (classValue) {
            Field[] fields = ReflectUtil.getFields(TestEntity.class);
            for (Field field : fields) {
                Boolean value = AnnotationUtil.getAnnotationValue(field, EsFormat.class);
                System.out.println(value);
            }
        }
        System.out.println();
    }
}
