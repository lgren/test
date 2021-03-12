package work.jyyh.archives;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrSpliter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.setting.dialect.Props;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * TODO
 * @author lgren
 * @since 2020-12-02 9:24 上午
 */
@Data
public class ChineseHandle {
    /*---------------------------------------- 设置 ----------------------------------------*/
    // 最终结果的输出地址
    private Function<String, String> resultPathname;
    // 源文件副本地址
    private Function<File, String> duplicatePathname;
    // 分类类型列表
    private final List<CategoryInfo> categoryList;
    // properties格式映射 格式例如 put("0:中文", Locale.SIMPLIFIED_CHINESE)
    private final Map<String, Locale> localeMapping;
    // properties名称生成方法
    private final BiFunction<String, Locale, String> outPropsPathnameFunc;
    // 生成properties的key的方法
    private final BiFunction<String, TranslateItem, String> propsKeyNameFunc;
    // 当不存在properties时, 默认输入的内容
    private final String notExistPropsOutStr;

    /*---------------------------------------- 处理 ----------------------------------------*/
    // 源文件路径
    private String translateDictPathname;
    // 源文件
    private File translateDictFile;
    // 源文件路径
    private String oriPathname;
    // 源文件
    private File oriFile;
    // 源文件内容
    private String oriContent;
    // 缓存计数
    private AtomicInteger tempReplaceStrI;
    // 待替换/翻译的内容
    private Set<TranslateItem> tempWaitReplaceSearchSet;
    // 翻译字典
    private Map<String, Map<Locale, String>> translateDictMap;
    // 处理步骤
    private Stack<TranslateResult> resultContentStack;
    // 最终结果文本
    private String resultContent;
    // 输出properties地址
    private Map<Locale, Set<String>> outPropsMap;

    /*---------------------------------------- 内部设置 ----------------------------------------*/
    private final Supplier<String> tempReplaceStrFunc = () -> "TTTTTTLGRenTempTTTTTT" + getTempReplaceStrI().incrementAndGet();
    // 防止 例如 文本: "123, 12" 需要将"123"->"你好呀", 12->"不好", 结果为"你好呀, 不好", 如果不做处理可能结果为"不好呀, 不好"
    private final static Comparator<String> translateComparator = (o1, o2) -> o2.length() > o1.length() ? 1 : Objects.equals(o2.length(), o1.length()) ? o2.compareTo(o1) : -1;
    private final static Comparator<TranslateItem> translateItemComparator = (o1, o2) -> translateComparator.compare(o1.getSearchContent(), o2.getSearchContent());
    private final static Comparator<TranslateItem> translateItemComparatorByNowWait = (o1, o2) -> translateComparator.compare(o1.getNowWaitReplaceStr(), o2.getNowWaitReplaceStr());
    private final static String WAIT_FLAG_STR = "____wait____";
    private final static String PROPS_SPLIT_STR = "<->";

    //region 构造方法
    public ChineseHandle(Stack<CategoryInfo> categoryList,
                         Map<String, Locale> localeMapping,
                         Function<File, String> duplicatePathname,
                         BiFunction<String, Locale, String> outPropsPathnameFunc,
                         BiFunction<String, TranslateItem, String> propsKeyNameFunc) {
        this(categoryList, localeMapping, s -> s, duplicatePathname, outPropsPathnameFunc, propsKeyNameFunc);
    }

