package com.lgren.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 通过文件流获取文件后缀
 * https://www.cnblogs.com/chenglc/p/7117847.html
 * @author lgren
 * @since 2020-06-10 5:27 下午
 */
public class LFileUtil {

    private static final Map<String, String> FILE_TYPE_MAP = new HashMap<>(64);

    static {
        FILE_TYPE_MAP.put("jpg", "FFD8FFE000104A464946");
        FILE_TYPE_MAP.put("png", "89504E470D0A1A0A0000");
        FILE_TYPE_MAP.put("gif", "47494638396126026F01");
        FILE_TYPE_MAP.put("tif", "49492A00227105008037");
        FILE_TYPE_MAP.put("bmp", "424D228C010000000000");
        FILE_TYPE_MAP.put("bmp", "424D8240090000000000");
        FILE_TYPE_MAP.put("bmp", "424D8E1B030000000000");
        FILE_TYPE_MAP.put("dwg", "41433130313500000000");
        FILE_TYPE_MAP.put("html", "3C21444F435459504520");
        FILE_TYPE_MAP.put("htm", "3C21646F637479706520");
        FILE_TYPE_MAP.put("css", "48544D4C207B0D0A0942");
        FILE_TYPE_MAP.put("js", "696B2E71623D696B2E71");
        FILE_TYPE_MAP.put("rtf", "7B5C727466315C616E73");
        FILE_TYPE_MAP.put("psd", "38425053000100000000");
        FILE_TYPE_MAP.put("eml", "46726F6D3A203D3F6762");
        FILE_TYPE_MAP.put("doc", "D0CF11E0A1B11AE10000");
        FILE_TYPE_MAP.put("vsd", "D0CF11E0A1B11AE10000");
        FILE_TYPE_MAP.put("mdb", "5374616E64617264204A");
        FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("pdf", "255044462D312E360D25");
        FILE_TYPE_MAP.put("rmvb", "2E524D46000000120001");
        FILE_TYPE_MAP.put("flv", "464C5601050000000900");
        FILE_TYPE_MAP.put("mp4", "00000020667479706973");
        FILE_TYPE_MAP.put("mp3", "49443303000000000F76");
        FILE_TYPE_MAP.put("mpg", "000001BA210001000180");
        FILE_TYPE_MAP.put("wmv", "3026B2758E66CF11A6D9");
        FILE_TYPE_MAP.put("wav", "524946464694C9015741");
        FILE_TYPE_MAP.put("avi", "52494646D07D60074156");
        FILE_TYPE_MAP.put("mid", "4D546864000000060001");
        FILE_TYPE_MAP.put("zip", "504B0304140000000800");
        FILE_TYPE_MAP.put("rar", "526172211A0700CF9073");
        FILE_TYPE_MAP.put("ini", "235468697320636F6E66");
        FILE_TYPE_MAP.put("jar", "504B03040A0000000000");
        FILE_TYPE_MAP.put("exe", "4D5A9000030000000400");
        FILE_TYPE_MAP.put("jsp", "3C25402070616765206C");
        FILE_TYPE_MAP.put("mf", "4D616E69666573742D56");
        FILE_TYPE_MAP.put("xml", "3C3F786D6C2076657273");
        FILE_TYPE_MAP.put("sql", "EFBBBF2F2A0D0A53514C");
        FILE_TYPE_MAP.put("java", "7061636B616765207765");
        FILE_TYPE_MAP.put("bat", "406563686F206F66660D");
        FILE_TYPE_MAP.put("gz", "1F8B0800000000000000");
        FILE_TYPE_MAP.put("properties", "6C6F67346A2E726F6F74");
        FILE_TYPE_MAP.put("class", "CAFEBABE0000002E0041");
        FILE_TYPE_MAP.put("chm", "49545346030000006000");
        FILE_TYPE_MAP.put("mxp", "04000000010000001300");
        FILE_TYPE_MAP.put("docx", "504B0304140006000800");
        FILE_TYPE_MAP.put("wps", "D0CF11E0A1B11AE10000");
        FILE_TYPE_MAP.put("torrent", "6431303A637265617465");
        FILE_TYPE_MAP.put("264", "494D4B48010100000200");
        FILE_TYPE_MAP.put("mov", "6D6F6F76");
        FILE_TYPE_MAP.put("wpd", "FF575043");
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F");
        FILE_TYPE_MAP.put("pst", "2142444E");
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F");
        FILE_TYPE_MAP.put("pwl", "E3828596");
        FILE_TYPE_MAP.put("ram", "2E7261FD");
        FILE_TYPE_MAP.put("raq", "52515152");
    }

    public static String getFileTypeByStream(byte[] bytes) {
        String fileTypeHex = String.valueOf(getFileHexString(bytes));
        Iterator var2 = FILE_TYPE_MAP.entrySet().iterator();

        Map.Entry entry;
        String fileTypeHexValue;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            entry = (Map.Entry) var2.next();
            fileTypeHexValue = (String) entry.getValue();
        } while (!fileTypeHex.toUpperCase().startsWith(fileTypeHexValue));

        return (String) entry.getKey();
    }

    private static String getFileHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bytes != null && bytes.length > 0) {
            byte[] var2 = bytes;
            int var3 = bytes.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                byte aByte = var2[var4];
                int v = aByte & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }
}
