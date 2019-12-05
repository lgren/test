package pojo;

import com.lgren.util.BlobUtil;
import lombok.Data;

@Data
public class TestEntity implements BlobUtil.HasToMapMethod {
    private String field1;

    @BlobUtil.BlobToString
    private byte[] field2;

    @BlobUtil.BlobToString
    private byte[] field3;

}
