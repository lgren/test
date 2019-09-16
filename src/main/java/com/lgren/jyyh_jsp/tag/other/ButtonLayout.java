package com.lgren.jyyh_jsp.tag.other;


import com.lgren.jyyh_jsp.base.Span;
import com.lgren.jyyh_jsp.base.WithAlign;
import com.lgren.jyyh_jsp.base.WithChildren;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * TODO
 * @create 2019-06-21 16:42
 * @since lgren
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ButtonLayout implements Span, WithAlign, WithChildren<ButtonLayout, Button> {
    private boolean allSpan = true;

    public ButtonLayout(Button... buttons) {
        addChild(buttons);
    }

    /*********************接口的实现*********************/
    private List<Button> children;

    private int span = 1;
    private boolean fit;
    private String fieldName;
    private String tagName = "buttonLayout";
    private String id;
    private String key;
    private String cssClass;
    private String cssStyle;
    private String align = WithAlign.CENTER;
}
