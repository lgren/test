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
@BaseAbs.Translate(name = "uh70")
public class Uh70 extends BaseAbs {
    @Id
    @Where
    private String yub001;

    /** 库房代码 */
    private String yuh701;

    /** 库房名称 */
    @Translate(limitSize = 50)
    private String yuh702;

    /** 图形信息 */
    private String yuh703;

    /** 备注 */
    private String aae013;

    /** 经办人 */
    private String aae011;

    /** 经办时间 */
    private java.sql.Timestamp aae036;

    /** 水平排列方式 */
    private String yuh704;

    /** 垂直排列方式 */
    private String yuh705;

    /** 档案门类 */
    private String yue001;

}
