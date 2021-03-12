package work.jyyh.archives.db;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.setting.dialect.Props;
import lombok.NonNull;
import org.junit.Test;
import work.jyyh.archives.ChineseHandle;
import work.jyyh.archives.db.table.*;

import javax.sql.DataSource;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * TODO
 * @author lgren
 * @since 2020-12-18 10:33 上午
 */
public class DBChineseHandleTest {
    private static final DataSource ds = new SimpleDataSource("jdbc:mysql://192.168.17.43:3306/dzda_hw", "dabase_en", "dabase_en");
    private static final File translateDictFile = new File("/Users/lgren/Desktop/档案国际化/db/翻译字典.properties");
    private static final Charset charset = StandardCharsets.UTF_8;
    private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy_MM_dd_HH_mm_ss");

    private static final Set<Class<? extends BaseAbs>> tableSet = CollUtil.newHashSet(
            // Aa10.class,
            Ue03.class
    );



    // 找到所有未翻译的词句
    @Test
    public void findAll() {
        Props translateDict = new Props(translateDictFile, charset);
        Set<String> set = new LinkedHashSet<>();
        tableSet.forEach(c -> set.addAll(findAllNotTranslate(c, translateDict)));
        addOtherWaitTranslate(translateDictFile, set);
    }

    // 检查翻译字典是否重复
    @Test
    public void checkTranslateDict() {
        Props translateDict = new Props(translateDictFile, charset);
        checkTranslateDict(translateDict);// 检查翻译字典
    }

    // 处理
    @Test
    public void handle() {
        Map<Locale, Set<String>> propsLineMap = new LinkedHashMap<>();
        Map<Class<? extends BaseAbs>, Set<String>> sqlMap = new LinkedHashMap<>();


        Props translateDict = new Props(translateDictFile, charset);
        checkTranslateDict(translateDict);// 检查翻译字典

        tableSet.forEach(c -> {
            Collection<? extends BaseAbs> dataList = handleTable(c, translateDict);

            dataList.forEach(o -> {
                sqlMap.computeIfAbsent(c, cV -> new LinkedHashSet<>())
                        .add(o.getSql());
                o.getTranslateMap().forEach((l, ftm) -> {
                    // 一条数据下所有翻译的字段
                    ftm.forEach((f, t) -> {
                        // 未改变
                        if (o.notChange()) {
                            return;
                        }
                        Set<String> propsSet = propsLineMap.computeIfAbsent(l, lV -> new LinkedHashSet<>());
                        propsSet.add(o.getPropsLine(f, t));
                    });
                });
            });
        });

        propsLineMap.forEach(this::generatorI18nProps);

        sqlMap.forEach(this::generatorSql);

    }

    public void checkTranslateDict(@NonNull Props translateDict) {
        Set<String> repeatSet = new LinkedHashSet<>();
        Set<String> singleSet = new LinkedHashSet<>();
        for (Object valueVO : translateDict.values()) {
            String valueV = (String) valueVO;
            if (StrUtil.isBlank(valueV)) {
                throw new RuntimeException("有未翻译词句");
            }
            if (!singleSet.contains(valueV)) {
                singleSet.add(valueV);
            } else {
                repeatSet.add(valueV);
            }
        }
        if (CollUtil.isNotEmpty(repeatSet)) {
            repeatSet.forEach(System.out::println);
            throw new RuntimeException("翻译重复");
        }
    }

