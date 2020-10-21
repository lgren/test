package json;

import cn.hutool.core.util.ReflectUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Field;

/**
 * 测试实体类
 * @author lgren
 * @since 2020-08-31 5:34 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@EsFormat
public class TestEntity extends EntityBase {
    @EsFormat(false)
    private String name;
    private int age;
    private int AGGG;
}
