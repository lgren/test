package com.lgren.jyyh_jsp.tag.form;


import com.lgren.jyyh_jsp.base.Base;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TODO
 * @create 2019-06-21 15:44
 * @since lgren
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class FromComponent implements Base {
    private String name;
    private String onClick;
    private String onChange;
    private String onBlur;
    private String onFocus;
    private String type;

    public FromComponent(String tagName, String fieldName, String key) {
        this.tagName = tagName;
        this.fieldName = fieldName;
        this.key = key;
    }

    /*********************接口的实现*********************/
    private String fieldName;
    private String tagName;
    private String id;
    private String key;
    private String cssClass;
    private String cssStyle;


}
