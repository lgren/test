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
public class Ue06 extends BaseAbs {
    @Id
    @Where
    private String yue060;

    /**
     * 档案门类
     */
    private String yue001;

    /**
     * 档案门类名称
     */
    @Translate(limitSize = 50)
    private String yue061;

    /**
     * 对应表名
     */
    private String yue062;

    /**
     * 代码服务名
     */
    private String yue063;

    /**
     * 业务数据采集时档案类型(2-案卷 3-文件 4-盒 5-箱）
     */
    private String yuf001;

    /**
     * 启用状态（1：表示启用，0表示不启用）
     */
    private String yue064;

    /**
     * 是否自动装盒
     */
    private String yue065;

    /**
     * 装盒规格(2-案卷 3-文件 4-盒 5-箱）
     */
    private String yue066;

    /**
     * 条码连续不断
     */
    private String yue067;

    /**
     * 是否启用ES查询
     */
    private String yue068;

    /**
     * ES索引名
     */
    private String yue069;

    /**
     * 门类所在档案大类（业务、财务、文书、项目、声像、人事、实物）
     */
    private String yue06a;
}
