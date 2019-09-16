package com.lgren.jyyh_jsp.base.event;

/**
 * TODO
 * @create 2019-06-21 17:24
 * @since lgren
 */
public interface OnChange<T> extends Event {
    // private String onChange;

    String getOnChange();

    OnChange<T> setOnChange(String onChange);

}