    public ChineseHandle(Stack<CategoryInfo> categoryList,
                         Map<String, Locale> localeMapping,
                         Function<String, String> resultPathname,
                         Function<File, String> duplicatePathname,
                         BiFunction<String, Locale, String> outPropsPathnameFunc,
                         BiFunction<String, TranslateItem, String> propsKeyNameFunc) {
        this.resultPathname = Objects.requireNonNull(resultPathname, "最终结果地址不能为空");
        this.duplicatePathname = Objects.requireNonNull(duplicatePathname, "源文件副本地址不能为空");
        this.categoryList = Objects.requireNonNull(categoryList, "分类不能为空");
        TreeMap<String, Locale> localeTreeMap = new TreeMap<>((s1, s2) -> {
            String[] s1Arr = s1.split(":");
            String[] s2Arr = s2.split(":");
            try {
                if (s1Arr.length < 2 || s2Arr.length < 2) {
                    throw new RuntimeException("长度异常");
                }
                int s1n = Integer.parseInt(s1Arr[0]);
                int s2n = Integer.parseInt(s2Arr[0]);
                if (s1n < 0 || s2n < 0) {
                    throw new RuntimeException("序号需要大等于0");
                }

                return s1n - s2n;
            } catch (Exception e) {
                throw new RuntimeException("映射key值结构错误, 正确结构例如: \"0:中文\"", e);
            }
        });
        localeTreeMap.putAll(Objects.requireNonNull(localeMapping, "properties语言映射不能为空"));
        this.localeMapping = localeTreeMap;

        this.outPropsPathnameFunc = Objects.requireNonNull(outPropsPathnameFunc, "properties输出文件生成不能为空");
        this.propsKeyNameFunc = Objects.requireNonNull(propsKeyNameFunc, "properties字段生成不能为空");

        this.notExistPropsOutStr = String.format("# 此文件为翻译字段 目前支持[%1$s] \n" +
                        "# 格式为 0=1%2$s2%2$s3... \n" +
                        "# 例1 你好=hello 其中 你好匹配为0, hello匹配为1\n" +
                        "# 例2 你好=hello%2$s思密达 其中[你好]匹配为0, [hello]匹配为1 [思密达]匹配为2\n"
                , getLocaleMapping().keySet(), PROPS_SPLIT_STR);
    }
    //endregion

    public void preHandleMultitude(String translateDictPathname, String... oriPathArr) {
        for (String oriPathSV : oriPathArr) {
            for (String oriPathV : oriPathSV.split(",")) {
                List<File> allFileList = FileUtil.loopFiles(oriPathV).stream().filter(f -> !f.getName().contains(".DS_Store")).collect(Collectors.toList());
                for (File f : allFileList) {
                    // 1 预处理
                    preHandleContent(translateDictPathname, f.getAbsolutePath());

                    // 2 将新的词句加入字典
                    addOtherWaitTranslate();
                }
            }
        }


        // 3 如果有新词汇则等待人工操作 !!删除等待标识即可结束等待
        waitHandle(getTranslateDictFile(), WAIT_FLAG_STR);

        for (String oriPathSV : oriPathArr) {
            for (String oriPathV : oriPathSV.split(",")) {
                List<File> allFileList = FileUtil.loopFiles(oriPathV).stream().filter(f -> !f.getName().contains(".DS_Store")).collect(Collectors.toList());
                for (File f : allFileList) {
                    // 1 预处理
                    preHandleContent(translateDictPathname, f.getAbsolutePath());

                    // 2 将新的词句加入字典
                    addOtherWaitTranslate();

                    // 3 如果有新词汇则等待人工操作 !!删除等待标识即可结束等待
                    waitHandle(getTranslateDictFile(), WAIT_FLAG_STR);

                    // 4 获取翻译字典所有内容
                    translateDictToMap();

                    // 5 完成所有替换操作
                    handleResult();

                    // // 5-1替换并复制源文件副本文件
                    // replaceAndDuplicateFile();

                    // 6 生成properties信息
                    generatePropertiesInfo();

                    // 7 生成properties文件
                    generateOutPropsFiles();

                    // // 7-1 打印properties信息
                    // printPropertiesInfo();
                }
            }
        }

    }

    public void handleMultitude(String translateDictPathname, String... oriPathArr) {
        for (String oriPathSV : oriPathArr) {
            for (String oriPathV : oriPathSV.split(",")) {
                List<File> allFileList = FileUtil.loopFiles(oriPathV).stream().filter(f -> !f.getName().contains(".DS_Store")).collect(Collectors.toList());
                for (File f : allFileList) {
                    handleContent(translateDictPathname, f.getAbsolutePath());
                }
            }
        }
    }

