package com.lgren.ohers;

import java.util.*;

public enum TaskAndMsgEnum{
    /** 任务类型：1客户关怀，2客户营销，3节点通知(暂时不做) */
    TASK_TYPE(new LinkedHashMap<Integer, String>(){{
        put(2,"客户关怀");
        put(1,"客户营销");
    }},null),

    /** 发送渠道 1.短信 2.APP通知 3.公共号通知 */
    SEND_CHANNEL(new LinkedHashMap<Integer, String>(){{
        put(1,"短信");
        put(2,"APP通知");
        put(3,"公共号通知");
    }},null),

    /** 推送方式 1.按渠道优先级发送 2.多渠道同时发送 */
    SEND_TYPE(new LinkedHashMap<Integer, String>(){{
        put(1,"按渠道优先级发送");
        put(2,"多渠道同时发送");
    }},null),

    /** 推送周期 1.仅此一次 2.周期发送 */
    SEND_PERIOD(new LinkedHashMap<Integer, String>(){{
        put(1,"仅此一次");
        put(2,"周期发送");
    }},null),

    /** 推送周期方式 1.每天 2.每周 3.每月 */
    PERIOD_TYPE(new LinkedHashMap<Integer, String>(){{
        put(2,"每周");
        put(3,"每月");
        put(1,"每天");
    }},null),

    /** 公众号消息形式   微信公共号消息类型 1.图文 2.文字 3.图片 4.语音, 5.视频*/
    WEIXIN_MSG_TYPE(new LinkedHashMap<Integer, String>(){{
        put(1,"图文");
        put(2,"文字");
        put(3,"图片");
        put(4,"语音");
        put(5,"视频");
    }},null),

    /** 任务状态描述  任务状态 1进行中，2已停用 */
    TASK_STATUS(new LinkedHashMap<Integer, String>(){{
        put(1,"进行中");
        put(2,"已停用");
    }},null),

    /** 审批状态：1 待审批，2审批中，3审批通过，4审批不通过 */
    SP_STATUS(new LinkedHashMap<Integer, String>(){{
        put(1,"待审批");
        put(2,"审批中");
        put(3,"审批通过");
        put(4,"审批不通过");
    }},null);

    private Map<Integer, String> strMap;
    private List<Map<String, Object>> fieldList;
    TaskAndMsgEnum(Map<Integer, String> strMap, List<Map<String, Object>> fieldList) {
        this.strMap = strMap;
        if (fieldList == null) {
            fieldList = new ArrayList<>(strMap.size());
            for (Map.Entry<Integer, String> entry : strMap.entrySet()) {
                Map<String, Object> mapParam = new HashMap<>(2);
                mapParam.put("id", entry.getKey());
                mapParam.put("getName", entry.getValue());
                fieldList.add(mapParam);
            }
        }
        this.fieldList = fieldList;
    }
}
