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
public class Ue01 extends BaseAbs {
    /** 业务环节编号（三级类目） */
    @Id
    @Where
    private String yue010;

    /** 业务环节名称 */
    @Translate(limitSize = 200)
    private String yue012;

    /** 密级 */
    private String yuf00i;

    /** 案卷类别编号（目录号） */
    private String yue013;

    /** 案卷类别名称（卷名） */
    @Translate(limitSize = 200)
    private String yue014;

    /** 档案类别 */
    private String yue002;

    /** 保管期限 */
    private String yuf00h;

    /** 业务主体(0:单位或个人,1:单位,2:个人) */
    private String yue01b;

    /** 档案门类 */
    private String yue001;

    /** 是否有效（0无效，1有效） */
    private String aae000;

    /** 拼音 */
    private String yue01d;

    /** 经办人 */
    private String aae011;

    /** 经办机构 */
    private String aae017;

    /** 经办时间 */
    private java.sql.Timestamp aae036;
}
