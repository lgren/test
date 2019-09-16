package com.lgren.jyyh_jsp.base;

/**
 * TODO
 * @create 2019-06-21 15:49
 * @since lgren
 */
public interface WithIcon {
    // private String icon;

    String EDIT = "icon-edit-mine";
    String DELETE = "icon-remove-mine";

    String getIcon();// 默认为CENTER
    WithIcon setIcon(String icon);
}
