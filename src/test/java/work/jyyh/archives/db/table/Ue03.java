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
@BaseAbs.Translate(name = "ue03")
public class Ue03 extends BaseAbs {
    @Id
    @Where
    private String yue030;

    /**
     * 业务材料名称
     */
    @Translate// TEXT
    private String yue031;

    /**
     * 默认页数
     */
    private Integer yue032;

    /**
     * 原材料编号
     */
    private String yue033;

    /**
     * 是否有效 0-无效 1-有效
     */
    private String aae100;

    /**
     * 材料主体（01-个人，02-单位）
     */
    private String yue035;

    /**
     * 档案门类
     */
    private String yue001;

    /**
     * 材料编号前缀
     */
    private String yue034;

    /**
     * 接入系统编码
     */
    private String yue211;

}
