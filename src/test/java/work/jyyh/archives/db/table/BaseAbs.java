package work.jyyh.archives.db.table;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 * @author lgren
 * @since 2020-12-18 12:24 下午
 */
@Data
public abstract class BaseAbs {
    @Setter(AccessLevel.NONE)
    private String propsPreKey;
    private final Map<Locale, Map<Field, String>> translateMap = new HashMap<>();

    public boolean notChange() {
        return getTranslateMap().isEmpty();
    }

    public BaseAbs addTranslate(Locale locale, Field field, String translate) {
        getTranslateMap().put(locale, MapUtil.of(field, translate));
        return this;
    }

    public String getTableName() {
        return getTableName(this.getClass());
    }

    public String getTableIdName() {
        return getTableIdName(this.getClass());
    }

    public List<Field> getWaitTranslateFieldList() {
        return getWaitTranslateFieldList(this.getClass());
    }

    public Map<String, Object> getWhereFieldList() {
        Map<String, Object> fieldNameList = new HashMap<>();
        for (Field field : ReflectUtil.getFields(this.getClass())) {
            Boolean enableTranslate = AnnotationUtil.getAnnotationValue(field, BaseAbs.Where.class);
            if (enableTranslate != null && enableTranslate) {
                String fieldName = AnnotationUtil.getAnnotationValue(field, Where.class, "name");
                try {
                    field.setAccessible(true);
                    Object value = field.get(this);
                    fieldNameList.put(StrUtil.isBlank(fieldName) ? field.getName() : fieldName, "'" + value + "'");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return fieldNameList;
    }

    public String getSql() {
        String setSql = getWaitTranslateFieldList().stream().map(f -> getTableFieldName(f) + "='" + getPropsKey(f) + "'").collect(Collectors.joining(", "));
        String whereSql = MapUtil.join(getWhereFieldList(), " and ", "=");

        return MessageFormat.format("update {2} set {0} where {1};", setSql, whereSql, getTableName());
    }

    public String getPropsKeyBase(Field field) {
        String tableFieldName = getTableFieldName(field);
        return MessageFormat.format("{0}_{1}_{2}",
                getTableName(),
                getTableIdName(),
                tableFieldName);
    }

    // 获取properties的key
    public String getPropsKey(Field field) {
        // 0表名_1ID名_2字段名
        String baseStr = getPropsKeyBase(field);
        String propsPreKey = getPropsPreKey();
        // 0表名_1ID名_2字段名_3英文驼峰式翻译名
        return MessageFormat.format("{0}_{1}", baseStr, propsPreKey);
    }

    // 获取properties的的一行
    public String getPropsLine(Field field, String translate) {
        // 0表名_1ID名_2字段名_3英文驼峰式翻译名=4翻译
        return MessageFormat.format("{0}={1}", getPropsKey(field), translate);
    }

    public static <T extends BaseAbs> String getTableName(Class<T> tableClass) {
        String table = AnnotationUtil.getAnnotationValue(tableClass, BaseAbs.Translate.class, "name");
        table = StrUtil.isNotBlank(table) ? table : tableClass.getSimpleName().toLowerCase();// 默认为类名的小写
        return table;
    }

    public static <T extends BaseAbs> String getTableIdName(Class<T> tableClass) {
        for (Field field : ReflectUtil.getFields(tableClass)) {
            String idNameV = AnnotationUtil.getAnnotationValue(field, BaseAbs.Id.class);
            if (idNameV != null) {
                return StrUtil.isNotBlank(idNameV) ? idNameV : field.getName();
            }
        }
        throw new RuntimeException("未设置ID");
    }

    public static int getTableFieldLimitSize(Field field) {
        return AnnotationUtil.getAnnotationValue(field, Translate.class, "limitSize");
    }

    public static String getTableFieldName(Field field) {
        String fieldName = AnnotationUtil.getAnnotationValue(field, Translate.class, "name");
        return StrUtil.isBlank(fieldName) ? field.getName() : fieldName;
    }

    public static <T extends BaseAbs> List<Field> getWaitTranslateFieldList(Class<T> tableClass) {
        List<Field> fieldNameList = new ArrayList<>();
        for (Field field : ReflectUtil.getFields(tableClass)) {
            Boolean enableTranslate = AnnotationUtil.getAnnotationValue(field, BaseAbs.Translate.class);
            if (enableTranslate != null && enableTranslate) {
                // fieldNameList.add(getFieldName(field));
                fieldNameList.add(field);
            }
        }
        return fieldNameList;
    }

    private static int count = 0;
    public void setPropsPreKey(Field field, String propsPreKey) {
        String tableFieldName = getTableFieldName(field);
        String baseStr = getPropsKeyBase(field);
        int limitSize = getTableFieldLimitSize(field);
        if (limitSize > 0) {
            int size = limitSize - baseStr.length();
            if (propsPreKey.length() > size) {
                String info = MessageFormat.format("表[{4}].字段[{0}]设置[{1}]超长.\n" +
                                "此字段总长度为:{5}, 长度不能超过:{3}, 此字段值长度为:{2}",
                        tableFieldName,
                        propsPreKey,
                        propsPreKey.length(),
                        size,
                        getTableName(),
                        limitSize);
                System.out.println(info);
                count++;
                System.out.println("因此此字段将命名为[msg" + count + "]");
                // throw new RuntimeException(info);
                propsPreKey = "msg" + count;
                if (propsPreKey.length() > size) {
                    throw new RuntimeException("简化后长度为[" + propsPreKey.length() + "]依然超长, 请处理, 推荐增加表长度.");
                }
            }
        }
        this.propsPreKey = propsPreKey;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
    public @interface Translate {
        boolean value() default true;

        String name() default "";

        int limitSize() default 0;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
    public @interface Id {
        String value() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.METHOD})
    public @interface Where {
        boolean value() default true;

        String name() default "";
    }
}
