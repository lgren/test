package work.jyyh.archives;

import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * TODO
 * @author lgren
 * @since 2020-12-03 2:17 下午
 */
public class ChineseHandleTest {

    @Test
    public void preJspAndJs() {
        Data data = Data.文件夹测试;
        //region 配置匹配分类
        Stack<ChineseHandle.CategoryInfo> categoryStack = new Stack<>();
        // key
        categoryStack.add(new ChineseHandle.CategoryInfo("key", false, s -> "key=\"" + s + "\"",
                s -> ChineseHandle.findAllGroup("(?<=key=([\"']))(?=(?:\\\\\"|[^\"])*?[\\u4e00-\\u9fa5])(?:\\\\\"|[^\"])*(?=\\1)", s)));
        // placeholder
        categoryStack.add(new ChineseHandle.CategoryInfo("placeholder", false, s -> "placeholder=\"" + s + "\"",
                s -> ChineseHandle.findAllGroup("(?<=placeholder=([\"']))(?=(?:\\\\\"|[^\"])*?[\\u4e00-\\u9fa5])(?:\\\\\"|[^\"])*(?=\\1)", s)));
        // bpopTipMsg
        categoryStack.add(new ChineseHandle.CategoryInfo("bpopTipMsg", false, s -> "bpopTipMsg=\"" + s + "\"",
                s -> ChineseHandle.findAllGroup("(?<=bpopTipMsg=([\"']))(?=(?:\\\\\"|[^\"])*?[\\u4e00-\\u9fa5])(?:\\\\\"|[^\"])*(?=\\1)", s)));
        // data里边
        categoryStack.add(new ChineseHandle.CategoryInfo("data", false,
                s -> s,
                s -> String.format("${I18nUtil.getMessage(\\\"%1$s\\\", \\\"%1$s\\\")}", s),
                s -> ChineseHandle.findAllGroup("(?<=data=([\"']))(?=(?:\\\\\"|[^\"])*?[\\u4e00-\\u9fa5])(?:\\\\\"|[^\"])*(?=\\1)", s)
                        .stream().flatMap(sv -> ChineseHandle.findAllGroup("[\\u4e00-\\u9fa5]+", sv).stream()).collect(Collectors.toList())));
        // 忽略注释
        categoryStack.add(new ChineseHandle.CategoryInfo("comment", true, s -> s,
                (s1, s2) -> ChineseHandle.findAllGroup("//.*" +
                        "|\\<\\%\\-\\-[\\s\\S]*?\\-\\-\\%\\>" +
                        "|\\<\\!\\-\\-[\\s\\S]*?\\-\\-\\>" +
                        "|\\/\\*[\\s|\\S]*?\\*\\/", s1)));
        categoryStack.add(new ChineseHandle.CategoryInfo("normal", false, s -> s, s -> String.format("${I18nUtil.getMessage(\"%1$s\", \"%1$s\")}", s),
                (s1, s2) -> ChineseHandle.findAllGroup("[\\u4e00-\\u9fa5]+", s2)));
        //endregion
        base(categoryStack, h -> h.preHandleMultitude(data.translateDictPathname, data.oriPathArr), data.ignorePrefix, data.duplicatePath);
    }

