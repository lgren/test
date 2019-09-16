package com.lgren.jyyh_jsp.tag.div;


import com.lgren.jyyh_jsp.base.Base;
import com.lgren.jyyh_jsp.base.Div;
import com.lgren.jyyh_jsp.base.TagBeginEnd;
import com.lgren.jyyh_jsp.base.WithChildren;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * TODO
 * @create 2019-06-21 15:37
 * @since lgren
 */
@Data
@Accessors(chain = true)
public class Panel implements Div, WithChildren<Panel, Base>, TagBeginEnd {
    private boolean withToolBar;

    /*********************接口的实现*********************/
    private List<Base> children;

    private int cols = 1;
    private int span = 1;
    private boolean allSpan = false;
    private boolean fit;
    private String fieldName;
    private String tagName = "panel";
    private String id;
    private String key;
    private String cssClass;
    private String cssStyle;
}
