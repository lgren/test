package frame.hutool;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import frame.hutool.entity.TradeInvoiceDO;
import lombok.Data;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
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

    @Test
    public void insertDataSession() {
        // DataSource ds = new SimpleDataSource("jdbc:oracle:thin:@172.20.21.213:1521/pdb20", "zsk123", "zsk123");
        // Session session = Session.create(ds);
        //
        // session.beginTransaction();
        // map.forEach((k,v) -> v.forEach(o -> insert(session, Entity.create("VK0123").parseBean(o))));
        // session.commit();
    }
}
