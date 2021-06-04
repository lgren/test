package work.jyyh.archives.db.sql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * TODO
 * 注意 为了支持英文
 *  1.aa10中的aaa103和aaa101需要调整大小为100
 *  2.uh70中YUH702需要调整大小为100
 * @author lgren
 * @since 2020-12-22 11:51 上午
 */
public class HandleTables {
    @Test
    public void findAndCheckStr() {
        String dictPathname = "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict";
        CollUtil.newArrayList(
                "/Users/lgren/Desktop/久远银海/档案国际化/ue0e.sql"
        ).forEach(s -> {
            System.out.println(s);
            findAndCheckBase(s, dictPathname);
        });
        open(dictPathname);
    }

    @Test
    public void handleStr() {
        String dictPathname = "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict";
        CollUtil.newArrayList(
                "/Users/lgren/Desktop/久远银海/档案国际化/ue0e.sql"
        ).forEach(s -> {
            System.out.println(s);
            handle(s, "/Users/lgren/Desktop/久远银海/档案国际化/%1$s/" + FileUtil.mainName(s) + ".sql", dictPathname);
        });
    }

    // @Test
    // public void findAndCheck() {
    //     CollUtil.newArrayList(
    //             // Select.DAZH_TABLES_SQL,
    //             // Select.DAZH_BUSINESS_DATA_SQL,
    //             // Select.DAZH_FRAME_DATA_SQL
    //             Select.DZDA_HW_20210125_FRAME
    //     ).forEach(this::findAndCheckBase);
    //     open(Select.DZDA_HW_20210125_FRAME.dictPathname);
    // }
    //
    // @Test
    // public void handle() {
    //     CollUtil.newArrayList(
    //             // Select.DAZH_TABLES_SQL,
    //             // Select.DAZH_BUSINESS_DATA_SQL,
    //             // Select.DAZH_FRAME_DATA_SQL
    //             Select.DZDA_HW_20210125_FRAME
    //     ).forEach(this::handle);
    // }

    // private static final String dictPathname = "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/翻译字典.ldict";
    // 最终前版 (?<=(["']))(?:\\.|[^'\\\n])*?[\u4e00-\u9fa5](?:\\.|[^'\\\n])*(?=\1)
    // 查找带有中文的字符串 分割中的[^']这个现目前只能每次修改为" 或者 '
    private final static Pattern FIND_P = Pattern.compile("(?<=([\"']))(?:\\\\.|[^'\\\\\\n])*?[\\u4e00-\\u9fa5](?:\\\\.|[^'\\\\\\n])*(?=\\1)");

    private final static Comparator<String> TRANSLATE_COMPARATOR = (o1, o2) -> o2.length() > o1.length() ? 1 : Objects.equals(o2.length(), o1.length()) ? o2.compareTo(o1) : -1;


    private void findAndCheckBase(Select select) {
        findAndCheckBase(select.oriPathname, select.dictPathname);
    }

    private void findAndCheckBase(String tables, String dictPathname) {
        String content = FileUtil.readUtf8String(tables);
        List<String> list = ReUtil.findAllGroup0(FIND_P, content);

        Object[] dictMappingArr = {Locale.SIMPLIFIED_CHINESE, Locale.US};
        File dictFile = getOrCreateFile(dictPathname, "#" + ArrayUtil.join(dictMappingArr, "<=>") + "\n");
        LDict lDict = new LDict(dictFile, dictMappingArr);
        lDict.addIfNot(list);
        if (lDict.checkKeyAndValueRepeat()) {
            throw new RuntimeException("具有重复");
        }
    }

    private void handle(Select select) {
        handle(select.oriPathname, select.outPathnameFormat, select.dictPathname);
    }

