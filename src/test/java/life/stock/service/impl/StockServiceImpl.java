package life.stock.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.extra.mail.MailUtil;
import life.stock.config.ConfigData;
import life.stock.dao.IStockDao;
import life.stock.entity.IGenerateHtml;
import life.stock.entity.MyAccountPO;
import life.stock.manage.api.BaseItemAbs;
import life.stock.model.strategy.IStrategyModel;
import life.stock.service.IStockApiService;
import life.stock.service.IStockService;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现参考
 * 湖南有一位炒股奇才，每天只在尾盘30分钟选股，短短三年时间就实现了财富自由！ - 骑士的文章 - 知乎
 * https://zhuanlan.zhihu.com/p/412959317
 */
public enum StockServiceImpl implements IStockService {
    INSTANCE;
    @Getter @Setter @Accessors(chain = true)
    private String chooseStockPathnameFormat = ConfigData.CHOOSE_STOCK_VIEW__PATHNAME_FORMAT;
    @Getter @Setter @Accessors(chain = true)
    private String queryStockViewPathnameFormat = ConfigData.QUERY_STOCK_VIEW_PATHNAME_FORMAT;

    private final IStockDao stockDao = ConfigData.STOCK_DAO;
    private final IStockApiService stockApiService = ConfigData.STOCK_API_MANAGE;


    @Override
    public MyAccountPO addAccount(long id, String name, double money) {
        return stockDao.addAccount(id, name, money);
    }

    @Override
    public MyAccountPO getAccount(Long id) {
        return stockDao.getAccount(id);
    }

    @Override
    public MyAccountPO refreshAccount(Long id) {
        MyAccountPO account = getAccount(id);// 获取账号
        account.refreshNew(stockApiService::mapStockNew);// 通过id获取当前最新信息
        return stockDao.updateAccount(account);// 更新数据
    }

    @Override
    public MyAccountPO previewHoldStock(Long id, boolean isOpenView, boolean isSendMail) {
        MyAccountPO account = getAccount(id);
        String filePathname = String.format(queryStockViewPathnameFormat, id.toString());
        String content = generateHtml(filePathname, account.getMyStockMap().values());
        if (isOpenView) {
            open(filePathname);
        }
        if (isSendMail) {
            MailUtil.send("625552409@qq.com", id.toString(), content, true);
        }
        return account;
    }

    @Override
    public MyAccountPO buy(Long id, String symbol, int num, double current) {
        return stockDao.updateAccount(getAccount(id).buy(symbol, NumberUtil.toBigDecimal(current), num, BUY_BROKERAGE_RATIO));
    }

    @Override
    public MyAccountPO buy(Long id, String symbol, int num) {
        return stockDao.updateAccount(getAccount(id).buy(symbol, stockApiService.getStockNew(symbol).getCurrent(), num, BUY_BROKERAGE_RATIO));
    }

    @Override
    public List<BaseItemAbs> runStrategy(IStrategyModel strategyService, boolean isOpenView, boolean isSendMail) {
        // 获取策略后的结果
        List<BaseItemAbs> resultList = strategyService.chooseStock(stockApiService, stockApiService.getList("sha,sza"));

        String nowStr = DTF.format(LocalDateTime.now());
        String filePathname = String.format(chooseStockPathnameFormat, nowStr);
        String content = generateHtml(filePathname, resultList);
        if (isOpenView) {
            open(filePathname);
        }
        if (isSendMail) {
            MailUtil.send("625552409@qq.com", nowStr, content, true);
        }
        return resultList;
    }

    private String generateHtml(String filePathname, Collection<? extends IGenerateHtml<? extends Number>> list) {
        String content = generateHtml(list);
        FileUtil.writeUtf8String(content, filePathname);
        return content;
    }

    private String generateHtml(Collection<? extends IGenerateHtml<? extends Number>> stockList) {
        String html = "<!DOCTYPE html><html lang=\"cn\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>{0}</title></head><body>{1}</body></html>";
        String item = "<div style=\"display: flex;\"><div style=\"width: 100px;\"><div>{1}</div><div>{0}</div><div>{2,number,#.####}</div></div><div><img src=\"http://image.sinajs.cn/newchart/min/n/{0}.gif\" alt=\"时\" style=\"width: 50%;\"><img src=\"http://image.sinajs.cn/newchart/daily/n/{0}.gif\" alt=\"日K\" style=\"width: 50%;\"></div></div>";
        return MessageFormat.format(html, "K", stockList.stream().map(o -> MessageFormat.format(item, o.getSymbol().toLowerCase(), o.getName(), o.getCurrent())).collect(Collectors.joining()));
    }

    @SneakyThrows
    private void open(String pathname) {
        String osName = System.getProperty("os.name");
        if (osName != null) {
            if (osName.contains("Mac")) {
                Runtime.getRuntime().exec("open " + pathname);
            } else if (osName.contains("Windows")) {
                Runtime.getRuntime().exec("cmd /c start " + pathname);
            } else {
                System.out.println("文件输出目录:" + pathname);
            }
        }
    }
}
