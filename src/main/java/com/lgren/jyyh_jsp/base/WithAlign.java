package com.lgren.jyyh_jsp.base;

/**
 * TODO
 * @create 2019-06-21 15:49
 * @since lgren
 */
public interface WithAlign {
    String CENTER = "center";
    String LEFT = "left";
    String RIGHT = "right";

    String getAlign();// 默认为CENTER
    WithAlign setAlign(String align);
}