    private void handle(String tables, String tablesEnUs, String dictPathname) {
        String content = FileUtil.readUtf8String(tables);
        List<String> list = ReUtil.findAllGroup0(FIND_P, content);

        File dictFile = getOrCreateFile(dictPathname, "# 翻译字典\n\n");
        LDict lDict = new LDict(dictFile, new Object[]{Locale.SIMPLIFIED_CHINESE, Locale.US});
        lDict.addIfNot(list);
        if (lDict.checkKeyAndValueRepeat()) {
            throw new RuntimeException("具有重复");
        }

        // key1为语言, key2为源寻找数据, v为翻译后数据
        LTable<Object, String> replaceDict = new LTable<>();
        // k1: 类型 k: 源寻找数据 v1: 值
        lDict.getKVTable().forEach((k,v) -> v.forEach((k1, v1) -> replaceDict.put(v1.getValue(), k1, k)));

        // k1为语言, k2为源寻找数据, v为翻译后数据 v1为
        replaceDict.forEach((k1, i1) -> {
            String resultContent = StrUtil.replace(content, FIND_P, p -> Optional.of(i1.get(p.group(0)))
                    .map(s -> s.replaceAll("'", "''"))
                    .orElseThrow(() -> new RuntimeException("不存在")));
            FileUtil.writeUtf8String(resultContent, String.format(tablesEnUs, k1));
        });
    }

    public static File getOrCreateFile(String fileName, String notExistsDefaultContent) {
        File file = Optional.of(new File(fileName)).filter(f -> f.getParentFile().exists()).orElseThrow(() -> new RuntimeException("目录不存在"));
        if (!file.exists()) {
            FileUtil.writeUtf8String(notExistsDefaultContent, file);
        }
        return file;
    }

    public static void open(String pathOrPathname) {
        String osName = System.getProperty("os.name");
        if (osName == null) {
            return;
        }
        try {
            if (osName.contains("Mac")) {
                Runtime.getRuntime().exec("open " + pathOrPathname);
            } else if (osName.contains("Windows")) {
                Runtime.getRuntime().exec("cmd /c start " + pathOrPathname);
            } else {
                System.out.println("未找到文件输出目录:" + pathOrPathname);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    enum Select {
        DAZH_TABLES_SQL("/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/dazh_hw.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/%1$s/dazh_hw_%1$s.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/翻译字典.ldict"),

        DAZH_BUSINESS_DATA_SQL("/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/dazh_hw_business_data.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/%1$s/dazh_hw_business_data_%1$s.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/翻译字典.ldict"),

        DAZH_FRAME_DATA_SQL("/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/dazh_hw_frame_data.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/%1$s/dazh_hw_frame_data_%1$s.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/翻译字典.ldict"),

        DAZH_FRAME_PG_SQL("/Users/lgren/Desktop/档案国际化/pg/ta402-postgresql-all.sql",
                "/Users/lgren/Desktop/档案国际化/pg/%1$s/ta402-postgresql-all_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        DAZH_PG_SQL_20210125("/Users/lgren/Desktop/档案国际化/pg/pg_hw_20210125.sql",
                "/Users/lgren/Desktop/档案国际化/pg/%1$s/pg_hw_20210125_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        DAZH_PG_SQL_20210125_1("/Users/lgren/Desktop/档案国际化/pg/pg_hw_20210125_1.sql",
                "/Users/lgren/Desktop/档案国际化/pg/%1$s/pg_hw_20210125_1_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        DAZH_PG_SQL_20210125_2("/Users/lgren/Desktop/档案国际化/pg/pg_hw_20210125_2.sql",
                "/Users/lgren/Desktop/档案国际化/pg/%1$s/pg_hw_20210125_2_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        DAZH_PG_SQL_FRAME_20210125("/Users/lgren/Desktop/档案国际化/pg/pg_hw_frame_20210125.sql",
                "/Users/lgren/Desktop/档案国际化/pg/%1$s/pg_hw_frame_20210125_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        DZDA_HW_20210125_FRAME("/Users/lgren/Desktop/档案国际化/pg/dzda_hw_20210125_frame.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data_translate/dzda_hw_20210125_frame_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        ;
        private String oriPathname;
        private String outPathnameFormat;
        private String dictPathname;

        Select(String oriPathname, String outPathnameFormat, String dictPathname) {
            this.oriPathname = oriPathname;
            this.outPathnameFormat = outPathnameFormat;
            this.dictPathname = dictPathname;
        }
    }

}
