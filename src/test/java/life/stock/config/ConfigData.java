package life.stock.config;

import cn.hutool.core.util.NumberUtil;
import life.stock.dao.IStockDao;
import life.stock.dao.impl.StockTxtDaoImpl;
import life.stock.model.strategy.IStrategyModel;
import life.stock.model.strategy.ZHModel;
import life.stock.service.IStockApiService;
import life.stock.service.impl.StockApiManage;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public interface ConfigData {
    String ACCOUNT_DATA_PATHNAME = "/Users/lgren/Project/Java/0My/test/src/test/java/life/stock/data/%s.txt";
    String CHOOSE_STOCK_VIEW__PATHNAME_FORMAT = "/Users/lgren/Project/Java/0My/test/src/test/java/life/stock/data/choose/%s.html";
    String QUERY_STOCK_VIEW_PATHNAME_FORMAT = "/Users/lgren/Project/Java/0My/test/src/test/java/life/stock/data/query/%s.html";

    IStockDao STOCK_DAO = StockTxtDaoImpl.INSTANCE;
    IStockApiService STOCK_API_MANAGE = StockApiManage.XueQiu;
    IStrategyModel STRATEGY_SERVICE = ZHModel.INSTANCE;

    BigDecimal BUY_BROKERAGE_RATIO = NumberUtil.toBigDecimal(0.00032);

    DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");

}
