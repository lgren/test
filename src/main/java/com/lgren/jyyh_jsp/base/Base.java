package com.lgren.jyyh_jsp.base;


import com.lgren.jyyh_jsp.tag.IsNotAttr;

/**
 * TODO
 * @create 2019-06-21 15:32
 * @since lgren
 */
public interface Base {
    // private String fieldName;
    // private String tagName;
    // private String id;
    // private String key;
    // private String cssClass;
    // private String cssStyle;
    // private String tagBegin;

    @IsNotAttr
    String getFieldName();

    @IsNotAttr
    Base setFieldName(String fieldName);

    @IsNotAttr
    String getTagName();

    @IsNotAttr
    Base setTagName(String tagName);

    default String getId() {
        return getFieldName();
    }

    Base setId(String id);

    String getKey();

    Base setKey(String key);

    String getCssClass();

    Base setCssClass(String cssClass);

    String getCssStyle();

    Base setCssStyle(String cssStyle);
}