    public <T extends BaseAbs> Set<String> findAllNotTranslate(@NonNull Class<T> tableClass, @NonNull Props translateDict) {
        String table = BaseAbs.getTableName(tableClass);

        List<Field> fieldNameList = BaseAbs.getWaitTranslateFieldList(tableClass);
        if (fieldNameList.isEmpty()) {
            return Collections.emptySet();
        }
        Set<String> waitTranslateSet = new LinkedHashSet<>();

        List<T> list = null;
        try {
            list = Db.use(ds).findAll(Entity.create(table), tableClass);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        for (T itemV : list) {
            for (Field field : fieldNameList) {
                String fieldValue = (String) BeanUtil.getFieldValue(itemV, BaseAbs.getTableFieldName(field));
                if (StrUtil.isNotBlank(fieldValue) && !translateDict.containsKey(fieldValue) && ReUtil.isMatch(".*?[\\u4e00-\\u9fa5].*", fieldValue)) {
                    waitTranslateSet.add(fieldValue);
                }
            }
        }
        return waitTranslateSet;
    }

    public <T extends BaseAbs> Collection<T> handleTable(@NonNull Class<T> tableClass, @NonNull Props translateDict) {
        String table = BaseAbs.getTableName(tableClass);


        List<Field> fieldNameList = BaseAbs.getWaitTranslateFieldList(tableClass);
        if (fieldNameList.isEmpty()) {
            return Collections.emptyList();
        }


        List<T> list;
        try {
            list = Db.use(ds).findAll(Entity.create(table), tableClass);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        Map<Field, Set<String>> notTranslateMap = new LinkedHashMap<>();
        for (T itemV : list) {
            // 所有字段的英文翻译
            for (Field fieldV : fieldNameList) {
                // 字段值
                String fieldValue = (String) BeanUtil.getFieldValue(itemV, BaseAbs.getTableFieldName(fieldV));
                // 如果此值为空 或者 不带有中文 则跳过
                if (StrUtil.isBlank(fieldValue) || !ReUtil.isMatch(".*?[\\u4e00-\\u9fa5].*", fieldValue)) {
                    continue;
                }
                // 字段翻译 TODO 多语言可修改 目前是 中文=英文
                String usTranslate = translateDict.getStr(fieldValue);
                // 翻译为空则跳过
                if (StrUtil.isBlank(usTranslate)) {
                    notTranslateMap.computeIfAbsent(fieldV, k -> new LinkedHashSet<>()).add(fieldValue);
                    continue;
                }
                itemV.addTranslate(Locale.CHINA, fieldV, fieldValue);
                itemV.addTranslate(Locale.US, fieldV, usTranslate);
                // props的key值
                itemV.setPropsPreKey(fieldV, StrUtil.toCamelCase(usTranslate.replaceAll("[^a-zA-Z0-9]", "_")));
            }
        }
        if (CollUtil.isNotEmpty(notTranslateMap)) {
            System.out.printf("表[%s]未翻译内容如下\n", table);
            notTranslateMap.forEach((k, v) -> {
                System.out.printf("字段[%s]未翻译内容如下\n", k);
                v.forEach(System.out::println);
            });
            throw new RuntimeException("具有未翻译的内容, 请全部翻译后再行处理");
        }

        return list;
    }

    public void generatorI18nProps(Locale locale, Set<String> propsLineColl) {
        File i18nPropsFile = ChineseHandle.getOrCreateFile(translateDictFile.getParent() + File.separator + locale + ".properties", "");
        addOtherWaitTranslate(i18nPropsFile, propsLineColl);
    }

    public void generatorSql(Class<? extends BaseAbs> table, Set<String> sqlSet) {
        File sqlFile = ChineseHandle.getOrCreateFile(translateDictFile.getParent() + File.separator + BaseAbs.getTableName(table) + "_" + fdf.format(new Date()) + ".sql", "");
        FileUtil.writeUtf8String(String.format("\n#region %s\n", LocalDateTime.now()), sqlFile);
        sqlSet.forEach(s -> FileUtil.appendUtf8String(String.format("%s\n", s), sqlFile));
        FileUtil.appendUtf8String(String.format("#endregion"), sqlFile);

    }

    /**
     * 将properties不存在的字段加入其中
     */
    private void addOtherWaitTranslate(@NonNull File localeProps, @NonNull Set<String> waitAddSet) {
        Props checkProps = new Props(localeProps, charset);
        boolean thisTimeHasOtherContentFlag = false;
        for (String ws : waitAddSet) {
            if (!checkProps.containsKey(ws.split("=")[0])) {
                if (!thisTimeHasOtherContentFlag && (thisTimeHasOtherContentFlag = true)) {
                    FileUtil.appendUtf8String(String.format("\n#region %s\n", LocalDateTime.now()), localeProps);
                }
                FileUtil.appendUtf8String(String.format("%s\n", ws), localeProps);
            }
        }
        if (thisTimeHasOtherContentFlag) {
            FileUtil.appendUtf8String(String.format("#endregion"), localeProps);
        }
    }


    @Test
    public void common() {
        System.out.println();
    }
}
