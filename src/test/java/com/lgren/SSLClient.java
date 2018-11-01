package com.lgren;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * [Project]:whymoy  <br/>
 * [Email]:moy25@foxmail.com  <br/>
 * [Date]:2018/3/14  <br/>
 * [Description]:  <br/>
 *
 * @author YeXiangYang
 */
public class SSLClient {
    public static void main(String[] args) {
        //测试公司的API接口，将json当做一个字符串传入httppost的请求体
        String result = null;
        HttpClient client = HttpClients.createDefault();
        URIBuilder builder = new URIBuilder();
        URI uri = null;
        try {
            uri = builder.setScheme("https")
                    .setHost("api.weixin.qq.com")
                    .setPath("/cgi-bin/material/batchget_material?access_token=2")
                    .build();

            HttpPost post = new HttpPost(uri);
            //设置请求头
            post.setHeader("Content-Type", "application/json");
            Map<String, Object> maps = new HashMap<>();
            maps.put("getType", "image");
            maps.put("offset", "0");
            maps.put("count", "2");
            //将封装好的map转换成json格式
            String jsonStr = JSON.toJSONString(maps);
            String body = jsonStr;
            //设置请求体
            post.setEntity(new StringEntity(body));
            //获取返回信息
            HttpResponse response = client.execute(post);
            result = response.toString();
        } catch (Exception e) {
            System.out.println("接口请求失败" + e.getStackTrace());
        }
        System.out.println(result);
    }

    public static String sendHttpPost(String url, String body) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(body));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode() + "\n");
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseContent);

        response.close();
        httpClient.close();
        return responseContent;
    }

//    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        Map<String,Object> maps = new HashMap<>();
//        maps.put("getType", "image");
//        maps.put("offset", "0");
//        maps.put("count", "2");
//        //将封装好的map转换成json格式
//        String jsonStr = JSON.toJSONString(maps);
//
//        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
//        try (CloseableHttpClient httpClient = createHttpClient()) {
//            HttpPost httpPost = new HttpPost(url);
//            try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
//                HttpEntity entity = httpResponse.getEntity();
//                String result = EntityUtils.toString(entity);
//                EntityUtils.consume(entity);
//
//                System.out.printf(result);
//            }
//        }
//    }
//
//
//    private static CloseableHttpClient createHttpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//        SSLContext sslcontext = SSLContexts.custom()
//                .loadTrustMaterial(null, (chain, authType) -> true)
//                .build();
//        SSLConnectionSocketFactory sslSf = new SSLConnectionSocketFactory(sslcontext, null, null, new NoopHostnameVerifier());
//        return HttpClients.custom().setSSLSocketFactory(sslSf).build();
//    }
}