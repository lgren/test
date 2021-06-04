package base;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * TODO
 * @author lgren
 * @since 2020-04-07 9:44 上午
 */
public class BaseTest {
    @Test
    public void name() {
        boolean b1 = false;
        boolean b2 = true;
        System.out.println(b1 | b2);
    }

    @Test
    public void systemInfo() {
        System.out.println("Java运行时环境版本:" + System.getProperty("java.version"));
        System.out.println("Java 运行时环境供应商:" + System.getProperty("java.vendor"));
        System.out.println("Java 供应商的URL:" + System.getProperty("java.vendor.url"));
        System.out.println("Java安装目录:" + System.getProperty("java.home"));
        System.out.println("Java 虚拟机规范版本:" + System.getProperty("java.vm.specification.version"));
        System.out.println("Java 类格式版本号:" + System.getProperty("java.class.version"));
        System.out.println("Java类路径：" + System.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表:" + System.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径:" + System.getProperty("java.io.tmpdir"));
        System.out.println("要使用的 JIT 编译器的名称:" + System.getProperty("java.compiler"));
        System.out.println("一个或多个扩展目录的路径:" + System.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称:" + System.getProperty("os.name"));
        System.out.println("操作系统的架构:" + System.getProperty("os.arch"));
        System.out.println("操作系统的版本:" + System.getProperty("os.version"));
        System.out.println("文件分隔符（在 UNIX 系统中是“/”）:" + System.getProperty("file.separator"));
        System.out.println("路径分隔符（在 UNIX 系统中是“:”）:" + System.getProperty("path.separator"));
        System.out.println("行分隔符（在 UNIX 系统中是“/n”）:" + System.getProperty("line.separator"));
        System.out.println("用户的账户名称:" + System.getProperty("user.name"));
        System.out.println("用户的主目录:" + System.getProperty("user.home"));
        System.out.println("用户的当前工作目录:" + System.getProperty("user.dir"));
        System.out.println("当前的classpath的绝对路径的URI表示法:" + Thread.currentThread().getContextClassLoader().getResource(""));
        System.out.println("得到的是当前的classpath的绝对URI路径:" + BaseTest.class.getResource("/"));
        System.out.println("得到的是当前类BaseTest.class文件的URI目录:" + BaseTest.class.getResource(""));
    }

    @Test
    public void name1() {
        System.out.println(Timestamp.class);
    }

    // 仅仅保存
    private static final int UPLOAD_FEATURE_DEFAULT = 0;
    // 不用保存源文件
    private static final int UPLOAD_FEATURE_NOT_SAVE_SOURCE = 0x00000001;
    // 计算MD5值
    private static final int UPLOAD_FEATURE_COMPUTE_MD5 = 0x00000002;
    // 转换成HTML并返回
    private static final int UPLOAD_FEATURE_CONVERT_HTML_RETURN = 0x00000004;
    // 转换成HTML并保存
    private static final int UPLOAD_FEATURE_CONVERT_HTML_SAVE = 0x00000008;

    @Test
    public void name2() {
        int feature = UPLOAD_FEATURE_NOT_SAVE_SOURCE | UPLOAD_FEATURE_COMPUTE_MD5;
        System.out.println((feature & UPLOAD_FEATURE_NOT_SAVE_SOURCE) != 0);// 包含特征则true
        System.out.println((feature & UPLOAD_FEATURE_COMPUTE_MD5) != 0);// 包含特征则true
        System.out.println((feature & (UPLOAD_FEATURE_NOT_SAVE_SOURCE | UPLOAD_FEATURE_COMPUTE_MD5)) != 0);// 包含特征则true
        System.out.println((feature ^ (UPLOAD_FEATURE_NOT_SAVE_SOURCE | UPLOAD_FEATURE_COMPUTE_MD5)) == 0);// 全等特征则true
        System.out.println((feature & (UPLOAD_FEATURE_CONVERT_HTML_RETURN | UPLOAD_FEATURE_COMPUTE_MD5)) != 0);// 包含特征则true
        System.out.println((feature ^ (UPLOAD_FEATURE_CONVERT_HTML_RETURN | UPLOAD_FEATURE_NOT_SAVE_SOURCE | UPLOAD_FEATURE_COMPUTE_MD5)) == 0);// 全等特征则true
    }

    @Test
    public void name3() {
        System.out.println(0x00000010);
    }

    public static final int ANSWER_TYPE_NOT_MATCH = 0;// 未找到
    public static final int ANSWER_TYPE_ONLY = 0x00000001;// 只有一条数据
    public static final int ANSWER_TYPE_MORE = 0x00000002;// 多条数据

    public static final int ANSWER_TYPE_PRECISE = 0x00000010;// 直接匹配
    public static final int ANSWER_TYPE_MULTI_LAYER = 0x00000020;// 多级回复（办事指南）
    public static final int ANSWER_TYPE_LIST = 0x00000040;// 列表引导回复
    public static final int ANSWER_TYPE_DYNAMIC = 0x00000080;// 动态问答回复



    // 添加特征
    public static int addFeature(int... features) {
        return features.length == 0 ? 0 : Arrays.stream(features).collect(() -> new int[1], (r, f) -> r[0] |= f, (r1, r2) -> r1[0] |= r2[0])[0];
    }

    // 检查特征 待检测数需要满足所有特征才会返回true
    public static boolean hasFeature(int waitCheck, int... features) {
        return features.length == 0 || Arrays.stream(features).allMatch(f -> (waitCheck & f) != 0);
    }

    // // JS写法
    // function addFeature (...features) {
    //     return !features.length ? 0 : features.reduce((r, f) => (r |= f), 0)
    // }

    // function hasFeature(waitCheck, ...features) {
    //     return !features.length || features.every(f => waitCheck & f)
    // }

    @Test
    public void name4() {
        System.out.println(addFeature(ANSWER_TYPE_ONLY));
        System.out.println(hasFeature(addFeature(ANSWER_TYPE_ONLY, ANSWER_TYPE_MORE, ANSWER_TYPE_PRECISE), ANSWER_TYPE_DYNAMIC, ANSWER_TYPE_MORE));
        System.out.println(RandomUtil.randomEle(Lists.<Integer>newArrayList(1)));
    }
}
