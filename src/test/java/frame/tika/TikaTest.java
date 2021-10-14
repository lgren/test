package frame.tika;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.microsoft.OfficeParser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.*;

/**
 * TODO
 * @author lgren
 * @since 2020-11-19 11:49 上午
 */
public class TikaTest {
    @Test
    public void name1() throws IOException, TikaException {
        //二进制文件路径
        String fileName="/Users/lgren/Desktop/test.txt";
        //二进制文件
        //通过tika获取文件内容
        Tika tika = new Tika();
        String filecontent = tika.parseToString(new File(fileName));

        //打印文件内容
        System.out.println("Extracted Content: " + filecontent);

        try{

            //要转换到的文件
            File file =new File("/Users/lgren/Desktop/test.doc");

            //文件不存在就新建
            if(!file.exists()){
                file.createNewFile();
            }

            //把二进制文件内容写入doc文件
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(filecontent);
            bw.close();

            System.out.println("Done");

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void name() throws IOException, TikaException {
        String s = tikaTool(new File("/Users/lgren/Desktop/HLWRS_SJ_GF_代码编写规范_20190911V1.0(1).doc"));
        System.out.println();

    }

    private String tikaTool(File f) throws IOException, TikaException {
        Tika tika = new Tika();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.AUTHOR, "空号");//重新设置文档的媒体内容
        metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());
        String str = tika.parseToString(new FileInputStream(f),metadata);
        for(String name:metadata.names()) {
            System.out.println(name+":"+metadata.get(name));
        }
        return str;
    }

    @Test
    public void name2() throws TikaException, SAXException, IOException {
        // Tika默认是10*1024*1024，这里防止文件过大导致Tika报错
        BodyContentHandler handler = new BodyContentHandler(1024 * 1024 * 10);
        Metadata metadata = new Metadata();
        // Tika-1.1最高支持2007及更低版本的Office Word文档，如果是高于2007版本的Word文档需要使用POI处理（Tika会报错）
        FileInputStream inputstream = new FileInputStream(new File("/Users/lgren/Desktop/HLWRS_GL_ZH_办公场地管理制度_20190925V1.0(1).docx"));
        ParseContext pcontext = new ParseContext();

        // 解析Word文档时应由超类AbstractParser的派生类OfficeParser实现
        Parser msofficeparser = new OfficeParser();
        msofficeparser.parse(inputstream, handler, metadata, pcontext);
        // 获取Word文档的内容
        System.out.println("Word文档内容:" + handler.toString());

        // 获取Word文档的元数据
        System.out.println("Word文档元数据:");
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
            System.out.println(name + " : " + metadata.get(name));
        }
    }

    @Test
    public void base() throws IOException, TikaException {
        Tika tika = new Tika();
        tika.getMaxStringLength();

        System.out.printf("tika.detect: %s\n", tika.detect("/Users/lgren/Desktop/Excel测试.xlsx"));
        System.out.printf("tika.detect: %s\n", tika.detect("/Users/lgren/Desktop/HLWRS_SJ_GF_代码编写规范_20190911V1.0(1).doc"));
        System.out.printf("tika.detect: %s\n", tika.detect("/Users/lgren/Desktop/HLWRS_GL_ZH_办公场地管理制度_20190925V1.0(1).docx"));
        System.out.printf("tika.detect: %s\n", tika.detect("/Users/lgren/Desktop/SCHLWRS_GL_ZH_项目管理规范_20190922V1.2.docx"));
        System.out.printf("tika.detect: %s\n", tika.detect("/Users/lgren/Desktop/Mathematica12.0-12.1激活.html"));

        String content = tika.parseToString(new FileInputStream("/Users/lgren/Desktop/HLWRS_SJ_GF_代码编写规范_20190911V1.0(1).doc"));
        // String content = tika.parseToString(new FileInputStream("/Users/lgren/Desktop/SCHLWRS_GL_ZH_项目管理规范_20190922V1.2.docx"));
        // System.out.println();
    }
}
