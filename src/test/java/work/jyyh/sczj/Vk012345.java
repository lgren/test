package work.jyyh.sczj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName VK012345
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Vk012345 implements Serializable {
    /**
     * 知识ID
     */
    private String yvk001;

    /**
     * 知识标题
     */
    private String yvk002;

    /**
     * 知识内容
     */
    private String yvk003;

    /**
     * 发布时间=出文时间
     */
    private Date yvk004;

    /**
     * 生效时间
     */
    private Date yvk005;

    /**
     * 失效时间
     */
    private Date yvk006;

    /**
     * 行政区划代码
     */
    private String yvk007;

    /**
     * 业务领域ID
     */
    private String yvk008;

    /**
     * 业务领域名称
     */
    private String yvk009;

    /**
     * 发布人名称
     */
    private String yvk010;

    /**
     * 发布人部门
     */
    private String yvk011;

    /**
     * 附件标识
     */
    private String yvk012;

    /**
     * 是否同步12345
     */
    private String yvk013;

    /**
     * 是否同步成功
     */
    private String yvk014;

    /**
     * 同步时间
     */
    private Date yvk016;

    /**
     * 同步人
     */
    private String yvk017;

    /**
     * 更新时间
     */
    private Date yvk018;

    /**
     * 同步结果详情(正常有正常提示 异常有异常原因)
     */
    private String yvk015;

    private static final long serialVersionUID = 1L;
}
