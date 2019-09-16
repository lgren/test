package com.lgren.jyyh_jsp.tag.other;


import com.lgren.jyyh_jsp.base.Base;
import com.lgren.jyyh_jsp.base.event.OnClick;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TODO
 * @create 2019-06-21 16:42
 * @since lgren
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Button implements Base, OnClick<Button> {
    private String type;

    public Button(String key, String onClick) {
        this.key = key;
        this.onClick = onClick;
    }

    /*********************接口的实现*********************/
    private String fieldName;
    private String tagName = "button";
    private String id;
    private String key;
    private String cssClass;
    private String cssStyle;

    private String onClick;
}
