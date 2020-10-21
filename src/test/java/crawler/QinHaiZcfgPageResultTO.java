package crawler;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TODO
 * @author lgren
 * @since 2020-07-01 5:24 下午
 */
@NoArgsConstructor
@Data
public class QinHaiZcfgPageResultTO {
    private String currentpage;
    private String pagesize;
    private String totalcount;
    private String totalpage;
    @JSONField(name = "zcfgs")
    private List<QinHaiZcfgTO> zcfgList;


}
