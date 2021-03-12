package hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import cn.hutool.setting.dialect.Props;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import hutool.entity.TradeInvoiceDO;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TODO
 * @author lgren
 * @since 2020-06-19 4:06 下午
 */
public class DBTest {
    @Test
    public void query() throws SQLException {
        List<Consume> consumeList = Db.use().findAll(Entity.create("consume").set("name", "张三"), Consume.class);
        List<Entity> list = Db.use().findAll(Entity.create("consume"));
        System.out.println();

    }

    @Data
    public class Consume {
        private String consumerId;
        private String goodsId;
        private String expenditure;
    }

    @Test
    public void insertData() throws SQLException {
        MockConfig mockConfig = new MockConfig()
                .excludes("id", "create_time")
                .subConfig("creatorName")
                .stringRegex("[a-z0-9]{1,5}10093?[a-z0-9]{1,5}")
                .globalConfig();
        List<Entity> list = IntStream.range(1, 10_0000)
                .parallel()
                .mapToObj(i -> Entity.create("trade_invoice")
                        .parseBean(JMockData.mock(TradeInvoiceDO.class, mockConfig), true, false)
                )
                .collect(Collectors.toList());
        Db.use().insert(list);
    }
}
