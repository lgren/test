package life.stock.dao;

import life.stock.config.ConfigData;
import life.stock.entity.MyAccountPO;

import java.math.BigDecimal;

public interface IStockDao extends ConfigData {
    MyAccountPO addAccount(long id, String name, double money);

    MyAccountPO getAccount(long id);

    MyAccountPO updateAccount(MyAccountPO account);
}
