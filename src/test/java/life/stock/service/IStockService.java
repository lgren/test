package life.stock.service;

import life.stock.config.ConfigData;
import life.stock.entity.MyAccountPO;
import life.stock.manage.api.BaseItemAbs;
import life.stock.model.strategy.IStrategyModel;

import java.util.List;

public interface IStockService extends ConfigData {
    List<BaseItemAbs> runStrategy(IStrategyModel strategyService, boolean isOpenView, boolean isSendMail);

    MyAccountPO addAccount(long id, String name, double money);

    MyAccountPO getAccount(Long id);

    MyAccountPO refreshAccount(Long id);

    MyAccountPO previewHoldStock(Long id, boolean isOpenView, boolean isSendMail);

    MyAccountPO buy(Long id, String symbol, int num, double currentVar);


    MyAccountPO buy(Long id, String symbol, int num);
}
