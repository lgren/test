package com.lgren.ohers;

import java.util.*;

/**
 * 数据库部分字段解释以及前端展示字段
 * @author Lgren
 * @create 2018-08-24 17:39
 **/
public class FieldValueStr {
    /** 任务类型：1客户关怀，2客户营销，3节点通知(暂时不做) */
    public final static Map<Integer, String> taskTypeStrMap = new LinkedHashMap<Integer, String>(){{
        put(2,"客户关怀");
        put(1,"客户营销");
    }};

    /** 发送渠道 1.短信 2.APP通知 3.公共号通知 */
    public final static Map<Integer, String> sendChannelStrMap = new LinkedHashMap<Integer, String>(){{
        put(1,"短信");
        put(2,"APP通知");
        put(3,"公共号通知");
    }};

    /** 推送方式 1.按渠道优先级发送 2.多渠道同时发送 */
    public final static Map<Integer, String> sendTypeStrMap = new LinkedHashMap<Integer, String>(){{
        put(1,"按渠道优先级发送");
        put(2,"多渠道同时发送");
    }};

    /** 推送周期 1.仅此一次 2.周期发送 */
    public final static Map<Integer, String> sendPeriodStrMap = new LinkedHashMap<Integer, String>(){{
        put(1,"仅此一次");
        put(2,"周期发送");
    }};

    /** 推送周期方式 1.每天 2.每周 3.每月 */
    public final static Map<Integer, String> periodTypeStrMap = new LinkedHashMap<Integer, String>(){{
        put(2,"每周");
        put(3,"每月");
        put(1,"每天");
    }};

    /** 公众号消息形式   微信公共号消息类型 1.图文 2.文字 3.图片 4.语音, 5.视频*/
    public final static Map<Integer, String> weixinMsgTypeStrMap = new LinkedHashMap<Integer, String>(){{
        put(1,"图文");
        put(2,"文字");
        put(3,"图片");
        put(4,"语音");
        put(5,"视频");
    }};

    /** 任务状态描述  任务状态 1进行中，2已停用 */
    public final static Map<Integer, String> taskStatusStrMap = new LinkedHashMap<Integer, String>(){{
        put(1,"进行中");
        put(2,"已停用");
    }};

    /** 审批状态：1 待审批，2审批中，3审批通过，4审批不通过 */
    public final static Map<Integer, String> spStatusStrMap = new LinkedHashMap<Integer, String>(){{
        put(1,"待审批");
        put(2,"审批中");
        put(3,"审批通过");
        put(4,"审批不通过");
    }};

    /** 将map转为前端展示字段 */
    public static List<Map<String, Object>> getFieldList(Map<Integer, String> map) {
        List<Map<String, Object>> resultList = new LinkedList<>();
        map.forEach((k,v) -> {
            Map<String, Object> mapParam = new HashMap<>();
            mapParam.put("id", k);
            mapParam.put("getName", v);
            resultList.add(mapParam);
        });
        return resultList;
    }
}