    @Test
    public void jspAndJs() {
        Data data = Data.文件夹测试;
        //region 配置匹配分类
        Stack<ChineseHandle.CategoryInfo> categoryStack = new Stack<>();
        // key
        categoryStack.add(new ChineseHandle.CategoryInfo("key", false, s -> "key=\"" + s + "\"",
                s -> ChineseHandle.findAllGroup("(?<=key=([\"']))(?=(?:\\\\\"|[^\"])*?[\\u4e00-\\u9fa5])(?:\\\\\"|[^\"])*(?=\\1)", s)));
        // placeholder
        categoryStack.add(new ChineseHandle.CategoryInfo("placeholder", false, s -> "placeholder=\"" + s + "\"",
                s -> ChineseHandle.findAllGroup("(?<=placeholder=([\"']))(?=(?:\\\\\"|[^\"])*?[\\u4e00-\\u9fa5])(?:\\\\\"|[^\"])*(?=\\1)", s)));
        // bpopTipMsg
        categoryStack.add(new ChineseHandle.CategoryInfo("bpopTipMsg", false, s -> "bpopTipMsg=\"" + s + "\"",
                s -> ChineseHandle.findAllGroup("(?<=bpopTipMsg=([\"']))(?=(?:\\\\\"|[^\"])*?[\\u4e00-\\u9fa5])(?:\\\\\"|[^\"])*(?=\\1)", s)));
        // data里边
        categoryStack.add(new ChineseHandle.CategoryInfo("data", false,
                s -> s,
                s -> String.format("${I18nUtil.getMessage(\\\"%1$s\\\", \\\"%1$s\\\")}", s),
                s -> ChineseHandle.findAllGroup("(?<=data=([\"']))(?=(?:\\\\\"|[^\"])*?[\\u4e00-\\u9fa5])(?:\\\\\"|[^\"])*(?=\\1)", s)
                        .stream().flatMap(sv -> ChineseHandle.findAllGroup("[\\u4e00-\\u9fa5]+", sv).stream()).collect(Collectors.toList())));
        // 忽略注释
        categoryStack.add(new ChineseHandle.CategoryInfo("comment", true, s -> s,
                (s1, s2) -> ChineseHandle.findAllGroup("//.*" +
                        "|\\<\\%\\-\\-[\\s\\S]*?\\-\\-\\%\\>" +
                        "|\\<\\!\\-\\-[\\s\\S]*?\\-\\-\\>" +
                        "|\\/\\*[\\s|\\S]*?\\*\\/", s1)));
        categoryStack.add(new ChineseHandle.CategoryInfo("normal", false, s -> s, s -> String.format("${I18nUtil.getMessage(\"%1$s\", \"%1$s\")}", s),
                (s1, s2) -> ChineseHandle.findAllGroup("[\\u4e00-\\u9fa5]+", s2)));
        //endregion
        base(categoryStack, h -> h.handleMultitude(data.translateDictPathname, data.oriPathArr), data.ignorePrefix, data.duplicatePath);
    }

    @Test
    public void preArchivesJava() {
        Data data = Data.档案gims_java;
        //region 配置匹配分类
        Stack<ChineseHandle.CategoryInfo> categoryStack = new Stack<>();
        // 忽略注释
        categoryStack.add(new ChineseHandle.CategoryInfo("comment", true, s -> s,
                (s1, s2) -> ChineseHandle.findAllGroup("//.*" +
                        "|\\<\\%\\-\\-[\\s\\S]*?\\-\\-\\%\\>" +
                        "|\\<\\!\\-\\-[\\s\\S]*?\\-\\-\\>" +
                        "|\\/\\*[\\s|\\S]*?\\*\\/", s1)));

        // string
        categoryStack.add(new ChineseHandle.CategoryInfo("string", false, s -> "\"" + s + "\"", s -> String.format("com.yinhai.modules.i18n.util.I18nUtil.getMessage(\"%1$s\", \"%1$s\")", s),
                (s1, s) -> ChineseHandle.findAllGroup("(?<=([\"']))(?=(?:\\\\\"|[^\"\n])*?[\\u4e00-\\u9fa5])(?:\\\\\"|[^\"\n])*(?=\\1)", s)));
        // 常规
        categoryStack.add(new ChineseHandle.CategoryInfo("normal", false, s -> s, s -> String.format("com.yinhai.modules.i18n.util.I18nUtil.getMessage(\"%1$s\", \"%1$s\")", s),
                (s1, s2) -> ChineseHandle.findAllGroup("[\\u4e00-\\u9fa5]+", s2)));
        //endregion
        base(categoryStack, h -> h.preHandleMultitude(data.translateDictPathname, data.oriPathArr), data.ignorePrefix, data.duplicatePath);
    }

