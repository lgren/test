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
@BaseAbs.Translate(name = "ue07")
public class Ue07 extends BaseAbs {
    @Id
    @Where
    private String                yue070;

    /** 保管期限代码 */
    private String                yuf00h;

    /** 保管期限名称 */
    @Translate(limitSize = 50)
    private String                yue071;

    /** 档案门类 */
    private String                yue001;

    /** 期限年度 */
    private Integer               yue072;

}
