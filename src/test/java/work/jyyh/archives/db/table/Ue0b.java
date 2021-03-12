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
@BaseAbs.Translate(name = "ue0b")
public class Ue0b extends BaseAbs {
    @Id
    @Where
    private String yue0b0;

    /** 档案类别 */
    private String yue001;

    /** 案卷类别 */
    private String yue002;

    /** 案卷类别名称 */
    @Translate(limitSize = 150)
    private String yue0b1;

}
