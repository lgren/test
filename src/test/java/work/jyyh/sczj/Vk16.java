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
public class Vk16 implements Serializable {
    /**
     * 知识业务领域ID
     */
    private String yvk160;

    /**
     * 知识ID
     */
    private String yvk001;

    /**
     * 业务领域ID
     */
    private String yve901;

    /**
     * 经办人ID
     */
    private String aae011;

    /**
     * 经办时间
     */
    private Date aae036;

    /**
     * 经办机构
     */
    private String aae017;

    /**
     * 经办人名称
     */
    private String aae012;

    /**
     * 有效标识
     */
    private String aae100;

    /**
     * 变更人ID
     */
    private String aae013;

    /**
     * 变更人名称
     */
    private String aae014;

    /**
     * 变更人经办机构
     */
    private String aae019;

    /**
     * 变更时间
     */
    private Date aae037;

    private static final long serialVersionUID = 1L;
}
