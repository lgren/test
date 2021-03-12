package work.jyyh;

import cn.hutool.core.util.XmlUtil;
import org.junit.Test;
import org.w3c.dom.Document;

import java.util.Map;

/**
 * TODO
 * @author lgren
 * @since 2021-03-10 9:57 上午
 */
public class CommonTest {
    @Test
    public void name() {
        String xmlStr = "<data>" +
                "<respMsg>调用档案业务信息接口成功</respMsg>" +
                "<yuf003>0199990002021Ygl01000002</yuf003>" +
                "<yaz001>202002120001</yaz001>" +
                "<respCode>" +
                "<![CDATA[" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><input><yug643>00</yug643><yae049>A01061</yae049><yue001>01</yue001><aae011>175028</aae011><yab003>9999</yab003><yuf00z>9999</yuf00z><yaz001>202002120001</yaz001><aae017>135611</aae017><yuf00k>1</yuf00k><person></person><material><yue046>1</yue046><_row_>0</_row_><__id___>0</__id___><yue031>Else</yue031><yue030>other</yue030><yue041>1</yue041></material></input>]]>" +
                "</respCode></data>";
        // String xmlStr = "<data>" +
        // "<respMsg>调用档案业务信息接口成功</respMsg>" +
        // "<yuf003>0199990002021Ygl01000002</yuf003>" +
        // "<yaz001>202002120001</yaz001>" +
        // "<respCode>SUCCESS</respCode>" +
        // "</data>";
        Map<String, Object> map = XmlUtil.xmlToMap(xmlStr);
        Map<String, Object> dataMap = XmlUtil.xmlToMap(String.valueOf(map.get("respCode")));
        System.out.println();
    }
}
