package hanlp;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * TODO
 * @author lgren
 * @since 2020-09-22 11:40 上午
 */
public class VecTest {
    @Test
    public void vecTest() throws IOException {
        DocVectorModel docModel = new DocVectorModel(new WordVectorModel("/Users/lgren/工作/久远银海/FTP/hanlp-wiki-vec-zh.txt"));
        // DocVectorModel docModel = new DocVectorModel(new WordVectorModel("/Users/lgren/工作/久远银海/FTP/sgns.baidubaike.bigram-char"));
        // DocVectorModel docModel = new DocVectorModel(new WordVectorModel("/Users/lgren/工作/久远银海/FTP/Tencent_AILab_ChineseEmbedding.txt"));
        String[] documents = new String[]{
                "山东苹果丰收",
                "农民在江苏种水稻",
                "奥运会女排夺冠",
                "世界锦标赛胜出",
                "中国足球失败",
        };

        for (int i = 0; i < documents.length; i++) {
            docModel.addDocument(i, documents[i]);
        }

        System.out.println("============体育=============");
        List<Map.Entry<Integer, Float>> entryList = docModel.nearest("体育");
        for (Map.Entry<Integer, Float> entry : entryList)
        {
            System.out.printf("%d %s %.2f\n", entry.getKey(), documents[entry.getKey()], entry.getValue());
        }

        System.out.println("============农业=============");
        entryList = docModel.nearest("农业");
        for (Map.Entry<Integer, Float> entry : entryList)
        {
            System.out.printf("%d %s %.2f\n", entry.getKey(), documents[entry.getKey()], entry.getValue());
        }

    }
}
