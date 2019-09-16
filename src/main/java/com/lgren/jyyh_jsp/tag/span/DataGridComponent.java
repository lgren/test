package com.lgren.jyyh_jsp.tag.span;


import com.lgren.jyyh_jsp.base.Base;
import com.lgren.jyyh_jsp.base.WithIcon;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TODO
 * @create 2019-06-21 15:56
 * @since lgren
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DataGridComponent implements Base, WithIcon {

    /*********************接口的实现*********************/
    private String icon;

    private String fieldName;
    private String tagName = "datagridItem";
    private String id;
    private String key;
    private String cssClass;
    private String cssStyle;
}
