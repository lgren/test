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
public class GPTotal {
    private BigDecimal money;
    private BigDecimal asset;

    public GPTotal(String dataArrStr) {
        String[] dataArr = dataArrStr.split(",");
        this.money = NumberUtil.toBigDecimal(dataArr[0]);
        this.asset = NumberUtil.toBigDecimal(dataArr[1]);
    }

    public GPTotal moneySub(BigDecimal num) {
        money = NumberUtil.sub(money, num);
        return this;
    }

    public GPTotal moneyAdd(BigDecimal num) {
        money = NumberUtil.add(money, num);
        return this;
    }

    public GPTotal assetSub(BigDecimal num) {
        asset = NumberUtil.sub(asset, num);
        return this;
    }

    public GPTotal assetAdd(BigDecimal num) {
        asset = NumberUtil.add(asset, num);
        return this;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0,number,#.####},{1,number,#.####}", money, asset);
    }

    public String printInfo() {
        return MessageFormat.format("现金: {0,number,#.####}\n资产: {1,number,#.####}", money, asset);
    }
}