    @Test
    public void java() {
        Data data = Data.档案gims_java;
        //region 配置匹配分类
        Stack<ChineseHandle.CategoryInfo> categoryStack = new Stack<>();
        // 忽略注释
        categoryStack.add(new ChineseHandle.CategoryInfo("comment", true, s -> s,
                (s1, s2) -> ChineseHandle.findAllGroup("//.*" +
                        "|\\<\\%\\-\\-[\\s\\S]*?\\-\\-\\%\\>" +
                        "|\\<\\!\\-\\-[\\s\\S]*?\\-\\-\\>" +
                        "|\\/\\*[\\s|\\S]*?\\*\\/", s1)));

        // string
        categoryStack.add(new ChineseHandle.CategoryInfo("string", false, s -> "\"" + s + "\"", s -> String.format("com.yinhai.modules.i18n.util.I18nUtil.getMessage(\"%1$s\", \"%1$s\")", s),
                (s1, s) -> ChineseHandle.findAllGroup("(?<=([\"']))(?=(?:\\\\\"|[^\"\n])*?[\\u4e00-\\u9fa5])(?:\\\\\"|[^\"\n])*(?=\\1)", s)));
        // 常规
        categoryStack.add(new ChineseHandle.CategoryInfo("normal", false, s -> s, s -> String.format("com.yinhai.modules.i18n.util.I18nUtil.getMessage(\"%1$s\", \"%1$s\")", s),
                (s1, s2) -> ChineseHandle.findAllGroup("[\\u4e00-\\u9fa5]+", s2)));
        //endregion
        base(categoryStack, h -> h.handleMultitude(data.translateDictPathname, data.oriPathArr), data.ignorePrefix, data.duplicatePath);
    }

    private static void base(Stack<ChineseHandle.CategoryInfo> categoryStack, Consumer<ChineseHandle> runFunc, String ignorePrefix, String duplicatePath) {
        FastDateFormat FDF = FastDateFormat.getInstance("yyyyMMddHHmmss");
        AtomicInteger count = new AtomicInteger(1);

        //region 配置语言类型
        Map<String, Locale> localeMap = new LinkedHashMap<>();
        localeMap.put("0:中文", Locale.SIMPLIFIED_CHINESE);
        localeMap.put("1:英文", Locale.US);
        //endregion

        //region i18n的props的key值生成方法
        BiFunction<String, ChineseHandle.TranslateItem, String> propsKeyNameFunc = (s, t) -> {
            String prefixR = s.replace(ignorePrefix, "").replaceAll("[\\\\/.]", "_");
            String thisName = t.getTranslateMap().get(Locale.US)
                    .replaceAll("[,.!:'\\[\\]()/《》：]", "")
                    .replaceAll("%", "_per")
                    .replaceAll("℃", "_celsius")
                    .replaceAll(" ", "_");
            String strV = StrUtil.toCamelCase(thisName);
            return prefixR + "_" + (strV.length() > 15 ? "msg" + count.getAndIncrement() : strV);
        };
        //endregion

        // 副本文件生成方法
        Function<File, String> duplicatePathname = f -> String.format("%1$s%2$s_%3$s.%4$s", duplicatePath, FileUtil.mainName(f), FDF.format(new Date()), FileUtil.extName(f));
        // properties名称生成方法
        BiFunction<String, Locale, String> outPropsPathnameFunc = (s, l) -> String.format(s.substring(0, s.lastIndexOf(".")) + "_%1$s.properties", l);

        ChineseHandle handle = new ChineseHandle(categoryStack, localeMap, duplicatePathname, outPropsPathnameFunc, propsKeyNameFunc);
        runFunc.accept(handle);
        // handle.preHandleMultitude(translateDictPathname, oriPathArr);
        // handle.handleMultitude(translateDictPathname, oriPathArr);
    }

