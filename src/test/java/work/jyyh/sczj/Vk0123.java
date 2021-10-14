package work.jyyh.sczj;

import com.lgren.poi.poi3_17.new_read.ReadExcelField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Vk0123 implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 适用范围/行政级别
     */
    @ReadExcelField("适用范围")
    private String yvk001;

    /**
     * 信息是否公开
     */
    @ReadExcelField("信息公开")
    private String yvk002;

    /**
     * 创建人
     */
    @ReadExcelField("创建人")
    private String yvk003;

    /**
     * 标题
     */
    @ReadExcelField("标题")
    private String yvk004;

    /**
     * 资料简码
     */
    @ReadExcelField("资料简码")
    private String yvk005;

    /**
     * 所属类别
     */
    @ReadExcelField("所属类别")
    private String yvk006;

    /**
     * 发文日期
     */
    @ReadExcelField("发文日期")
    private Date yvk007;

    /**
     * 更新日期
     */
    @ReadExcelField("更新日期")
    private String yvk008;

    /**
     * 发文单位
     */
    @ReadExcelField("发文单位")
    private String yvk009;

    /**
     * 是否有效
     */
    @ReadExcelField("是否有效")
    private String yvk010;

    /**
     * 状态
     */
    @ReadExcelField("状态")
    private String yvk011;

    /**
     * 关键字
     */
    @ReadExcelField("关键字")
    private String yvk012;

    /**
     * 文号
     */
    @ReadExcelField("文号")
    private String yvk013;

    /**
     * 失效日期
     */
    @ReadExcelField("失效日期")
    private Date yvk014;

    /**
     * 生效日期
     */
    @ReadExcelField("生效日期")
    private Date yvk015;

    /**
     * 内容
     */
    @ReadExcelField("资料内容")
    private String yvk016;

}