    public void handleContent(String translateDictPathname, String oriPathname) {
        // 1 预处理
        preHandleContent(translateDictPathname, oriPathname);

        // 2 将新的词句加入字典
        addOtherWaitTranslate();

        // 3 如果有新词汇则等待人工操作 !!删除等待标识即可结束等待
        waitHandle(getTranslateDictFile(), WAIT_FLAG_STR);

        // 4 获取翻译字典所有内容
        translateDictToMap();

        // 5 完成所有替换操作
        handleResult();

        // 5-1替换并复制源文件副本文件
        replaceAndDuplicateFile();

        // 6 生成properties信息
        generatePropertiesInfo();

        // 7 生成properties文件
        generateOutPropsFiles();

        // // 7-1 打印properties信息
        // printPropertiesInfo();

    }

    private void printPropertiesInfo() {
        getOutPropsMap().forEach((l, s) -> {
            System.out.println(l);
            s.forEach(System.out::println);
        });
    }

    private void generateOutPropsFiles() {
        getOutPropsMap().forEach((l, s) -> {
            String propsNameV = getOutPropsPathnameFunc().apply(getTranslateDictPathname(), l);
            System.out.printf("获取/生成文件: %1$s\n", propsNameV);
            // 获取/生成翻译后的字典
            File propsOutFileV = getOrCreateFile(propsNameV, "#最终结果\n");
            Props propsV = new Props(propsOutFileV, StandardCharsets.UTF_8);
            boolean thisTimeHasOtherContentFlagV = false;
            for (String ws : s) {
                String[] thisPropsKV = ws.split("=");
                if (!Objects.equals(propsV.get(thisPropsKV[0]), thisPropsKV[1])) {
                    if (!thisTimeHasOtherContentFlagV) {
                        FileUtil.appendUtf8String(String.format("\n#region %s\n", LocalDateTime.now()), propsOutFileV);
                        thisTimeHasOtherContentFlagV = true;
                    }
                    FileUtil.appendUtf8String(String.format("%s\n", ws), propsOutFileV);
                }
            }
            if (thisTimeHasOtherContentFlagV) {
                FileUtil.appendUtf8String(String.format("#endregion"), propsOutFileV);
            }
        });
    }

    public void generatePropertiesInfo() {
        getLocaleMapping().values().forEach(v -> getOutPropsMap().put(v, new LinkedHashSet<>()));
        // 用到的翻译Set 每个Item对应的翻译
        for (TranslateItem tItemV : getTempWaitReplaceSearchSet()) {
            tItemV.getTranslateMap().forEach((k, v) -> {
                String propsTranslateResult = tItemV.getPropsKey() + "=" + v;
                tItemV.addPropsTranslate(k, propsTranslateResult);
                getOutPropsMap().get(k).add(propsTranslateResult);
            });
        }
    }

    private void replaceAndDuplicateFile() {
        File duplicateFile = new File(getDuplicatePathname().apply(getOriFile()));
        if (!FileUtil.exist(duplicateFile.getParentFile())) {
            throw new RuntimeException("副本文件父地址不存在");
        }
        FileUtil.copy(getOriFile(), duplicateFile, true);
        FileUtil.writeUtf8String(getResultContent(), getOriFile());
    }

    private void handleResult() {
        // 正式开始替换
        String tempContent = getResultContentStack().peek().getTempContent();
        String resultContent = tempContent;
        // 替换方法 TODO 性能有必要的话可以优化
        for (TranslateItem tItemV : getTempWaitReplaceSearchSet()) {
            String nowReplaceStr;
            if (!tItemV.getCategoryInfo().isIgnore()) {
                // 获取翻译
                tItemV.addTranslate(getTranslateDictMap().get(tItemV.getSearchContent()));
                // 获取props的key
                tItemV.setPropsKey(getPropsKeyNameFunc().apply(getOriPathname(), tItemV));
                nowReplaceStr = tItemV.getCategoryInfo().getReplaceStr(tItemV.getPropsKey());
            } else {
                nowReplaceStr = tItemV.getSearchContent();
            }
            tItemV.setNowReplaceStr(nowReplaceStr);// 只是用于展示
            resultContent = StrUtil.replace(resultContent, tItemV.getNowWaitReplaceStr(), tItemV.getNowReplaceStr());
        }
        setResultContent(resultContent);
        getResultContentStack().add(new TranslateResult("最终结果", tempContent, resultContent));
    }

