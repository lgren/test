package life.stock.dao.impl;

import cn.hutool.core.io.FileUtil;
import life.stock.config.ConfigData;
import life.stock.dao.IStockDao;
import life.stock.entity.MyAccountPO;
import life.stock.entity.MyStockPO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.IntStream;


public enum StockTxtDaoImpl implements IStockDao {
    INSTANCE;
    @Getter@Setter@Accessors(chain = true)
    private String pathnameFormat = ConfigData.ACCOUNT_DATA_PATHNAME;


    @Override
    public MyAccountPO addAccount(long id, String name, double money) {
        MyAccountPO account = MyAccountPO.create(id, name, money);
        FileUtil.writeUtf8String(account.toString(), String.format(pathnameFormat, id));
        return account;
    }

    @Override
    public MyAccountPO getAccount(long id) {
        List<String> fileData = FileUtil.readUtf8Lines(String.format(pathnameFormat, id));
        LinkedHashMap<String, MyStockPO> stockMap = IntStream.range(1, fileData.size()).mapToObj(fileData::get).map(MyStockPO::new)
                .collect(LinkedHashMap::new, (r, o) -> r.put(o.getSymbol(), o), LinkedHashMap::putAll);
        return new MyAccountPO(fileData.get(0)).setMyStockMap(stockMap);
    }

    @Override
    public MyAccountPO updateAccount(MyAccountPO account) {
        List<Object> nowList = new ArrayList<>(account.getMyStockMap().values());
        nowList.add(0, account);
        FileUtil.writeUtf8Lines(nowList, String.format(pathnameFormat, account.getId()));
        return account;
    }
}
