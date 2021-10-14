package life.gp.test;

import cn.hutool.core.util.NumberUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.text.MessageFormat;

@Data
@SuperBuilder
@NoArgsConstructor
public class GPItem {
    private String symbol;
    private BigDecimal myPrice;
    private int num;
    private BigDecimal total;

    private BigDecimal current;
    private BigDecimal income;
    private BigDecimal incomeRatio;


    public GPItem(String dataArrStr) {
        String[] dataArr = dataArrStr.split(",|\\|\\|");
        this.symbol = dataArr[0];
        this.myPrice = NumberUtil.toBigDecimal(dataArr[1]);
        this.num = Integer.parseInt(dataArr[2]);
        this.total = NumberUtil.toBigDecimal(dataArr[3]);

        this.current = NumberUtil.toBigDecimal(dataArr[4]);
        this.income = NumberUtil.toBigDecimal(dataArr[5]);
        this.incomeRatio = NumberUtil.toBigDecimal(dataArr[6]);
    }

    public GPItem merge(GPItem item) {
        if (item != null) {
            return GPItem.builder()
                    .symbol(symbol)
                    .myPrice(NumberUtil.div(total, num))
                    .num(num + item.getNum())
                    .total(NumberUtil.add(total, item.getTotal()))
                    .build().calIncome(current);
        }
        return this;
    }

    public BigDecimal calYJ(BigDecimal yjRatio) {
        return NumberUtil.mul(total, yjRatio);
    }

    public BigDecimal calSpend(BigDecimal yjRatio) {
        return NumberUtil.mul(total, NumberUtil.add(yjRatio, 1));
    }

    public GPItem calIncome(BigDecimal current) {
        this.current = current;
        incomeRatio = NumberUtil.div(NumberUtil.sub(current, myPrice), myPrice);
        income = NumberUtil.mul(total, incomeRatio);
        return this;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0},{1,number,#.####},{2,number,#},{3,number,#.####}||{4,number,#.####},{5,number,#},{6,number,#.####}", symbol, myPrice, num, total, current, income, incomeRatio);
    }
}
