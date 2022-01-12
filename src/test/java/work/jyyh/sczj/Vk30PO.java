package work.jyyh.sczj;

import cn.hutool.core.bean.BeanUtil;
import com.lgren.poi.poi3_17.new_read.LRowCommon;
import com.lgren.poi.poi3_17.new_read.ReadExcelField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.Map;

/**  */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Vk30PO {

    /**  对接12345日志ID */
    @ReadExcelField("资料简码")
    private String yvk301;
    /**  知识ID */
    @ReadExcelField("资料简码")
    private String yvk001;
    /** 知识标题 */
    @ReadExcelField("标题")
    private String yvk003;
    /** 发布时间|出文时间 */
    @ReadExcelField("发文日期")
    private Date yvk007;
    /** 生效时间 */
    @ReadExcelField("生效日期")
    private Date yvk012;
    /** 失效时间 */
    @ReadExcelField("失效日期")
    private Date yvk013;
    /**  知识内容 */
    @ReadExcelField("资料内容")
    private String yvk016;
    /**  行政区划代码 */
    @ReadExcelField(value = "适用范围", mapping4 = "01-国家,02-部级,03-省级,03-省本级,04-市级,05-区县,05-区级,05-县级")
    private String yvb301;
    /**  业务领域ID */
    @ReadExcelField("所属类别")
    private String yve901;
    /**  业务领域名称 */
    @ReadExcelField("所属类别")
    private String yve902;
    /**  知识发布人名称 */
    @ReadExcelField("创建人")
    private String yvk302;
    /**  知识发布人部门名称 */
    @ReadExcelField("发文单位")
    private String yvk303;
    /**  附件标识 */
    @ReadExcelField(LRowCommon.USE_UUID)
    private String yvk304;
    /** 同步开始时间 */
    private Date yvk305;
    /** 同步结束时间 */
    private Date yvk306;
    /** 调用接口是否成功 */
    private String yvk307;
    /** 接口入参 */
    private String yvk308;
    /** 接口出参(同步结果详情) */
    private String yvk309;
    /** 同步类型(1新增 2更新 3删除) */
    private String yvk310;
    // /** !!必填!! 逻辑删除 */
    // private String aae100;
    /** 创建人ID */
    private String aae011;
    /** 创建人姓名 */
    private String aae012;
    // /** 更新人ID */
    // private String aae013;
    // /** 更新人姓名 */
    // private String aae014;
    /** 创建时间 */
    private Date aae036;
    // /** 更新时间 */
    // private Date aae037;
    // /** 创建人部门ID */
    // private final String aae017 = "四川省人力资源与社会保障厅信息中心";
    // /** 更新人部门ID */
    // private String aae019;


    public static Vk30PO build(Map<String, Object> map) {
        return BeanUtil.mapToBean(map, Vk30PO.class, false);
    }
}
