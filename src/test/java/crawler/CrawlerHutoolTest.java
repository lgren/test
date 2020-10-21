package crawler;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

/**
 * 爬虫DEMO
 * @author lgren
 * @since 2020-06-30 5:45 下午
 */
public class CrawlerHutoolTest {
    @Test
    public void crawler() {
        // 请求列表页
        String listContent = HttpUtil.get("http://rst.qinghai.gov.cn/qhrstweb/zcfg/zcfgpages.action?_=1593511090757&nodeId=137&title=&sendDate=&page=1&type=zcfg&fileNo=");
        JSONObject result = JSON.parseObject(listContent);
        JSONArray list = result.getJSONArray("zcfgs");
        System.out.println(listContent);
    }
    @Test
    public void crawlerJSON() {
        // 请求列表页
        String listContent = HttpUtil.get("http://rst.qinghai.gov.cn/qhrstweb/zcfg/zcfgpages.action?_=1593511219743&nodeId=235&title=&sendDate=&page=1&type=zcfg&fileNo=");
        System.out.println(listContent);
    }

    @Test
    public void crawler1() throws IOException {
        // Connection.Response execute = Jsoup.connect("http://rst.qinghai.gov.cn/").ignoreContentType(true).execute();
        Connection.Response execute = Jsoup.connect("http://rst.qinghai.gov.cn/").execute();
        Document document = execute.parse();
        System.out.println();

        // execute.charset("gb2312");
        // System.out.println(execute.body());
    }
}
