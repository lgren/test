package work.jyyh.archives.db.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * TODO
 * @author lgren
 * @since 2020-12-18 11:15 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@BaseAbs.Translate(name = "ue01")
public class Aa10 extends BaseAbs {
    @Id
    @Where
    private String AAA100; // AAA100代码类别
    @Translate(limitSize = 50)
    private String AAA101; // AAA102代码值
    @Where
    private String AAA102; // AAA102代码值
    @Translate(limitSize = 50)
    private String AAA103; // AAA103代码名称
    private String YAB003; // YAB003经办机构
    private String AAE120; // AAE120注销标志
    private Double VER; // UL
}
