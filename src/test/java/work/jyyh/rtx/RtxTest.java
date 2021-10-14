package work.jyyh.rtx;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RtxTest {
    @SneakyThrows
    @Test
    public void name() {
        String sendImg = " /SendNotify.cgi? ";  //  RTX发送消息接口
        String host = "118.112.188.111";  //  RTX服务器地址
        String getSessionkey = " /getsessionkey.cgi? ";  //  RTX获取会话接口
        // int port = 8012;  //  RTX服务器监听端口
        int port = 8000;  //  RTX服务器监听端口
        String content = "测试一下";  //  内容
        StringBuilder sendMsgParams = new StringBuilder(sendImg);
        sendMsgParams.append("&receiver=").append(String.join(",", "luxun"));
        sendMsgParams.append("&sender=").append("luxun");
        sendMsgParams.append("&msg=").append(content);
        // sendMsgParams.append(" &msg= " + new String(content.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
        URL url = new URL("HTTP", host, port, sendMsgParams.toString());
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String s;
        while ((s = reader.readLine()) != null) {
            result.append(s);
        }

        System.out.println(result);

        String ret = httpConnection.getHeaderField(3);
        System.out.println(ret);
    }

    @Test
    public void name2() {
        HttpRequest post = HttpUtil.createPost(String.format("http://118.112.188.111:8000/%s", "SendNotify.cgi"))
                .form(MapUtil.<String, Object>builder()
                        .put("sender", "luxun")
                        .put("receiver", String.join(",", "luxun"))
                        .put("msg", "java调用测试")
                        .build());

        System.out.println(post.execute().body());

    }

    @Test
    public void name3() {
        SendRTXNotify("luxun", "标题", "内容", null, null);
    }


    @SneakyThrows
    public int SendRTXNotify(String receivers, String title, String msg, String type, String delayTime) {
        int iRet = -1;
        String rtxHost = "118.112.188.111";
        StringBuilder strURL = new StringBuilder("http://" + rtxHost + ":8000/sendnotify.cgi");//?msg=hello&receiver=admin";
        strURL.append("?msg=").append(URLEncoder.encode(msg, "gb2312"))
                .append("&sender=").append(URLEncoder.encode("luxun", "gb2312"))
                .append("&receiver=").append(URLEncoder.encode(receivers, "gb2312"))
                .append("&title=").append(URLEncoder.encode(title, "gb2312"));
        java.net.URL url = new URL(strURL.toString());
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String s;
        while ((s = reader.readLine()) != null) {
            result.append(s);
        }

        if (result.indexOf("操作成功") > -1) iRet = 0;
        System.out.println(result);
        return iRet;

    }

}
