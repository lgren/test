package com.lgren.util;

import javax.sql.rowset.serial.SerialBlob;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 针对数据库字段blob进行处理的工具类
 * @author lgren
 * @since 2019-11-07 14:30
 */
public class BlobUtil {
    /**
     * 将列表的Blob转换成String
     * @param list 源list
     * @param blobFieldName 需要转换的字段名
     * @return 传入的源list
     */
    public static List<Map<String, Object>> handlerBlob(List<Map<String, Object>> list, String blobFieldName) {
        if (list == null || list.size() == 0) {
            return list;
        }
        for (Map<String, Object> map : list) {
            Blob blob = (Blob) map.get(blobFieldName);
            map.put(blobFieldName, blobToString(blob));
        }
        return list;
    }

    /** 将String转换成byte数组 */
    public static byte[] stringToByteArr(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return str.getBytes("UTF-8");
    }

    /** byte数组 转换成 String */
    public static String byteArrToString(byte[] byteArr) throws UnsupportedEncodingException {
        if (byteArr == null) {
            return null;
        }
        return new String(byteArr, "UTF-8");
    }

    /** String 转换成 Blob */
    public static Blob stringToBlob(String str) throws UnsupportedEncodingException, SQLException {
        if (str == null) {
            return null;
        }
        return new SerialBlob(str.getBytes("UTF-8"));
    }

    /** Blob 转换成 String */
    public static String blobToString(Blob blob) {
        if (blob == null) {
            return null;
        }
        String blobString = null;//blob 转 String
        try {
            blobString = new String(blob.getBytes(1L, (int) blob.length()),"UTF-8");
        } catch (UnsupportedEncodingException | SQLException e) {
            e.printStackTrace();
        }
        return blobString;
    }
}
