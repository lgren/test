package work.jyyh;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * @author lgren
 * @since 2020-10-28 11:57 上午
 */
public class JyyhTest {
    private List<HttpCookie> cookieList;
    private String token;

    @Test
    public void login() {
        HttpResponse response = HttpUtil.createPost("http://127.0.0.1:8081/zsk/login")
                .form(MapUtil.builder(new HashMap<String, Object>())
                        .put("username", "developer")
                        .put("password", "640aa7edf8c517abafb292aeba046a983ba2a54dce6099e95ed9e8f4fcd7830a897c84600bd2b5fc8ad60072c2fc70d9dead9d097a861ccf942dcbca462cfbecd179209ab8847630a052ccdd381d598eb6e6d75115557bdc9999a5642cfad24b7b66b9084f2ca00dd55a52e572678f0fd27d6294f33825a273defdcbf4cdbea0")
                        .build())
                .execute();
        cookieList = response.getCookies();
        token = JSONUtil.parseObj(response.body())
                .getJSONObject("data")
                .getStr("TA-RJTOKEN");

        System.out.println(cookieList);
        System.out.println(token);
    }

    @Test
    public void post() {
        login();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ignored) {
        }
        HttpRequest post = HttpUtil.createPost("http://127.0.0.1:8081/zsk/answer/sensitive/query");
        HttpResponse response = post
                // .cookie(ArrayUtil.toArray(cookieList, HttpCookie.class))
                // .contentType("application/json;charset=UTF-8")
                .execute();
        System.out.println(response.body());
    }

    @Test
    public void test1() throws IOException, InterruptedException {
        // Process exec = Runtime.getRuntime().exec("/Users/lgren/开发工具/Tomcat/tomcat-8.5.39-gams/bin/startup.sh");
        // Process exec = Runtime.getRuntime().exec("/Users/lgren/开发工具/Tomcat/tomcat-8.5.39-gams/bin/shutdown.sh");
        // Process exec = Runtime.getRuntime().exec("open /Users/lgren/Desktop/截图");
        // Process exec = Runtime.getRuntime().exec("curl http://localhost:8080/gams/login.jsp");
        // Process exec = Runtime.getRuntime().exec("curl https://blog.csdn.net/jimzhai/article/details/7864806");
        Process exec = Runtime.getRuntime().exec("ping qq.com");

        List<String> errorList = new LinkedList<>();
        List<String> resultList = new LinkedList<>();
        new Thread(() -> handleIn("error-> ", exec.getErrorStream(), errorList)).start();
        new Thread(() -> handleIn("result-> ", exec.getInputStream(), resultList)).start();


        exec.waitFor(5L, TimeUnit.SECONDS);
        System.out.println(errorList);
        System.out.println(resultList);
        exec.destroy();
    }

    private static Collection<String> handleIn(String prefix, InputStream in, Collection<String> coll) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (coll != null) {
                    coll.add(line);
                }
                System.out.println(prefix + line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coll;
    }
}
