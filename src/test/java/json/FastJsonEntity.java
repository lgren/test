package json;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 测试实体类
 * @author lgren
 * @since 2020-08-31 5:34 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@EsFormat
public class FastJsonEntity extends FastJsonEntityBase {
    @EsFormat(false)
    private String name;
    private int age;
    private int AGGG;
}
