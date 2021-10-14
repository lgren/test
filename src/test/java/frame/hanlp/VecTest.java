package frame.hanlp;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.TrainingCallback;
import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
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
        DocVectorModel docModel = new DocVectorModel(new WordVectorModel("/Users/lgren/工作/久远银海/FTP/frame.hanlp-wiki-vec-zh.txt"));
        // DocVectorModel docModel = new DocVectorModel(new WordVectorModel("/Users/lgren/工作/久远银海/FTP/sgns.baidubaike.bigram-char"));
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


    @Test
    public void train() {
        Word2VecTrainer trainerBuilder = new Word2VecTrainer();
        trainerBuilder.setCallback(new TrainingCallback() {
            @Override
            public void corpusLoading(float percent) {
                System.out.println(percent);
            }

            @Override
            public void corpusLoaded(int vocWords, int trainWords, int totalWords) {
                System.out.println(vocWords);
                System.out.println(trainWords);
                System.out.println(totalWords);
            }

            @Override
            public void training(float alpha, float progress) {
                System.out.printf("%s\t%s\n", alpha, progress);
            }
        });

        WordVectorModel wordVectorModel = trainerBuilder.train(
                "/Users/lgren/Project/download/搜狗文本分类语料库已分词.txt",
                "/Users/lgren/Project/download/ok.txt");
        System.out.println(wordVectorModel.nearest("中国"));
    }

    private static void progress(double now, double all) {
        for (int j = 0; j < 6; j++) {
            System.out.print("\b");
        }
        double nowD = now / all;
        int calcSpaceNum = (int) (nowD / 10);
        System.out.printf("%3.2f%s", nowD, calcSpaceNum == 0 ? "  " : calcSpaceNum == 10 ? "" : " ");
    }

}
