package com.lgren.pattern;

import java.util.*;

/**
 * 数据库部分字段解释以及前端展示字段
 * @author Lgren
 * @create 2018-08-24 17:39
 **/
public class FieldValueStr {
    public static Map<Integer, String> taskTypeStrMap = getTaskTypeStrMap();// 任务类型
    public static Map<Integer, String> sendChannelStrMap = getSendChannelStrMap();// 发送渠道
    public static Map<Integer, String> sendTypeStrMap = getSendTypeStrMap();// 推送方式
    public static Map<Integer, String> sendPeriodStrMap = getSendPeriodStrMap();// 推送周期
    public static Map<Integer, String> periodTypeStrMap = getPeriodTypeStrMap();// 推送周期方式
    public static Map<Integer, String> weixinMsgTypeStrMap = getWeixinMsgTypeStrMap();// 公众号消息形式
    public static Map<Integer, String> taskStatusStrMap = getTaskStatusStrMap();// 任务状态描述
    public static Map<Integer, String> spStatusStrMap = getSpStatusStrMap();// 审批状态
    //region 字段对应值
    /** 任务类型：1客户关怀，2客户营销，3节点通知(暂时不做) */
    private static Map<Integer, String> getTaskTypeStrMap() {
        if (taskTypeStrMap == null) {
            taskTypeStrMap = new LinkedHashMap<>();
            taskTypeStrMap.put(2,"客户关怀");
            taskTypeStrMap.put(1,"客户营销");
//            taskTypeStrMap.put(3,"节点通知");
        }
        return taskTypeStrMap;
    }

    /** 发送渠道 1.短信 2.APP通知 3.公共号通知 */
    private static Map<Integer, String> getSendChannelStrMap() {
        if (sendChannelStrMap == null) {
            sendChannelStrMap = new LinkedHashMap<>();
            sendChannelStrMap.put(1,"短信");
            sendChannelStrMap.put(2,"APP通知");
            sendChannelStrMap.put(3,"公共号通知");
        }
        return sendChannelStrMap;
    }

    /** 推送方式 1.按渠道优先级发送 2.多渠道同时发送 */
    private static Map<Integer, String> getSendTypeStrMap() {
        if (sendTypeStrMap == null) {
            sendTypeStrMap = new LinkedHashMap<>();
            sendTypeStrMap.put(1,"按渠道优先级发送");
            sendTypeStrMap.put(2,"多渠道同时发送");
        }
        return sendTypeStrMap;
    }

    /** 推送周期 1.仅此一次 2.周期发送 */
    private static Map<Integer, String> getSendPeriodStrMap() {
        if (sendPeriodStrMap == null) {
            sendPeriodStrMap = new LinkedHashMap<>();
            sendPeriodStrMap.put(1,"仅此一次");
            sendPeriodStrMap.put(2,"周期发送");
        }
        return sendPeriodStrMap;
    }

    /** 推送周期方式 1.每天 2.每周 3.每月 */
    private static Map<Integer, String> getPeriodTypeStrMap() {
        if (periodTypeStrMap == null) {
            periodTypeStrMap = new LinkedHashMap<>();
            periodTypeStrMap.put(2,"每周");
            periodTypeStrMap.put(3,"每月");
            periodTypeStrMap.put(1,"每天");
        }
        return periodTypeStrMap;
    }

    /** 公众号消息形式   微信公共号消息类型 1.图文 2.文字 3.图片 4.语音, 5.视频*/
    private static Map<Integer, String> getWeixinMsgTypeStrMap() {
        if (weixinMsgTypeStrMap == null) {
            weixinMsgTypeStrMap = new LinkedHashMap<>();
            weixinMsgTypeStrMap.put(1,"图文");
            weixinMsgTypeStrMap.put(2,"文字");
            weixinMsgTypeStrMap.put(3,"图片");
            weixinMsgTypeStrMap.put(4,"语音");
            weixinMsgTypeStrMap.put(5,"视频");
        }
        return weixinMsgTypeStrMap;
    }

    /** 任务状态描述  任务状态 1进行中，2已停用 */
    private static Map<Integer, String> getTaskStatusStrMap() {
        if (taskStatusStrMap == null) {
            taskStatusStrMap = new LinkedHashMap<>();
            taskStatusStrMap.put(1,"进行中");
            taskStatusStrMap.put(2,"已停用");
        }
        return taskStatusStrMap;
    }

    /** 审批状态：1 待审批，2审批中，3审批通过，4审批不通过 */
    private static Map<Integer, String> getSpStatusStrMap() {
        if (spStatusStrMap == null) {
            spStatusStrMap = new LinkedHashMap<>();
            spStatusStrMap.put(1,"待审批");
            spStatusStrMap.put(2,"审批中");
            spStatusStrMap.put(3,"审批通过");
            spStatusStrMap.put(4,"审批不通过");
        }
        return spStatusStrMap;
    }

    //endregion

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
