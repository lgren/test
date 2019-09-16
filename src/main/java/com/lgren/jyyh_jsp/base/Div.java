package com.lgren.jyyh_jsp.base;

/**
 * TODO
 * @create 2019-06-21 15:35
 * @since lgren
 */
public interface Div extends Span {
    // private int cols = 1;
    // private List<Base> children;

    // private int span = 1;
    // private boolean allSpan = false;
    // private boolean fit;

    // private String fieldName;
    // private String tagName;
    // private String id;
    // private String key;
    // private String cssClass;
    // private String cssStyle;

    default int getCols() {
        return 1;
    }

    Div setCols(int cols);
}
