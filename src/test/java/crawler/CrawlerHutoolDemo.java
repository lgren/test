package crawler;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * 爬虫DEMO
 * http://rst.qinghai.gov.cn/
 * 用到了 hutool工具类 和 fastjson解析
 * @author lgren
 * @since 2020-06-30 5:45 下午
 */
public class CrawlerHutoolDemo {
    public static final String BASE_URL = "http://rst.qinghai.gov.cn";// 网站基础url
    public static final String BASE_DETAILS_URL = "/qhrstweb/zcfg/zcfginfo.action?rid=";// 进入列表页基础url
    public static final String SOCIAL_JYCY_CODE = "137";// 就业创业
    public static final String SOCIAL_SHBZ_CODE = "235";// 社会保障

    @Test
    public void getResult() {
        QinHaiZcfgPageResultTO resultNew = getResultNew(SOCIAL_SHBZ_CODE, 1);
        System.out.println();
    }

    @Test
    public void use() {
        List<Item> jycyList = getNodeList(SOCIAL_JYCY_CODE);// 就业创业
        List<Item> shbzList = getNodeList(SOCIAL_SHBZ_CODE);// 社会保障
    }

    /**
     * 获取某一类的所有list
     * @param nodeId 类型 例如 {@link CrawlerHutoolDemo#SOCIAL_SHBZ_CODE}为社会保障
     * @return 结果list
     */
    private List<Item> getNodeList(String nodeId) {
        List<Item> list = new LinkedList<>();
        Integer nowPage = 1;
        Integer totalPage;
        do {
            System.out.printf("现在是nodeId(%s)的第(%s)页\n", nodeId, nowPage);
            JSONObject page1 = getResult(nodeId, nowPage++);
            totalPage = page1.getInteger("totalpage");
            resultToList(list, page1);
        } while (totalPage >= nowPage);
        return list;
    }

    /**
     * 获取列表结果
     * @param nodeId 类型 例如 {@link CrawlerHutoolDemo#SOCIAL_SHBZ_CODE}为社会保障
     * @param page 第几页 从1开始
     * @return 返回的json数据
     */
    private JSONObject getResult(String nodeId, int page) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("_", "1593511090757");
        paramMap.put("nodeId", nodeId);// 类型 例如 SOCIAL_SECURITY_CODE为社会保障
        paramMap.put("title", "");
        paramMap.put("sendDate", "");
        paramMap.put("type", "zcfg");
        paramMap.put("fileNo", "");
        paramMap.put("page", page);// 当前页数
        // 请求列表页
        String content = HttpUtil.get(BASE_URL + "/qhrstweb/zcfg/zcfgpages.action", paramMap);
        return JSON.parseObject(content);
    }

    /**
     * 获取列表结果
     * @param nodeId 类型 例如 {@link CrawlerHutoolDemo#SOCIAL_SHBZ_CODE}为社会保障
     * @param page 第几页 从1开始
     * @return 返回的数据
     */
    private QinHaiZcfgPageResultTO getResultNew(String nodeId, int page) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("_", "1593511090757");
        paramMap.put("nodeId", nodeId);// 类型 例如 SOCIAL_SECURITY_CODE为社会保障
        paramMap.put("title", "");
        paramMap.put("sendDate", "");
        paramMap.put("type", "zcfg");
        paramMap.put("fileNo", "");
        paramMap.put("page", page);// 当前页数
        // 请求列表页
        String content = HttpUtil.get(BASE_URL + "/qhrstweb/zcfg/zcfgpages.action", paramMap);
        return JSON.parseObject(content, QinHaiZcfgPageResultTO.class);
    }

    /**
     * 将列表结果中的数据转换成{@link Item}并写入数据list中
     * @param list   写入的list
     * @param result 列表结果
     */
    private void resultToList(List<Item> list, JSONObject result) {
        // 数据列表
        JSONArray zcfgs = result.getJSONArray("zcfgs");
        for (int i = 0; i < zcfgs.size(); i++) {
            Optional.ofNullable(zcfgs.getJSONObject(i))
                    .map(o -> new Item(o.getString("rid"), o.getString("title")))
                    .ifPresent(list::add);
        }
    }

    class Item {
        private String rid;
        private String title;
        private String url;

        public Item(String rid, String title) {
            this.rid = Objects.requireNonNull(rid);
            this.title = Objects.requireNonNull(title);
            this.url = CrawlerHutoolDemo.BASE_URL + CrawlerHutoolDemo.BASE_DETAILS_URL + rid;
        }

        public String getRid() {
            return rid;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"rid\":\"" + rid + '\"' +
                    ",\"title\":\"" + title + '\"' +
                    ",\"url\":\"" + url + '\"' +
                    "}";
        }
    }
}
