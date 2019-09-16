package com.lgren.jyyh_jsp.base;


import com.lgren.jyyh_jsp.tag.IsNotAttr;

/**
 * TODO
 * @create 2019-06-21 15:48
 * @since lgren
 */
public interface Span extends Base {
    // private int span = 1;
    // private boolean allSpan = false;
    // private boolean fit;

    // private String fieldName;
    // private String tagName;
    // private String id;
    // private String key;
    // private String cssClass;
    // private String cssStyle;

    default int getSpan() {
        return 1;
    }

    Span setSpan(int span);

    @IsNotAttr
    default boolean isAllSpan() {
        return false;
    }

    @IsNotAttr
    Span setAllSpan(boolean allSpan);

    boolean isFit();

    Span setFit(boolean fit);
}
