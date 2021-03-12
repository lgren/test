package hutool.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO
 * @author lgren
 * @since 2020-11-24 11:11 上午
 */
@Data
public class TradeInvoiceDO {
    private Long id;
    private String bank;
    private String creatorName;
    private LocalDateTime createTime;
}
