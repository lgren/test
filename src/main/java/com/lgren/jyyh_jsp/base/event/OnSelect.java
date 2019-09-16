package com.lgren.jyyh_jsp.base.event;

/**
 * TODO
 * @create 2019-06-21 17:24
 * @since lgren
 */
public interface OnSelect<T> extends Event {
    // private String onSelect;

    String getOnSelect();

    OnSelect<T> setOnSelect(String onSelect);
}
