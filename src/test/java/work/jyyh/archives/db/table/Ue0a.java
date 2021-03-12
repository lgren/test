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
@BaseAbs.Translate(name = "ue0a")
public class Ue0a extends BaseAbs {
    @Id
    @Where
    private String yue0a0;

    /** 档案门类 */
    private String yue001;

    /** 案卷类别 */
    private String yue002;

    /** 档案类别 */
    private String yue0a1;

    /** 保管期限 */
    private String yuf00h;

    /** 目录号 */
    private String yue013;

    /** 卷名 */
    @Translate(limitSize = 200)
    private String yue014;

    /** 经办人 */
    private String aae011;

    /** 经办机构 */
    private String aae017;

    /** 经办日期 */
    private java.sql.Timestamp aae036;

    /** 是否有效 */
    private String aae000;
}
