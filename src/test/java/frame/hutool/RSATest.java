package frame.hutool;

import cn.hutool.crypto.SecureUtil;
import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 * @author lgren
 * @since 2021-04-01 5:25 下午
 */
public class RSATest {
    @Test
    public void name1() {
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey aPrivate = pair.getPrivate();
        PublicKey aPublic = pair.getPublic();
        System.out.println(aPrivate);
        System.out.println(aPublic);
    }

    @Test
    public void name2() throws IOException {
        // 加载词向量模型 系统启动加载 后续不用加载
        DocVectorModel docVectorModel = new DocVectorModel(new WordVectorModel("/Users/lgren/工作/久远银海/FTP/frame.hanlp-wiki-vec-zh.txt"));

        // 加载自己的问题进去 待匹配
        AtomicInteger i = new AtomicInteger();
        String[] arr = {
                "山东苹果丰收",
                "农民在江苏种水稻",
                "奥运会女排夺冠",
                "世界锦标赛胜出",
                "中国足球失败",
        };
        Arrays.stream(arr).forEach(s -> docVectorModel.addDocument(i.getAndIncrement(), s));

        // 使用词语/句子等去匹配
        docVectorModel.nearest("蔬菜水果").forEach(o -> System.out.printf("%d(%s)=%s\n", o.getKey(), arr[o.getKey()], o.getValue()));
    }
}
