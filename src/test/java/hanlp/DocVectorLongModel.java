package hanlp;

import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.mining.word2vec.AbstractVectorModel;
import com.hankcs.hanlp.mining.word2vec.Vector;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 文档向量模型
 * @author hankcs
 */
public class DocVectorLongModel extends AbstractVectorModel<Long> {
    private final WordVectorModel wordVectorModel;
    // 分词器
    private Segment segment;
    // 是否使用CoreStopwordDictionary的过滤器
    private boolean filter;

    private final Map<Long, Vector> storageT;

    public DocVectorLongModel(WordVectorModel wordVectorModel) {
        this(wordVectorModel, NotionalTokenizer.SEGMENT, true, new TreeMap<>());
    }

    public DocVectorLongModel(WordVectorModel wordVectorModel, Segment segment, boolean filter, Map<Long, Vector> storage) {
        super(storage);
        this.storageT = storage;
        this.wordVectorModel = wordVectorModel;
        this.segment = segment;
        this.filter = filter;
    }

    /**
     * 添加文档
     * @param id      文档id
     * @param content 文档内容
     * @return 文档向量
     */
    public Vector addDocument(long id, String content) {
        Vector result = query(content);
        if (result == null) return null;
        storageT.put(id, result);
        return result;
    }


    /**
     * 查询最相似的前10个文档
     * @param query 查询语句（或者说一个文档的内容）
     * @return
     */
    public List<Map.Entry<Long, Float>> nearest(String query) {
        return nearest(query, 10);
    }

    public List<Map.Entry<Long, Float>> nearest(String query, int size) {
        if (query == null || query.length() == 0) {
            return Collections.emptyList();
        }
        try {
            return nearest(query(query), size);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }


    /**
     * 将一个文档转为向量
     * @param content 文档
     * @return 向量
     */
    public Vector query(String content) {
        if (content == null || content.length() == 0) return null;
        List<Term> termList = segment.seg(content);
        if (filter) {
            CoreStopWordDictionary.apply(termList);
        }
        Vector result = new Vector(dimension());
        int n = 0;
        for (Term term : termList) {
            Vector vector = wordVectorModel.vector(term.word);
            if (vector == null) {
                continue;
            }
            ++n;
            result.addToSelf(vector);
        }
        if (n == 0) {
            return null;
        }
        result.normalize();
        return result;
    }

    @Override
    public int dimension() {
        return wordVectorModel.dimension();
    }

    /**
     * 文档相似度计算
     * @param what
     * @param with
     * @return
     */
    public float similarity(String what, String with) {
        Vector A = query(what);
        if (A == null) return -1f;
        Vector B = query(with);
        if (B == null) return -1f;
        return A.cosineForUnitVector(B);
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    /**
     * 是否激活了停用词过滤器
     * @return
     */
    public boolean isFilterEnabled() {
        return filter;
    }

    /**
     * 激活/关闭停用词过滤器
     * @param filter
     */
    public void enableFilter(boolean filter) {
        this.filter = filter;
    }
}

