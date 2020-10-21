package hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.webservice.SoapClient;
import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathConstants;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * TODO
 * @author lgren
 * @since 2020-09-08 10:40 上午
 */
public class HutoolNetworkTest {
    @Test
    public void httpGet() {
        String result = HttpUtil.get("http://192.168.8.137:9001/iApp2/answer/api/question.do?question=哈哈哈&questionFlag=2&questionLevel=1&channel=3&systemId=100000000000000006");
        System.out.println(result);
    }

    @Test
    public void 爬虫() {
        // HttpUtil.createGet("").executeAsync().body();
        // 请求列表页
        String listContent = HttpUtil.get("https://www.oschina.net/action/ajax/get_more_news_list?newsType=&p=2");
        //使用正则获取所有标题
        List<String> titles = ReUtil.findAll("<span class=\"text-ellipsis\">(.*?)</span>", listContent, 1);
        for (String title : titles) {
            //打印标题
            Console.log(title);
        }
    }

    @Test
    public void 爬虫1() {
        // 请求列表页
        String listContent = HttpUtil.get("http://rst.qinghai.gov.cn/");
        System.out.println(listContent);
    }

    @Test
    public void httpPost() {
        //POST请求
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("question", "社保");
        paramMap.put("questionFlag", "2");
        paramMap.put("questionLevel", "1");
        paramMap.put("channel", "3");
        paramMap.put("systemId", "100000000000000006");

        String result = HttpUtil.post("http://192.168.8.137:9001/iApp2/answer/api/question.do", paramMap);
        System.out.println(result);
    }

    @Test
    public void download() {
        // HttpUtil.downloadFile("http://game.ali.cdn.ledu.com/rxsg/1.16.0/res/things/armor/8101408.png",// 装备地址
        //         "/Users/lgren/Downloads/0test");
        HttpUtil.downloadFile("http://game.ali.cdn.ledu.com/rxsg/1.20.0/res/images/hero/face/hero_boy_12.jpg",// 英雄地址
                "/Users/lgren/Downloads/0test");
        // HttpUtil.downloadFile("http://game.ali.cdn.ledu.com/rxsg/1.20.0/res/face/player_male_3.jpg",// 登陆界面玩家地址 female
        //         "/Users/lgren/Downloads/0test");

    }

    @Test
    public void httpStatus() {
        System.out.println(HttpStatus.HTTP_NOT_FOUND);
    }

    @Test
    public void ftp() throws IOException {
        //匿名登录（无需帐号密码的FTP服务器）
        Ftp ftp = new Ftp("172.0.0.1");
        //进入远程目录
        ftp.cd("/opt/upload");
        //上传本地文件
        ftp.upload("/opt/upload", FileUtil.file("e:/test.jpg"));
        //下载远程文件
        ftp.download("/opt/upload", "test.jpg", FileUtil.file("e:/test2.jpg"));

        //关闭连接
        ftp.close();
    }

    public static void main(String[] args) {
        // https://www.hutool.cn/docs/#/http/Server/%E7%AE%80%E6%98%93Http%E6%9C%8D%E5%8A%A1%E5%99%A8-SimpleServer
        HttpUtil.createServer(8888)// http://localhost:8888/
                .addAction("/", (req, res) -> res.write("Hello Hutool Server"))
                .start();
    }
}
