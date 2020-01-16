package com.lgren.office.openoffice.lgren;

/**
 * TODO
 * @author lgren
 * @since 2019-12-24 11:16
 */
public class DocumentProperties {
    private static String openOfficeHost = "192.168.28.94";
    private static Integer openOfficePost = 8100;
    // # 最大连接数
    private static Integer openOfficeMaxTotal = 70;
    // # 最大空闲连接数
    private static Integer openOfficeMaxIdle = 5;
    // # 最小空闲连接数
    private static Integer openOfficeMinIdle = 3;
    // # 转换后的路径
    private static String officeToViewOutPath = "/Users/lgren/Project/JavaIdeaSpace/test/src/main/resource/office/pdf/";
    // # 转换后的文件类型 ".html" ".pdf"
    private static String officeToViewOutType = ".pdf";

    public static String getOpenOfficeHost() {
        return openOfficeHost;
    }

    public static Integer getOpenOfficePost() {
        return openOfficePost;
    }

    public static Integer getOpenOfficeMaxTotal() {
        return openOfficeMaxTotal;
    }

    public static Integer getOpenOfficeMaxIdle() {
        return openOfficeMaxIdle;
    }

    public static Integer getOpenOfficeMinIdle() {
        return openOfficeMinIdle;
    }

    public static String getOfficeToViewOutPath() {
        return officeToViewOutPath;
    }

    public static String getOfficeToViewOutType() {
        return officeToViewOutType;
    }
}
