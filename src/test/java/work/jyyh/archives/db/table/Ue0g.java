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
@BaseAbs.Translate(name = "ue0g")
public class Ue0g extends BaseAbs {
    @Id
    @Where
    private String yub004;

    /**
     * 行政区划名称
     */
    @Translate(limitSize = 50)
    private String yue0e3;

    /**
     * 所属省级行政区划代码
     */
    private String yub005;

    /**
     * 所属省名称
     */
    @Translate(limitSize = 100)
    private String yue0g2;

    /**
     * 在组织机构中的编号
     */
    private String orgid;

}
