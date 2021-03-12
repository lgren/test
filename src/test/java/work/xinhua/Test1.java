package work.xinhua;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Test1 extends Base {
    private Snowflake snowflake = IdUtil.getSnowflake(0, 0);

    @Test
    public void 压力测试Plus() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(1000);
        long begin = System.currentTimeMillis();
        IntStream.range(0, 1000).forEach(i -> exe.submit(this::下单));
        exe.shutdown();
        exe.awaitTermination(1, TimeUnit.HOURS);
        System.out.println(System.currentTimeMillis() - begin);
    }

    @Test
    public void 下单() {
        int shopId = 110;// 商铺ID
        int deliveryFee = 6;// 运费
        List<String> productList = new ArrayList<>();// 商品
        productList.add("1200197213,1");// 格式为 "商品ID,数量"

        HttpRequest post = post("http://platform-beta.winxuan.com/trade/create");

        JSONObject paramsJSON = JSONUtil.parseObj("{\"shopId\":110" +
                ",\"needTransfer\":true,\"tradePayment\":\"PP_PLAT_ONLINE\",\"tradePayPath\":\"PW_PLATFORM\",\"sellType\":\"NORMAL_SELL\",\"tradeConsignee\":{\"customerId\":1700555954,\"consignee\":\"国图上线测试8096\",\"phone\":null,\"mobile\":\"15688897777\",\"email\":\"qqqqqqqqq@qq.com\",\"country\":23,\"province\":175,\"city\":1123,\"district\":1697,\"town\":80202,\"address\":\"测试地址也是地址呀\",\"zipCode\":\"658989\",\"remark\":\"可以的哈哈哈哒哒哒哒哒哒\",\"townId\":80202},\"tradeDelivery\":{\"deliveryType\":\"EXPRESS\",\"maxDeliveryTime\":1632326400000,\"stockOutOption\":\"UTMOST_DELIVERY\",\"wayBill\":\"DEFAULT_TEMPLATE\",\"dc\":\"\",\"deliveryPlatform\":\"CAI_NIAO\"},\"deliveryFee\":\"0\",\"outerTrade\":1603424032,\"customerId\":1700555954,\"tradeItemList\":[],\"maxPayTime\":1630498898000}");
        paramsJSON.set("shopId", shopId);// 商铺ID
        paramsJSON.set("outerTrade", outerTrade());// 外部交易单号
        paramsJSON.set("deliveryFee", deliveryFee);// 运费

        JSONArray tradeItemList = paramsJSON.getJSONArray("tradeItemList");
        for (String productStr : productList) {
            String[] productArr = productStr.split(",");
            tradeItemList.addAll(addProduct(shopId, productArr[0].trim(), Integer.parseInt(productArr[1].trim())));
        }

        post.body(paramsJSON.toString());
        JSONObject result = runHttp(post);
        System.out.println(result);
        System.out.println(result.getJSONObject("result").getStr("tradeId"));
    }

    // 新增商品
    private JSONArray addProduct(int shopId, String productSale, int purchaseQuantity) {
        // 创建一个test
        HttpRequest post = post("http://platform-beta.winxuan.com/trade/product/add");
        JSONObject body = JSONUtil.parseObj("{\"shopId\":110,\"productSale\":\"1200197213\",\"outerItemId\":\"\",\"checkStock\":false,\"dc\":\"\"}");
        body.set("productSale", productSale);// 商品编码
        body.set("shopId", shopId);// 店铺
        body.set("dc", "");// 仓库
        post.body(body.toString());
        JSONObject result = runHttp(post);
        JSONArray shopList = result.getJSONArray("result");

        DecimalFormat df2 = new DecimalFormat("#.00");

        JSONArray tradeItemList = new JSONArray();
        for (Object shop : shopList) {
            JSONObject shopJSONNew = new JSONObject();
            tradeItemList.add(shopJSONNew);
            JSONObject shopJSON = (JSONObject) shop;
            shopJSONNew.set("productSale", shopJSON.get("productSale"));
            shopJSONNew.set("outerProduct", shopJSON.get("outerItemId"));
            shopJSONNew.set("listPrice", shopJSON.get("listPrice"));
            shopJSONNew.set("purchaseQuantity", purchaseQuantity);
            shopJSONNew.set("settlePrice", shopJSON.get("settlePrice"));
            shopJSONNew.set("salePrice", df2.format(shopJSON.getBigDecimal("listPrice").multiply(shopJSON.getBigDecimal("discount"))));
        }
        return tradeItemList;
    }


    // 获取外部交易单号
    private synchronized String outerTrade() {
        return "test" + snowflake.nextId();
    }
}
