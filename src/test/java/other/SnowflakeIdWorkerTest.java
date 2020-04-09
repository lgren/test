package other;

import com.lgren.other.GetIdEnum;
import org.junit.Test;

public class SnowflakeIdWorkerTest {

    @Test
    public void name1() {
        System.out.println(GetIdEnum.BUILD.getId());
        System.out.println(GetIdEnum.BUILD.getId());
    }
}
