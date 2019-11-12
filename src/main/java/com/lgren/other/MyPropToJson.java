package com.lgren.other;

import java.io.*;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;


/**
 * properties文件转为json字符串
 *
 * @author lgren
 */
public class MyPropToJson {

/*    public static void main(String[] args) {
        Map map = new LinkedHashMap();
        map.put("description", "");
        MyPropToJson myPropToJson = new MyPropToJson("key","value",map);
        String aimName = "/Users/lgren/Project/JavaIdeaSpace/文件/out.txt";
        String resourceName1 = "/Users/lgren/Project/JavaIdeaSpace/文件/in.properties";
        String resourceName2 = "/Users/lgren/Project/JavaIdeaSpace/文件/in的副本.properties";
        String[] resourceNames = {resourceName1,resourceName2};
        System.out.println(myPropToJson.toJSONString(resourceNames,false,aimName));
    }*/

/*    public static void main(String[] args) {
        MyPropToJson myPropToJson = new MyPropToJson();
        String aimName = "/Users/lgren/Project/JavaIdeaSpace/文件/out.properties";
        String resourceName = "/Users/lgren/Project/JavaIdeaSpace/文件/out.txt";
        myPropToJson.toProperties(resourceName,aimName);
    }*/

    private String keyName;
    private String valueName;
    private Map initMap;


    public Properties toProperties(String resourceName) {
        String str = "";
        char[] chars =new char[1024];
        try {
            Reader reader = new FileReader(new File(resourceName));
            for (int size = reader.read(chars); size >0 ; size = reader.read(chars)) {
                str +=new String(chars,0,size);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        System.out.println(str);
        Properties props = new Properties();
        JSONArray inJsonArr = JSONArray.parseArray(str);
        JSONObject[] jsonObjects = inJsonArr.toArray(new JSONObject[0]);
        for (JSONObject jsonObject : jsonObjects) {
            props.setProperty(jsonObject.getString("key"),jsonObject.getString("value"));
        }
        return props;
    }

    public Properties toProperties(String resourceName,String aimName) {
        Properties props = toProperties(resourceName);
        out(props,aimName);

        return toProperties(resourceName);
    }

    /**
     * properties文件转为json字符串
     *
     * @param resourceName 例如，来源properties文件位置 ，"/Users/lgren/in.properties"
     */
    public JSONArray toJSONString(String resourceName) {
        //输出结果
        JSONArray resultJson = new JSONArray();
        try {
            //获取properties文件信息
            InputStream inputStream = new FileInputStream(new File(resourceName));
            Properties props = new Properties();
            props.load(inputStream);
            IOUtils.closeQuietly(inputStream);

            if (initMap == null) {
                resultJson.add(props);
                return resultJson;
            }
            //对初始的properties进行改造
            Enumeration<?> keysEn = props.propertyNames();
            while (keysEn.hasMoreElements()) {
                String key = (String) keysEn.nextElement();
                resultJson.add(forLikeXDiamond(key, props.getProperty(key)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultJson;
    }

    /**
     * properties文件转为json字符串
     *
     * @param resourceName 例如，来源properties文件位置 ，"/Users/lgren/in.properties"
     * @param isOut        是否输出到本地 格式为当前文件名与地址的txt文件
     */
    public JSONArray toJSONString(String resourceName, boolean isOut) {
        JSONArray inJSONArr = toJSONString(resourceName);
        if (!isOut) {
            return inJSONArr;
        }
        File in = new File(resourceName);
        String aimName = resourceName.substring(0, resourceName.indexOf(in.getName()))
                + in.getName().substring(0, in.getName().indexOf("properties")) + "txt";
        out(inJSONArr,aimName);
        return inJSONArr;
    }

    public JSONArray[] toJSONString(String[] resourceNames) {
        JSONArray[] result = new JSONArray[resourceNames.length];
        for (int i = 0; i < resourceNames.length; i++) {
            result[i] = toJSONString(resourceNames[i]);
        }
        return result;
    }

    /**
     * properties文件转为json字符串
     *
     * @param resourceNames 多个properties来源地址
     * @param isOut 是否输出每个到本地 格式为当前文件名与地址的txt文件
     */
    public JSONArray[] toJSONString(String[] resourceNames, boolean isOut) {
        JSONArray[] result = new JSONArray[resourceNames.length];
        for (int i = 0; i < resourceNames.length; i++) {
            result[i] = toJSONString(resourceNames[i],isOut);
        }
        return result;
    }

    /**
     * 将多个properties文件整合到一个txt里边
     *
     * @param resourceNames 需要转换的properties的地址
     * @param isOut 是否输出每个到本地 格式为当前文件名与地址的txt文件
     * @param aimName 整合到一起的一个地址
     * @return 所有properties整合到一起的JSONArray
     */
    public JSONArray toJSONString(String[] resourceNames, boolean isOut, String aimName) {
        JSONArray result = new JSONArray();
        for (JSONArray j : toJSONString(resourceNames,isOut)) {
            result.addAll(j);
        }
        out(result,aimName);
        return result;
    }


    public MyPropToJson(String keyName, String valueName, Map<String,Object> initMap) {
        Map<String, Object> resultMap;
        if (initMap == null) {
            resultMap = new LinkedHashMap<>();
        }
        else {
            resultMap = new LinkedHashMap<>(initMap);
        }
        resultMap.put(keyName,"");
        resultMap.put(valueName,"");
        this.keyName = keyName;
        this.valueName = valueName;
        this.initMap = resultMap;
    }

    private Map forLikeXDiamond(Object key, Object value) {
        if (key == null) {
            return null;
        }
        Map map = new LinkedHashMap(initMap);
        map.put(keyName, key);
        map.put(valueName, value);
        return map;
    }

    private void out(JSONArray jsonArray,String aimName) {
        try {
            OutputStream outputStream = new FileOutputStream(new File(aimName));
            outputStream.write(jsonArray.toJSONString().getBytes());
            outputStream.flush();
            IOUtils.closeQuietly(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void out(Properties props, String aimName) {
        try {
            OutputStream outputStream = new FileOutputStream(new File(aimName));
            props.store(outputStream,"test");
            IOUtils.closeQuietly(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
