package work.jyyh.sczj;

import com.lgren.poi.poi3_17.new_read.ReadExcelField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.Objects;

/** 知识信息 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Vk01PO {

    /** !!必填!! 知识ID */
    @ReadExcelField("资料简码")
    private String yvk001;
    /** !!必填!! 知识简码(英文加数字组成) */
    @ReadExcelField("资料简码")
    private String yvk002;
    /** !!必填!! 知识标题 */
    @ReadExcelField("标题")
    private String yvk003;
    /** !!必填!! 知识简介 !!内容前100 */
    @ReadExcelField(value = "资料内容", substring1 = {0, 100})
    private String yvk004;
    /** !!必填!! 关键词 */
    @ReadExcelField("关键字")
    private String yvk005;
    /** 出文单位 */
    @ReadExcelField("发文单位")
    private String yvk006;
    /** !!必填!! 出文时间 */
    @ReadExcelField("发文日期")
    private Date yvk007;
    /** 文号 */
    @ReadExcelField("文号")
    private String yvk008;
    /** !!必填!! 行政级别(01-国家,02-部级,03-省级,04-市级,05-区县级 ) */
    @ReadExcelField(value = "适用范围", mapping4 = "01-国家,02-部级,03-省级,04-市级,05-区级,05-县级")
    private String yvk009;
    /** !!必填!! 密级(01公开 02秘密 03绝密) */
    private final String yvk010 = "01";
    /** !!必填!! 公开类型(01-主动公开,02-依申请公开,03-无) */
    @ReadExcelField(value = "信息公开", mapping4 = "01-主动公开,02-依申请公开,03-无")
    private String yvk011;
    /** !!必填!! 有效开始时间 */
    @ReadExcelField("生效日期")
    private Date yvk012;
    /** 有效开始结束 */
    @ReadExcelField("失效日期")
    private Date yvk013;
    /** !!必填!! 知识类型(01政策法规 02问题解答 03新闻快讯 04时政新闻 05名词解释 06其他等等) !!现在全是01 */
    private String yvk014;
    /** !!必填!! 知识内容(纯文本) */
    @ReadExcelField("资料内容")
    private String yvk015;
    /** !!必填!! 知识内容(HTML) */
    @ReadExcelField("资料内容")
    private String yvk016;
    /** !!必填!! 业务领域ID(VE90表) */
    @ReadExcelField("所属类别")
    private String yve901;
    /** !!必填!! 置顶标识(0不置顶 1置顶) */
    private final String yvk017 = "0";
    /** !!必填!! 知识状态(01草稿箱 02回收站 03待审核 04审核中 05审核通过 06审核不通过 07发布) */
    @ReadExcelField(value = "状态",mapping4 = "01-草稿箱,02-回收站,03-待审核,04-审核中,05-审核通过,06-审核不通过,07-发布")
    private String yvk018;
    /** !!必填!! 知识内容有效性(0无效 1有效 2部分有效) */
    @ReadExcelField(value = "是否有效", mapping4 = "0-无效,1-有效,2-部分有效")
    private String yvk019;
    /** 浏览次数(默认0) */
    private Double yvk00a;
    /** 点赞次数(默认0) */
    private Double yvk00b;
    /** 评论次数(默认0) */
    private Double yvk00c;
    /** !!必填!! 经办机构 */
    private final String aae017 = "四川省人力资源与社会保障厅信息中心";
    /** 更新人经办机构 */
    private String aae019;
    /** 适用于地区(省市多选) */
    private String yvk00d;
    /** !!必填!! 知识录入渠道(01系统录入 02或者系统ID接口录入) */
    private final String yvk00e = "02";
    /** 知识图片 */
    private String yvk00f;
    /** 置顶结束时间(置顶1才有结束时间) */
    private Date yvk00g;
    /** 发布时间 */
    private Date yvk00h;
    /** !!必填!! 逻辑删除 */
    private final String aae100 = "1";
    /** 创建人ID */
    private String aae011;
    /** 创建人姓名 */
    @ReadExcelField("创建人")
    private String aae012;
    /** 更新人ID */
    private String aae013;
    /** 更新人姓名 */
    private String aae014;
    /** 创建时间 */
    private Date aae036;
    /** 更新时间 */
    @ReadExcelField("更新日期")
    private Date aae037;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vk01PO vk01PO = (Vk01PO) o;
        return Objects.equals(yvk001, vk01PO.yvk001);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yvk001);
    }

    // @ReadExcelField(value = "所属类别")
    // private String businesses;

    // @ReadExcelField(value = "所属类别", split2 = "->")
    // private String[] businessArr;

}
