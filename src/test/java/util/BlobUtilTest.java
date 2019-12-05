package util;

import com.google.common.collect.Lists;
import com.lgren.util.BlobUtil;
import lombok.Data;
import org.junit.Test;
import org.springframework.cglib.beans.BeanMap;
import pojo.TestEntity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * BlobUtil测试
 * @author lgren
 * @since 2019-11-29 16:12
 */
public class BlobUtilTest {

    @Test
    public void name1() throws UnsupportedEncodingException {
        TestEntity testEntity = getTestEntity();
        Map<String, Object> stringObjectMap = BlobUtil.handlerByteArrForDomain(testEntity);
        System.out.println();
    }

    @Test
    public void name2() throws UnsupportedEncodingException {
        List<BlobUtil.HasToMapMethod> list = Lists.newArrayList(getTestEntity(), getTestEntity(), getTestEntity(), getTestEntity(), getTestEntity());
        List<Map<String, Object>> list1 = BlobUtil.handlerByteArrForDomain(list);
        System.out.println();
    }

    private ThreadLocalRandom current = ThreadLocalRandom.current();
    private TestEntity getTestEntity() throws UnsupportedEncodingException {
        int i = current.nextInt(0, 10000);
        TestEntity testEntity = new TestEntity();
        testEntity.setField1("测试" + i);
        testEntity.setField2(BlobUtil.stringToByteArr("这是内容" + i));
        testEntity.setField3(BlobUtil.stringToByteArr("这是内容" + i));
        return testEntity;
    }
}