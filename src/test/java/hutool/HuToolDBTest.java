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
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * TODO
 * @author lgren
 * @since 2020-06-19 4:06 下午
 */
public class HuToolDBTest {
    @Test
    public void query() throws SQLException {
        List<Consume> consumeList = Db.use().findAll(Entity.create("consume"), Consume.class);
        List<Entity> list = Db.use().findAll(Entity.create("consume"));
        System.out.println();
    }

    @Data
    public class Consume {
        private String consumerId;
        private String goodsId;
        private String expenditure;
    }
}