    public Map<String, Map<Locale, String>> translateDictToMap() {
        ArrayList<Locale> localeList = CollUtil.newArrayList(getLocaleMapping().values());
        Props props = new Props(getTranslateDictFile(), StandardCharsets.UTF_8);
        setTranslateDictMap(MapUtil.newHashMap(props.size()));
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            String k = entry.getKey().toString();
            String v = entry.getValue().toString();
            // 值分割后的数组
            List<String> translateContentList = StrSpliter.split(v, PROPS_SPLIT_STR, 0, false, false);
            translateContentList.add(0, k);
            if (translateContentList.size() > 1 && (StrUtil.isNotBlank(translateContentList.get(1)))) {
                Map<Locale, String> localeTranslateMap = new LinkedHashMap<>();
                for (int i = 0; i < translateContentList.size(); i++) {
                    // 当前值的语言
                    Locale thisLocale = localeList.get(i);
                    // 代表不支持此类型
                    if (thisLocale == null) {
                        continue;
                    }
                    // 当前值
                    localeTranslateMap.put(thisLocale, translateContentList.get(i));

                }
                getTranslateDictMap().put(k, localeTranslateMap);
            }
        }
        return getTranslateDictMap();

    }

    // 等待文件中 字符消失
    private static void waitHandle(File file, String waitFlagStr) {
        // 等待人工操作
        // int needRemoveNum = 1;
        for (int i = 1; true; i++) {
            String propsContent = FileUtil.readUtf8String(file);
            if (propsContent.contains(waitFlagStr)) {
                if (i == 1) {
                    String pathname = file.getAbsolutePath();
                    open(pathname);
                    System.out.printf("将文件[%s]中的 %s 删除掉即处理完成\n", pathname, waitFlagStr);
                    System.out.println("等待中...已等待(秒) 0");
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("等待中...已等待(秒) %s\n", 2 * i);
                // for (int j = 0; j < needRemoveNum; j++) {
                //     System.out.print("\b");
                // }
                // needRemoveNum = String.valueOf(i * 2).length();
                // System.out.print(i * 2);
            } else {
                break;
            }
        }
        System.out.println("结束等待, 开始处理");
    }

    private void preHandleContent(String translateDictPathname, String oriPathname) {
        this.oriPathname = Objects.requireNonNull(oriPathname, "文件地址不能为空");
        this.translateDictPathname = translateDictPathname;
        // 获取/生成翻译字典文件
        this.translateDictFile = getOrCreateFile(getTranslateDictPathname(), getNotExistPropsOutStr());
        System.out.printf("开始文件%s\n", getOriPathname());
        this.oriFile = Optional.of(new File(oriPathname)).filter(FileUtil::exist).orElseThrow(() -> new RuntimeException("未找到源文件"));
        this.oriContent = FileUtil.readUtf8String(getOriFile());
        this.tempReplaceStrI = new AtomicInteger();
        this.tempWaitReplaceSearchSet = new TreeSet<>(translateItemComparatorByNowWait);
        this.resultContentStack = new Stack<>();
        this.outPropsMap = new LinkedHashMap<>();

        getResultContentStack().add(new TranslateResult("原内容", getOriContent(), getOriContent()));
        // 替换寻找到符合条件的内容到缓存
        for (CategoryInfo cInfoV : getCategoryList()) {
            cInfoV.getSearchSet().clear();
            // 带有延迟寻找方法 等下一步进行
            if (cInfoV.getDelayedSearchFunc() != null) {
                continue;
            }
            // 寻找符合条件的内容
            cInfoV.addSearchResult(cInfoV, cInfoV.getSearchFunc().apply(getOriContent()));
            // 没有匹配的内容
            if (CollUtil.isEmpty(cInfoV.getSearchSet())) {
                continue;
            }
            // 预处理替换 并 将此次结果放入结果堆栈中
            resultContentStack.add(replacementTempResults(resultContentStack.peek(), cInfoV));
        }

        // 寻找替换后, 进行延迟寻找符合条件的内容到缓存
        for (CategoryInfo cInfoV : getCategoryList()) {
            // 带有延迟寻找方法
            if (cInfoV.getDelayedSearchFunc() == null) {
                continue;
            }
            // 寻找符合条件的内容
            cInfoV.addSearchResult(cInfoV, cInfoV.getDelayedSearchFunc().apply(getOriContent(), resultContentStack.peek().getTempContent()));
            // 没有匹配的内容
            if (CollUtil.isEmpty(cInfoV.getSearchSet())) {
                continue;
            }
            // 替换 并 将此次结果放入结果堆栈中
            resultContentStack.add(replacementTempResults(resultContentStack.peek(), cInfoV));
        }
    }

    /**
     * 将目前缓存内容以目前的分类处理 TODO 性能有必要的话可以优化
     */
    private TranslateResult replacementTempResults(TranslateResult result, CategoryInfo cInfoV) {
        String tempContent = result.getTempContent();
        for (TranslateItem tItemV : cInfoV.getSearchSet()) {
            String replaceStr = getTempReplaceStrFunc().get();
            tempContent = StrUtil.replace(tempContent, tItemV.getNowWaitReplaceStr(), replaceStr);
            // tempContent = tempContent.replaceAll(tItemV.getNowWaitReplaceStr(), replaceStr);
            tItemV.setNowWaitReplaceStr(replaceStr);
            // 当前的待替换字符
            getTempWaitReplaceSearchSet().add(tItemV.setNowWaitReplaceStr(replaceStr));
        }
        return new TranslateResult(cInfoV.getName(), tempContent);
    }

    // public void printFound() {
    //     this.categoryList.forEach(c -> {
    //         System.out.printf("\n-------------------- %s --------------------\n", c.getName());
    //         c.getSearchSet().forEach(System.out::println);
    //     });
    //
    // }

    /**
     * 翻译结果类
     */
    @Data
    @Accessors(chain = true)
    static class TranslateResult {
        private String name;
        private String tempContent;
        private String resultContent;

        public TranslateResult(String name, String tempContent) {
            this.name = name;
            this.tempContent = tempContent;
        }

        public TranslateResult(String name, String tempContent, String resultContent) {
            this.name = name;
            this.tempContent = tempContent;
            this.resultContent = resultContent;
        }
    }

    /**
     * 每个分类的信息
     */
    @Data
    public static class CategoryInfo {
        // 分类名称
        private String name;
        // 忽略
        private boolean ignore;
        /**
         * 查找方法
         * @param 1 原文本
         * @param 2 找到的内容列表
         */
        private Function<String, Collection<String>> searchFunc;
        /**
         * 延迟寻找方法(当searchFunc寻找完毕并替换完毕后执行)
         * @param 1 原文本
         * @param 2 将 查询方法{@link #searchFunc}结果 + 上一次分类寻找结果 剔除掉后的文本
         * @param 3 找到的内容列表
         */
        private BiFunction<String, String, Collection<String>> delayedSearchFunc;
        @Getter(AccessLevel.NONE)
        private Function<String, String> waitReplaceFunc;
        @Getter(AccessLevel.NONE)
        private Function<String, String> replaceFunc;

        // 找到的所有内容
        private final Set<TranslateItem> searchSet = new TreeSet<>(translateItemComparator);
        // // 翻译后的所有内容
        // private final Map<String, TranslateItem> translateResultMap = new TreeMap<>(translateComparator);

        // 添加查询结果
        public void addSearchResult(CategoryInfo categoryInfo, Collection<String> searchColl) {
            for (String content : searchColl) {
                searchSet.add(new TranslateItem(categoryInfo, content, getWaitReplaceStr(content)));
            }
        }

        // 获取待替换内容
        public String getWaitReplaceStr(String str) {
            return waitReplaceFunc.apply(str)// TODO 特殊符号处理 文本替换时
                    // .replaceAll("\\[", "\\\\[")
                    // .replaceAll("\\)", "\\\\)")
                    // .replaceAll("\\(", "\\\\(")
                    ;
        }

        // 获取该替换成的内容
        public String getReplaceStr(String str) {
            return replaceFunc.apply(str);
        }

        //region 构造方法
        CategoryInfo(String name, boolean ignore, Function<String, String> waitAndReplaceFunc, Function<String, Collection<String>> searchFunc) {
            this(name, ignore, waitAndReplaceFunc, waitAndReplaceFunc, searchFunc);
        }

        CategoryInfo(String name, boolean ignore, Function<String, String> waitAndReplaceFunc, BiFunction<String, String, Collection<String>> delayedSearchFunc) {
            this(name, ignore, waitAndReplaceFunc, waitAndReplaceFunc, delayedSearchFunc);
        }

        CategoryInfo(String name, boolean ignore,
                     Function<String, String> waitReplaceFunc,
                     Function<String, String> replaceFunc,
                     Function<String, Collection<String>> searchFunc) {
            this(name, ignore, waitReplaceFunc, replaceFunc, searchFunc, null);

        }

        CategoryInfo(String name, boolean ignore,
                     Function<String, String> waitReplaceFunc,
                     Function<String, String> replaceFunc,
                     BiFunction<String, String, Collection<String>> delayedSearchFunc) {
            this(name, ignore, waitReplaceFunc, replaceFunc, null, delayedSearchFunc);
        }

        private CategoryInfo(String name, boolean ignore,
                             Function<String, String> waitReplaceFunc,
                             Function<String, String> replaceFunc,
                             Function<String, Collection<String>> searchFunc,
                             BiFunction<String, String, Collection<String>> delayedSearchFunc) {
            this.name = Objects.requireNonNull(name, "不能为空");
            this.ignore = ignore;
            this.waitReplaceFunc = Objects.requireNonNull(waitReplaceFunc, "不能为空");
            this.replaceFunc = Objects.requireNonNull(replaceFunc, "不能为空");
            if (searchFunc == null && delayedSearchFunc == null) {
                throw new RuntimeException("searchFunc和delayedSearchFunc不能都为空");
            }
            this.searchFunc = searchFunc;
            this.delayedSearchFunc = delayedSearchFunc;
        }
        //endregion


        @Override
        public String toString() {
            return "{" +
                    "\"name\":\"" + name + '\"' +
                    ",\"ignore\":" + ignore +
                    "}";
        }
    }

    /**
     * 翻译单词类
     */
    @Data
    @Accessors(chain = true)
    static class TranslateItem {
        // 分类
        private CategoryInfo categoryInfo;
        // 匹配到的内容
        private String searchContent;
        private String nowWaitReplaceStr;
        private String nowReplaceStr;
        private String propsKey;
        // 匹配到的内容的翻译
        private final Map<Locale, String> translateMap = MapUtil.newHashMap(4);
        // 匹配到的内容的翻译
        private final Map<Locale, String> propsTranslateMap = MapUtil.newHashMap(4);

        public TranslateItem(CategoryInfo categoryInfo, String searchContent, String nowWaitReplaceStr) {
            this.categoryInfo = categoryInfo;
            this.searchContent = searchContent;
            this.nowWaitReplaceStr = nowWaitReplaceStr;
        }

        public TranslateItem addTranslate(Map<Locale, String> translateMap) {
            getTranslateMap().putAll(translateMap);
            return this;
        }

        public TranslateItem addTranslate(Locale locale, String translateResult) {
            getTranslateMap().put(locale, translateResult);
            return this;
        }

        public TranslateItem addPropsTranslate(Map<Locale, String> propsTranslateMap) {
            getPropsTranslateMap().putAll(propsTranslateMap);
            return this;
        }

        public TranslateItem addPropsTranslate(Locale locale, String propsTranslateResult) {
            getPropsTranslateMap().put(locale, propsTranslateResult);
            return this;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"categoryInfo\":" + categoryInfo +
                    ",\"searchContent\":\"" + searchContent + '\"' +
                    ",\"translateMap\":" + translateMap +
                    "}";
        }
    }

    /**
     * 将properties不存在的字段加入其中
     */
    private void addOtherWaitTranslate() {
        Set<String> waitTranslateSet = getTempWaitReplaceSearchSet().stream()
                .filter(o -> !o.getCategoryInfo().isIgnore())
                .map(TranslateItem::getSearchContent).collect(Collectors.toSet());
        Props props = new Props(getTranslateDictFile(), StandardCharsets.UTF_8);
        boolean thisTimeHasOtherContentFlag = false;
        for (String ws : waitTranslateSet) {
            if (!props.containsKey(ws)) {
                if (!thisTimeHasOtherContentFlag && (thisTimeHasOtherContentFlag = true)) {
                    FileUtil.appendUtf8String(String.format("\n#region %s\n", LocalDateTime.now()), getTranslateDictFile());
                }
                StringBuilder sb = new StringBuilder(ws + "=");
                // try {
                //     sb.append(translateApi(ws, allLocaleList.get(0), allLocaleList.get(1)));
                // } catch (Exception e) {
                //     System.out.println("api调用异常");
                //     e.printStackTrace();
                //     // throw new RuntimeException("api调用异常");
                // }
                sb.append("\n");
                FileUtil.appendUtf8String(sb.toString(), getTranslateDictFile());
            }
        }
        if (thisTimeHasOtherContentFlag) {
            FileUtil.appendUtf8String(String.format("#endregion %s", WAIT_FLAG_STR), getTranslateDictFile());
        }
    }

    /**
     * 翻译第三方接口
     * @param word
     * @param from
     * @param to
     * @return
     * @throws UnsupportedEncodingException
     */
    private String translateApi(String word, Locale from, Locale to) throws UnsupportedEncodingException {
        return new JSONArray(HttpUtil.createGet(String.format("https://translate.googleapis.com/translate_a/single?client=gtx&sl=%1$s&tl=%2$s&dt=t&q=%3$s",
                from, to, URLEncoder.encode(word, "UTF-8")))
                .header("User-Agent", "Mozilla/5.0")
                .execute().body()).getJSONArray(0).getJSONArray(0).getStr(0);
    }

    /**
     * 获取properties文件, 如不存在则新建
     */
    public static File getOrCreateFile(String pathname, String ifNotExistOutStr) {
        File translateFile = new File(pathname);
        File translateParentFile = translateFile.getParentFile();
        if (!translateParentFile.exists()) {
            throw new RuntimeException("目录不存在");
        }
        if (!FileUtil.exist(translateFile)) {
            FileUtil.writeUtf8String(ifNotExistOutStr, translateFile);
        }
        return translateFile;
    }

    public static List<String> findAllGroup(String patternStr, CharSequence content, int... groupArr) {
        if (groupArr.length == 0) {
            return findAllGroup(patternStr, content, new ArrayList<>(), 0);
        }
        return findAllGroup(patternStr, content, new ArrayList<>(), groupArr);
    }

    public static <T extends Collection<String>> T findAllGroup(String patternStr, CharSequence content, T collection, int... groupArr) {
        if (null == patternStr || null == content) {
            return null;
        }

        if (null == collection) {
            throw new NullPointerException("Null collection param provided!");
        }

        Pattern pattern = Pattern.compile(patternStr);

        final Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            for (int groupV : groupArr) {
                collection.add(matcher.group(groupV));
            }
        }
        return collection;
    }

    private static void open(String pathOrPathname) {
        // java.class.path
        // ReUtil.findAllGroup0(Pattern.compile("^.*?idea.*?(?=/)", Pattern.CASE_INSENSITIVE), str)
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
}
