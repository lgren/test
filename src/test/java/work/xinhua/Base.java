package work.xinhua;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class Base {
    private String cookie;
    protected JSONObject runHttp(HttpRequest httpRequest) {
        return JSONUtil.parseObj(httpRequest.execute().body());
    }

    protected HttpRequest post(String url) {
        return HttpUtil.createPost(url)
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Cookie", getToken());
    }

    // 获取登录Token
    protected String getToken() {
        if (cookie != null) {
            return cookie;
        }
        HttpResponse result = HttpUtil.createPost("http://platform-beta.winxuan.com/user/dologin")
                .body("username=zhouyuan1&password=zy970101&url=/main")
                .header("Referer", "http://platform-beta.winxuan.com/")
                .execute();
        if (!result.body().contains("1")) {
            throw new RuntimeException("登录返回值不为1");
        }
        return cookie = result.header("Set-Cookie").split(";")[0];
    }

}
