package com.lgren.middleware.onlyoffice;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.ftp.SimpleFtpServer;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.SimpleServer;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConvertTest {
    @Test
    public void test1() {
        // http://192.168.1.103:9000/ConvertService.ashx
        String result = HttpUtil.createPost("http://192.168.1.103:9000/ConvertService.ashx")
                .body(JSONUtil.toJsonStr(MapUtil.<String, Object>builder()
                        .put("async", false)
                        // .put("url", "http://172.20.23.199:9001/cloudstore/ucm/ucmAction!getFile.do?loginid=developer&addrcode=9999&busitype=FILE&accesskey=NcI7Hnb0YuAD9pmOfz12epOtmyrzPndyqfChPG0&fromprovince=0&sysid=dagims&crosscity=0&realid=cb7b12824cb148eebb687fce71a488ee&fileid=62204d70be784ba69c16fe848ac1c003")
                        .put("url", "http://localhost:8019/file?pathname=/Users/lgren/Work/YH/%E8%A7%84%E8%8C%83/java%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83.docx")
                        // .put("url", "http://localhost:8019/file?pathname=/Users/lgren/Work/YH/规范/java开发规范.docx")
                        .put("filetype", "docx")
                        .put("key", "5")
                        .put("outputtype", "pdf")
                        .put("title", "test.docx")
                        // .put("token", )
                        .build()), "application/json;charset=UTF-8")
                .execute().body();
        JSONObject resultObj = JSONUtil.parseObj(result);
        System.out.println(resultObj);

        // 62204d70be784ba69c16fe848ac1c003
    }

    public static void main(String[] args) {
        // http://localhost:8019/file?pathname=成都市知识库-详细设计说明书.docx
        new SimpleServer(8019)
                .addAction("/file", (request, response) -> {
                    String pathname = request.getParam("pathname");
                    FileInputStream in = new FileInputStream(pathname);

                    // 2.设置文件下载头
                    response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(FileNameUtil.getName(pathname), StandardCharsets.UTF_8.toString()));
                    // 3.设置字符编码
                    response.setCharset(StandardCharsets.UTF_8);
                    // 4.设置文件ContentType类型，这样设置，会自动判断文件类型
                    response.setContentType("multipart/form-data");
                    IoUtil.copy(in, response.getOut(), 4096);
                    // response.write(in);
                })
                .start();
    }
}
