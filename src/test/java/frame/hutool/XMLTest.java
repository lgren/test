package frame.hutool;

import cn.hutool.core.util.XmlUtil;
import org.junit.Test;

import java.util.Map;

public class XMLTest {
    @Test
    public void name1() {
        Map<String, Object> map = XmlUtil.xmlToMap("<?xml version=\"1.0\" encoding=\"GBK\"?><result><code>1</code><message></message><output><status>10</status><message>null</message><data><问答><row><name>高龄津贴年龄限制</name><type>2</type><value>分为60-69、70-79、80+三档，不同地区政策有所差异</value></row></问答><政策文件><row><name>攀仁府办[2012]63号关于印发《攀枝花市仁和区80至89周岁老年人高龄生活津贴及90周岁以上老年人长寿生活补贴发放管理实施办法》的通知</name><type>3</type><value>10287</value></row><row><name>盐边县人民政府办公室关于建立80-89岁高龄老人生活津贴制度的通知</name><type>3</type><value>10295</value></row><row><name>米府办发〔2019〕2号高龄津贴发放管理办法</name><type>3</type><value>10444</value></row></政策文件></data></output></result>");
        System.out.println();
    }
}
/*
<?xml version=\"1.0\" encoding=\"GBK\"?>
<result>
<code>1</code><message></message><output><status>10</status><message>null</message>
<data>
    <问答>
        <row>
            <name>高龄津贴年龄限制</name>
            <type>2</type>
            <value>分为60-69、70-79、80+三档，不同地区政策有所差异</value>
        </row>
    </问答>
    <政策文件>
        <row>
            <name>攀仁府办[2012]63号关于印发《攀枝花市仁和区80至89周岁老年人高龄生活津贴及90周岁以上老年人长寿生活补贴发放管理实施办法》的通知</name>
            <type>3</type>
            <value>10287</value>
        </row>
        <row>
            <name>盐边县人民政府办公室关于建立80-89岁高龄老人生活津贴制度的通知</name>
            <type>3</type>
            <value>10295</value>
        </row>
        <row>
            <name>米府办发〔2019〕2号高龄津贴发放管理办法</name>
            <type>3</type>
            <value>10444</value>
        </row>
    </政策文件>
</data>
</output></result>
*/
