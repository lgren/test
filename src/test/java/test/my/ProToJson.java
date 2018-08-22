package test.my;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;
/**
 * 需要导入的包: 1.fastjson
 *             2.commons-lang3
 *             3.commons-io
 *             4.guava
 */

/**
 * 将properties文件数据转换成 json 以及 XDiamond所需的json格式, 支持将对应数据的"上一行"或者"当前行的末尾"注释变成description <br/>
 * 可以传入1. File文件或者 文件地址 2. File文件所在的目录 或者 文件所在的目录地址 3.File[] 或者 String[] <br/>
 * 例如文件为 String fileStr = "E://mysql.properties"; <br/>
 * //1 <br/>
 * ProToJson.proToJSONForXD(fileStr); <br/>
 * //2 <br/>
 * File file = new File(fileStr); <br/>
 * ProToJson.proToJSONForXD(file); <br/>
 * @createDate 2018/8/14 14:02
 * @author Lgren
 */
public class ProToJson {
    /**
     * 单个普通任务
     * @createDate 2018/8/14 14:01
     * @author Lgren
     * @param resourceFile 资源文件
     * @param isOut 是否在原有的地址处输出成 txt
     */
    public static JSONObject proToJSON(File resourceFile, boolean isOut) {
        //输出结果
        JSONObject resultJson = new JSONObject();
        //获取properties文件信息
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(resourceFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties props = new Properties();
        try {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultJson = new JSONObject(new HashMap(props));
        if (isOut) {
            String aimName = resourceFile.getAbsolutePath().replaceAll("\\.properties", "\\.txt");
            out(resultJson, new File(aimName));
        }
        return resultJson;
    }

    /**
     * 单个普通任务
     * @createDate 2018/8/14 14:01
     * @author Lgren
     * @param resourceFile 资源文件
     */
    public static JSONObject proToJSON(File resourceFile) {
        return proToJSON(resourceFile, true);
    }

    /**
     * 单个普通任务
     * @createDate 2018/8/14 14:01
     * @author Lgren
     * @param resourceName 资源文件地址
     * @param isOut 是否在原有的地址处输出成 txt
     */
    public static JSONObject proToJSON(String resourceName, boolean isOut) {
        return proToJSON(new File(resourceName), isOut);
    }

    /**
     * 单个普通任务
     * @createDate 2018/8/14 14:01
     * @author Lgren
     * @param resourceName 资源文件地址
     */
    public static JSONObject proToJSON(String resourceName) {
        return proToJSON(resourceName, true);
    }

    /**
     * 多个普通任务
     * @createDate 2018/8/14 14:01
     * @author Lgren
     * @param resourceFiles 资源文件
     * @param isOut 是否在原有的地址处输出成 txt
     * @param isAdd 是否将所有的资源转换后的json结合在一起
     */
    public static JSONObject proToJSON(File[] resourceFiles, boolean isOut, boolean isAdd) {
        JSONObject jsonObjectList = new JSONObject();
        for (File file : resourceFiles) {
            if (isAdd) {
                jsonObjectList.putAll(proToJSON(file, isOut));
            } else {
                jsonObjectList.put(file.getName(), proToJSON(file, isOut));
            }
        }
        if (isAdd && isOut) {
            String aimName = resourceFiles[0].getParent() + "\\all.txt";
            out(jsonObjectList, new File(aimName));
        }
        return jsonObjectList;
    }

    /**
     * 多个普通任务
     * @createDate 2018/8/14 14:01
     * @author Lgren
     * @param resourceFiles 资源文件
     * @param isOut 是否在原有的地址处输出成 txt
     */
    public static JSONObject proToJSON(File[] resourceFiles, boolean isOut) {
        return proToJSON(resourceFiles, isOut, true);
    }

    /**
     * 多个普通任务
     * @createDate 2018/8/14 14:01
     * @author Lgren
     * @param resourceFiles 资源文件
     */
    public static JSONObject proToJSON(File[] resourceFiles) {
        return proToJSON(resourceFiles, true);
    }

    /**
     * 多个普通任务
     * @createDate 2018/8/14 14:01
     * @author Lgren
     * @param resourceNames 资源文件
     * @param isOut 是否在原有的地址处输出成 txt
     * @param isAdd 是否将所有的资源转换后的json结合在一起
     */
    public static JSONObject proToJSON(String[] resourceNames, boolean isOut, boolean isAdd) {
        File[] resourceFiles = new File[resourceNames.length];
        for (int i = 0; i < resourceNames.length; i++) {
            resourceFiles[i] = new File(resourceNames[i]);
        }
        return proToJSON(resourceFiles, isOut, isAdd);
    }

    /**
     * 多个普通任务
     * @createDate 2018/8/14 14:01
     * @author Lgren
     * @param resourceNames 资源文件
     * @param isOut 是否在原有的地址处输出成 txt
     */
    public static JSONObject proToJSON(String[] resourceNames, boolean isOut) {
        return proToJSON(resourceNames, isOut, true);
    }

    /**
     * 多个普通任务
     * @createDate 2018/8/14 14:01
     * @author Lgren
     * @param resourceNames 资源文件
     */
    public static JSONObject proToJSON(String[] resourceNames) {
        return proToJSON(resourceNames, true);
    }

    //单个配置中心json

    /**
     * 单个XDiamond的 json格式
     * @createDate 2018/8/14 14:03
     * @author Lgren
     * @param resourceFile 资源文件
     * @param isOut 是否在原有的地址处输出成 txt
     * @param keyField properties的 key的名字字段
     * @param valueField properties的 value的名字字段
     * @param descriptionField properties的 描述的名字字段
     */
    public static JSONArray proToJSONForXD(File resourceFile, boolean isOut, String keyField, String valueField, String descriptionField) {
        //输出结果
        JSONArray resultJson = new JSONArray();
        //获取properties文件信息
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(resourceFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties props = new Properties();
        try {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> descriptionMap = getDescription(resourceFile);
        props.forEach((k, v) -> {
            Map<String, Object> map = new HashMap<>();
            map.put(keyField, k);
            map.put(valueField, v);
            map.put(descriptionField, Optional.ofNullable(descriptionMap.get(k)).orElse(""));
            resultJson.add(map);
        });
        if (isOut) {
            String aimName = resourceFile.getAbsolutePath().replaceAll("\\.properties", "\\.txt");
            out(resultJson, new File(aimName));
        }
        return resultJson;
    }

    /**
     * 单个XDiamond的 json格式
     * @createDate 2018/8/14 14:03
     * @author Lgren
     * @param resourceFile 资源文件
     * @param isOut 是否在原有的地址处输出成 txt
     */
    public static JSONArray proToJSONForXD(File resourceFile, boolean isOut) {
        return proToJSONForXD(resourceFile, isOut, "key", "value", "description");
    }

    /**
     * 单个XDiamond的 json格式
     * @createDate 2018/8/14 14:03
     * @author Lgren
     * @param resourceFile 资源文件
     */
    public static JSONArray proToJSONForXD(File resourceFile) {
        return proToJSONForXD(resourceFile, true);
    }

    /**
     * 单个XDiamond的 json格式
     * @createDate 2018/8/14 14:03
     * @author Lgren
     * @param resourceName 资源文件地址
     * @param isOut 是否在原有的地址处输出成 txt
     * @param keyField properties的 key的名字字段
     * @param valueField properties的 value的名字字段
     * @param descriptionField properties的 描述的名字字段
     */
    public static JSONArray proToJSONForXD(String resourceName, boolean isOut, String keyField, String valueField, String descriptionField) {
        return proToJSONForXD(new File(resourceName), isOut, keyField, valueField, descriptionField);
    }

    /**
     * 单个XDiamond的 json格式
     * @createDate 2018/8/14 14:03
     * @author Lgren
     * @param resourceName 资源文件地址
     * @param isOut 是否在原有的地址处输出成 txt
     * @return
     */
    public static JSONArray proToJSONForXD(String resourceName, boolean isOut) {
        return proToJSONForXD(resourceName, isOut, "key", "value", "description");
    }

    /**
     * 单个XDiamond的 json格式
     * @createDate 2018/8/14 14:03
     * @author Lgren
     * @param resourceName 资源文件地址
     */
    public static JSONArray proToJSONForXD(String resourceName) {
        return proToJSONForXD(resourceName, true);
    }


    /**
     * 多个XDiamond的 json格式
     * @createDate 2018/8/14 14:06
     * @author Lgren
     * @param resourceFiles 资源文件
     * @param isOut 是否在原有的地址处输出成 txt
     * @param isAdd 是否将所有的资源转换后的json结合在一起
     * @param keyField properties的 key的名字字段
     * @param valueField properties的 value的名字字段
     * @param descriptionField properties的 描述的名字字段
     */
    public static JSON proToJSONForXD(File[] resourceFiles, boolean isOut, boolean isAdd, String keyField, String valueField, String descriptionField) {
        JSONObject jsonObjectList = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (File file : resourceFiles) {
            if (isAdd) {
                jsonArray.addAll(proToJSONForXD(file, isOut, keyField, valueField, descriptionField));
            } else {
                jsonObjectList.put(file.getName(), proToJSONForXD(file, isOut, keyField, valueField, descriptionField));
            }
        }
        if (isAdd && isOut) {
            String aimName = resourceFiles[0].getParent() + "\\all.txt";
            out(jsonArray, new File(aimName));
        }
        return isAdd ? jsonArray : jsonObjectList;
    }

    /**
     * 多个XDiamond的 json格式
     * @createDate 2018/8/14 14:06
     * @author Lgren
     * @param resourceFiles 资源文件
     * @param isOut 是否在原有的地址处输出成 txt
     * @param isAdd 是否将所有的资源转换后的json结合在一起
     */
    public static JSON proToJSONForXD(File[] resourceFiles, boolean isOut, boolean isAdd) {
        return proToJSONForXD(resourceFiles, isOut, isAdd, "key", "value", "description");
    }

    /**
     * 多个XDiamond的 json格式
     * @createDate 2018/8/14 14:06
     * @author Lgren
     * @param resourceFiles 资源文件
     * @param isOut 是否在原有的地址处输出成 txt
     */
    public static JSON proToJSONForXD(File[] resourceFiles, boolean isOut) {
        return proToJSONForXD(resourceFiles, isOut, true);
    }

    /**
     * 多个XDiamond的 json格式
     * @createDate 2018/8/14 14:06
     * @author Lgren
     * @param resourceFiles 资源文件
     */
    public static JSON proToJSONForXD(File[] resourceFiles) {
        return proToJSONForXD(resourceFiles, true);
    }

    /**
     * 多个XDiamond的 json格式
     * @createDate 2018/8/14 14:06
     * @author Lgren
     * @param resourceNames 资源文件地址
     * @param isOut 是否在原有的地址处输出成 txt
     * @param isAdd 是否将所有的资源转换后的json结合在一起
     * @param keyField properties的 key的名字字段
     * @param valueField properties的 value的名字字段
     * @param descriptionField properties的 描述的名字字段
     */
    public static JSON proToJSONForXD(String[] resourceNames, boolean isOut, boolean isAdd, String keyField, String valueField, String descriptionField) {
        File[] resourceFiles = new File[resourceNames.length];
        for (int i = 0; i < resourceNames.length; i++) {
            resourceFiles[i] = new File(resourceNames[i]);
        }
        return proToJSONForXD(resourceFiles, isOut, isAdd, keyField, valueField, descriptionField);
    }

    /**
     * 多个XDiamond的 json格式
     * @createDate 2018/8/14 14:06
     * @author Lgren
     * @param resourceNames 资源文件地址
     * @param isOut 是否在原有的地址处输出成 txt
     * @param isAdd 是否将所有的资源转换后的json结合在一起
     */
    public static JSON proToJSONForXD(String[] resourceNames, boolean isOut, boolean isAdd) {
        return proToJSONForXD(resourceNames, isOut, isAdd, "key", "value", "description");
    }

    /**
     * 多个XDiamond的 json格式
     * @createDate 2018/8/14 14:06
     * @author Lgren
     * @param resourceNames 资源文件地址
     * @param isOut 是否在原有的地址处输出成 txt
     */
    public static JSON proToJSONForXD(String[] resourceNames, boolean isOut) {
        return proToJSONForXD(resourceNames, isOut, true);
    }

    /**
     * 多个XDiamond的 json格式
     * @createDate 2018/8/14 14:06
     * @author Lgren
     * @param resourceNames 资源文件地址
     */
    public static JSON proToJSONForXD(String[] resourceNames) {
        return proToJSONForXD(resourceNames, true);
    }

    /**
     * 获取文件夹下边的所有 *.properties文件
     * @createDate 2018/8/14 14:07
     * @author Lgren
     * @param file 文件夹 file
     * @return File[] 所有匹配到的 File
     */
    public static File[] forFolder(File file) {
        return file.listFiles(f -> f.getName().contains(".properties"));
    }

    /**
     * 获取文件夹下边的所有 *.properties文件
     * @createDate 2018/8/14 14:07
     * @author Lgren
     * @param fileStr 文件夹的String地址
     * @return File[] 所有匹配到的 File
     */
    public static File[] forFolder(String fileStr) {
        return new File(fileStr).listFiles(f -> f.getName().contains(".properties"));
    }

    /**
     * 输出方法
     * @createDate 2018/8/14 14:06
     * @author Lgren
     * @param json 数据
     * @param aimFile 输出的地址
     */
    private static void out(JSON json, File aimFile) {
        try {
            OutputStream outputStream = new FileOutputStream(aimFile);
            outputStream.write(json.toJSONString().getBytes());
            outputStream.flush();
            IOUtils.closeQuietly(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取properties的注释作为描述
     * @create 2018/8/14 20:45
     * @author Lgren
     * @param file 文件
     */
    private static Map<String, String> getDescription(File file){
        Map<String, String> map = new HashMap<>();
        List<String> stringList = null;//读取文件
        try {
            stringList = Files.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < stringList.size(); i++) {
            if (!stringList.get(i).contains("#") || StringUtils.isBlank(stringList.get(i).replaceAll("#", ""))) {// 如果不包含"#"且只有#或者为空 跳过
                continue;
            }
            String currLine = stringList.get(i).trim();// 当前这一行
            if (!currLine.startsWith("#") && currLine.contains("=")) {
                String currKeyName = currLine.substring(0, currLine.indexOf("=")).trim();// 获取当前行的key值
                String currZS = currLine.substring(currLine.lastIndexOf("#") + 1);// 获取当前行最后的注释
                if (StringUtils.isNotBlank(currZS)) {
                    map.put(currKeyName, currZS);
                }
                continue;
            }
            while(true) {
                if (stringList.get(i + 1).trim().startsWith("#")) {// 如果连续多行都是#开头的取最后一行
                    i++;
                } else {
                    break;
                }
            }
            String nextLine = stringList.get(i + 1).trim();// 下一行
            if (StringUtils.isBlank(nextLine.replaceAll("#", ""))) {// 如果下一行只有#或者为空则跳过
                i++;
                continue;
            }
            if (!nextLine.contains("#") && nextLine.contains("=")) {// 如果下一行包含有=没有#
                String nextKeyName = nextLine.substring(0, nextLine.indexOf("=")).trim();// 获取下一行的key值
                String currZS = stringList.get(i).replaceAll("#","");
                if (StringUtils.isNotBlank(currZS)) {
                    map.put(nextKeyName, currZS);
                }
                i++;// 跳过下一行
            }
        }
        return map;
    }
}