    @Test
    public void find() {
        Props props = new Props("/Users/lgren/Desktop/档案国际化/java/翻译字典.properties", StandardCharsets.UTF_8);
        Set<String> sameVSet = new HashSet<>();
        Set<String> otherVSet = new TreeSet<>((b,a) -> {
            if (Objects.equals(a, b)) {
                sameVSet.add(a);
                return 0;
            }
            return b.length() - a.length() == 0 ? 1 : b.length() - a.length();
        });
        props.values().stream().map(Objects::toString).forEach(otherVSet::add);
        sameVSet.forEach(System.out::println);
        System.out.println();
    }

    enum Data {
        文件夹测试("/Users/lgren/Desktop/", "/Users/lgren/Desktop/temp/翻译字典1.properties", "/Users/lgren/Desktop/temp/to/", "/Users/lgren/Desktop/temp/ori"),
        单文件测试("/Users/lgren/Desktop/temp/bugTest/", "/Users/lgren/Desktop/temp/bugTest/翻译字典.properties", "/Users/lgren/Desktop/temp/bugTest/to/", "/Users/lgren/Desktop/temp/bugTest/test.jsp"),
        档案java代码测试collection("/Users/lgren/Project/Java/JYYH/svn/JY19MFRD00114/04.Implement/dabase4.0.1/code_hw/archive-gamsBackend/src/main/",
                "/Users/lgren/Desktop/档案国际化/java/翻译字典.properties",
                "/Users/lgren/Desktop/档案国际化/java/备份/",
                "/Users/lgren/Project/Java/JYYH/svn/JY19MFRD00114/04.Implement/dabase4.0.1/code_hw/archive-gamsBackend/src/main/java/com/yinhai/accounting/collection"),

        档案gams_java("/Users/lgren/Project/Java/JYYH/svn/JY19MFRD00114/04.Implement/dabase4.0.1/code_hw/archive-gamsBackend/src/main/",
                "/Users/lgren/Desktop/档案国际化/java/翻译字典.properties",
                "/Users/lgren/Desktop/档案国际化/java/备份/",
                "/Users/lgren/Project/Java/JYYH/svn/JY19MFRD00114/04.Implement/dabase4.0.1/code_hw/archive-gamsBackend/src/main/java"),

        档案core_java("/Users/lgren/Project/Java/JYYH/svn/JY19MFRD00114/04.Implement/dabase4.0.1/code_hw/archive-core/src/main/",
                "/Users/lgren/Desktop/档案国际化/java/翻译字典.properties",
                "/Users/lgren/Desktop/档案国际化/java/备份/",
                "/Users/lgren/Project/Java/JYYH/svn/JY19MFRD00114/04.Implement/dabase4.0.1/code_hw/archive-core/src/main/java"),

        档案gims_java("/Users/lgren/Project/Java/JYYH/svn/JY19MFRD00114/04.Implement/dabase4.0.1/code_hw/archive-gimsBackend/src/main/",
                "/Users/lgren/Desktop/档案国际化/java/翻译字典.properties",
                "/Users/lgren/Desktop/档案国际化/java/备份/",
                "/Users/lgren/Project/Java/JYYH/svn/JY19MFRD00114/04.Implement/dabase4.0.1/code_hw/archive-gimsBackend/src/main/java"),
        ;
        public String ignorePrefix;// 去掉前缀用于生产properties的key
        public String translateDictPathname;// 翻译字典
        public String duplicatePath;// 备份地址
        public String[] oriPathArr;// 源路径

        Data(String ignorePrefix, String translateDictPathname, String duplicatePath, String... oriPathArr) {
            this.oriPathArr = oriPathArr;
            this.ignorePrefix = ignorePrefix;
            this.translateDictPathname = translateDictPathname;
            this.duplicatePath = duplicatePath;
        }
    }

    @Test
    public void common() {
        System.out.println(format("%1$s你好呀, %2$s还行吧", "tom:" , "sam:"));
    }

    private String format(String format, String... params) {
        return String.format(format, (Object[]) params);
    }
}
