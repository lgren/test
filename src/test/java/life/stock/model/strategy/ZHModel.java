package life.stock.model.strategy;

import com.google.common.base.Objects;
import life.stock.manage.api.BaseItemAbs;
import life.stock.service.IStockApiService;
import life.stock.service.impl.StockApiManage;

import java.util.List;

public enum ZHModel implements IStrategyModel {
    INSTANCE;
    @Override
    public List<BaseItemAbs> chooseStock(IStockApiService stockApi, List<BaseItemAbs> baseList) {
        List<BaseItemAbs> tempList = baseList;
        // 涨幅 3-5%
        tempList = filter(tempList, o1 -> o1.getPercent() >= 3 && o1.getPercent() <= 5);

        // 换手率 5-10%
        tempList = filter(tempList, o1 -> o1.getTurnoverRatio() >= 5 && o1.getTurnoverRatio() <= 10);

        // 流通市值50-200E
        tempList = filter(tempList, (o1 -> o1.getNmcE() >= 50 && o1.getNmcE() <= 200));

        // 量比小于1的全部剔除 此步骤放最后, 因为部分网站获取的数据没有量比, 需要再处理
        if (Objects.equal(stockApi, StockApiManage.XinLang)) {
            tempList.forEach(o -> o.setVolumeRatio(stockApi.getVolumeRatio(o.getSymbol())));
        }
        tempList = filter(tempList, (o -> o.getVolumeRatio() != null && o.getVolumeRatio() >= 1));
        return tempList;
    }
}
