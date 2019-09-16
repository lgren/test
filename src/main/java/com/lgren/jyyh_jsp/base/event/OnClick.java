package com.lgren.jyyh_jsp.base.event;

/**
 * TODO
 * @create 2019-06-21 17:23
 * @since lgren
 */
public interface OnClick<T> extends Event {
    // private String onClick;

    String getOnClick();

    OnClick<T> setOnClick(String onClick);
}
