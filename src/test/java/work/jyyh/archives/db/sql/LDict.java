package work.jyyh.archives.db.sql;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.NonNull;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

/**
 * TODO
 * @author lgren
 * @since 2020-12-22 3:20 下午
 */
@Data
public class LDict {
    private String pathname;
    private File file;
    private List<String> lineList;

    private Map<Object, Set<String>> repeatMap;

    private LTable<Object, String> kVTable;
    private Object[] dictMappingArr;

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private String SPLIT_STR = "<=>";
    private int keyI = 0;


    //region 构造
    public LDict(String pathname, Object[] dictMappingArr) {
        this(new File(pathname), dictMappingArr);
    }

    public LDict(File file, Object[] dictMappingArr) {
        this(file, DEFAULT_CHARSET, dictMappingArr);
    }

    public LDict(File file, Charset charset, Object[] dictMappingArr) {
        init(file, charset, dictMappingArr);
    }

    private void init(File file, Charset charset, Object[] dictMappingArr) {
        this.dictMappingArr = dictMappingArr;
        this.pathname = file.getAbsolutePath();
        this.file = file;
        this.lineList = new LinkedList<>();
        this.repeatMap = new LinkedHashMap<>();
        this.kVTable = new LTable<>();
        FileUtil.readLines(getFile(), charset, (LineHandler) line -> {
            if (StrUtil.isNotBlank(line) && !Objects.equals(line.trim().charAt(0), '#')) {
                lineList.add(line);
                String[] kvArr = line.split(SPLIT_STR);
                String key = kvArr[keyI];// 源寻找数据
                for (int i = 0; i < kvArr.length; i++) {
                    if (dictMappingArr.length > i) {
                        // 当前的映射类型
                        Object nowType = dictMappingArr[i];
                        String oldV = kVTable.put(kvArr[i], key, nowType);
                        if (oldV != null) {
                            repeatMap.computeIfAbsent(nowType, k -> new LinkedHashSet<>())
                                    .add(kvArr[0]);
                        }
                    }
                }
            }
        });
    }
    //endregion

    public boolean checkKeyAndValueRepeat() {
        boolean isRepeat = false;
        if (isKeyRepeat()) {
            System.out.println("----------------------------------------");
            System.out.println("警告! 有重复的key值! 输出如下:");
            repeatMap.get(dictMappingArr[keyI]).forEach(System.out::println);
            System.out.println("----------------------------------------\n");
            isRepeat = true;
        }
        if (isValueRepeat()) {
            repeatMap.forEach((k,v) -> {
                if (Objects.equals(k, dictMappingArr[keyI])) {
                    return;
                }
                System.out.println("----------------------------------------");
                System.out.println("警告! [" + k + "]有重复的value值! 输出如下:");
                v.forEach(System.out::println);
                System.out.println("----------------------------------------\n");
            });
            isRepeat = true;
        }
        return isRepeat;
    }

    public boolean containsKey(Object key, Object... keyArr) {
        return kVTable.containsKey(key, keyArr);
    }

    public boolean containsLine(Object line) {
        return lineList.contains(line);
    }

    public boolean isKeyRepeat() {
        return !Optional.ofNullable(repeatMap.get(dictMappingArr[keyI])).map(Set::isEmpty).orElse(true);
    }

    public boolean isValueRepeat() {
        return !repeatMap.isEmpty() && repeatMap.size() > 1;
    }

    public void addIfNot(@NonNull Collection<String> waitAddSetLine) {
        LDict checkProps = this;

        boolean thisTimeHasOtherContentFlag = false;
        for (String ws : new LinkedHashSet<>(waitAddSetLine)) {
            // 不存在此字段key值时
            if (!checkProps.containsKey(ws.split(SPLIT_STR)[keyI])) {
                if (!thisTimeHasOtherContentFlag && (thisTimeHasOtherContentFlag = true)) {
                    FileUtil.appendUtf8String(String.format("\n", LocalDateTime.now()), getFile());
                    // FileUtil.appendUtf8String(String.format("\n#region %s\n", LocalDateTime.now()), getFile());
                }
                FileUtil.appendUtf8String(String.format("%s%s\n", ws, SPLIT_STR), getFile());
            }
        }
        if (thisTimeHasOtherContentFlag) {
            // FileUtil.appendUtf8String("#endregion", getFile());
            init(getFile(), DEFAULT_CHARSET, dictMappingArr);
        }
    }

    @Override
    public String toString() {
        return kVTable.toString();
    }
}
