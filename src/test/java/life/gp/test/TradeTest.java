package life.gp.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import life.gp.DataSourceEnum;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TradeTest {
    private static final String pathname = "/Users/lgren/Project/Java/0My/test/src/test/java/life/gp/test/data.txt";
    private static GPTotal total;
    private static Map<String, GPItem> gpMap;

    @Test
    public void query() {
        System.out.println(total.printInfo());
    }

    @Test
    public void refresh() {
        DataSourceEnum.getCurrent(String.join(",", gpMap.keySet())).forEach((k, v) -> gpMap.computeIfPresent(k, (k1, v1) -> v1.calIncome(v)));
        writeFile(pathname);
        query();
    }

    @Test
    public void buy() {
        buy("sh600868", 3.21, 3000);
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        List<String> fileData = FileUtil.readUtf8Lines(pathname);
        total = new GPTotal(fileData.get(0));
        gpMap = IntStream.range(1, fileData.size()).mapToObj(fileData::get).map(GPItem::new).collect(Collectors.toMap(GPItem::getSymbol, o -> o));
    }

    private void buy(String symbol, double currentVar, int num) {
        if (num % 100 != 0) throw new IllegalArgumentException("数量为100的倍数");
        List<String> fileData = FileUtil.readUtf8Lines(pathname);
        // 交易佣金 万分之3 + 过户费 万分之0.3
        BigDecimal yjRatio = NumberUtil.toBigDecimal(0.00032);
        BigDecimal current = NumberUtil.toBigDecimal(currentVar);


        GPItem lastItem = gpMap.get(symbol);
        GPItem nowItem = GPItem.builder()
                .symbol(symbol)
                .myPrice(current)
                .num(num)
                .total(NumberUtil.mul(current, num))
                .build().calIncome(current);
        gpMap.put(symbol, nowItem.merge(lastItem));
        total = total.moneySub(nowItem.calSpend(yjRatio)).assetSub(nowItem.calYJ(yjRatio));
        // 没钱购买了
        if (NumberUtil.isLess(total.getMoney(), new BigDecimal(0))) {
            throw new RuntimeException("钱不够啦");
        }

        writeFile(pathname);
    }

    private void writeFile(String pathname) {
        List<Object> nowList = new ArrayList<>(gpMap.values());
        nowList.add(0, total);
        FileUtil.writeUtf8Lines(nowList, pathname);
    }
}
