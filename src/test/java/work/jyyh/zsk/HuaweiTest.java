package work.jyyh.zsk;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.text.MessageFormat;

public class HuaweiTest {
    @Test
    public void name() {
        String result = HttpUtil.post("http://103.203.219.157/cns-sccthj-job-test/rest/oauth2/token", "client_id=admin&client_secret=admin&grant_type=client_credentials");
        JSONObject resultJSON = JSONUtil.parseObj(result);
        String accessToken = resultJSON.getJSONObject("custom").getStr("access_token");

        // HttpRequest sendKnowledge = HttpUtil.createPost("http://103.203.219.157/cns-scxthj-job-test/rest/get_knowledge_info_send");
        HttpRequest sendKnowledge = HttpUtil.createPost("http://103.203.219.157/cns-sccthj-rest-test/rest/get_knowledge_info_send");
        sendKnowledge.header("Content-Type", "application/json");
        sendKnowledge.header("Authorization", MessageFormat.format("Bearer {0}", accessToken));
        sendKnowledge.body(JSONUtil.toJsonStr(MapUtil.<String, Object>builder()
                .put("token", MapUtil.builder()
                        .put("Cleint_Id", "admin")
                        .put("Cleint_secret", "admin")
                        .build())
                .put("paras", MapUtil.builder()
                        .put("CATEGORYCODE", "admin")// 知识类别code
                        .put("CATEGORYNAME", "知识类别名称")// 知识类别名称
                        .put("PUBLISHPERSON", "test")// 发布人
                        .put("OUNAME", "人社信息中心测试")// 所属部门名称
                        .put("KNAME", "知识名称知识名称")// 知识名称
                        .put("KCONTENT", "知识内容知识内容")// 知识内容
                        .put("CREATDATE", "2021-08-02")// 发布日期
                        .put("BEGINDATE", "2021-08-02")// 生效时间
                        .put("DISABLEDATE", "2029-08-06")// 失效时间
                        .put("UID", "6889900")// 知识唯一标识
                        .put("CLIENG_GUID", "1")// 材料标识
                        .put("AREA_CODE", "510000")//行政区划代码
                        .build())
                .build()));

        String sendKnowledgeResult = sendKnowledge.execute().body();


        System.out.println(sendKnowledgeResult);


    }
}
