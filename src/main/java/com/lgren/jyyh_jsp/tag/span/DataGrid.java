package com.lgren.jyyh_jsp.tag.span;


import com.lgren.jyyh_jsp.TagConst;
import com.lgren.jyyh_jsp.base.Span;
import com.lgren.jyyh_jsp.base.WithChildren;
import com.lgren.jyyh_jsp.base.WithFields;
import com.lgren.jyyh_jsp.base.WithIcon;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * TODO
 * @create 2019-06-21 15:54
 * @since lgren
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DataGrid implements Span, WithChildren<DataGrid, DataGridComponent>, WithFields<DataGrid> {
    public static final String EDIT = "edit";
    public static final String DELETE = "delete";
    public static final String PAGE = "page";
    private static final String EDIT_STR = "修改";
    private static final String DELETE_STR = "删除";

    private boolean forceFitColumns;
    private boolean haveSn;
    private boolean columnFilter;
    private String selectType;

    public DataGrid(Integer plan) {
        if (Objects.equals(plan, 1)) {
            forceFitColumns = true;
            haveSn = true;
            columnFilter = true;
            selectType = "checkbox";
            allFields = true;
        }
    }

    public DataGrid addOtherChild(DataGridComponent... otherChildren) {
        if (ArrayUtils.isNotEmpty(otherChildren)) {
            List<DataGridComponent> children = getChildren();
            if (children == null) {
                children = new ArrayList<>();
                setChildren(children);
            }
            for (DataGridComponent child : otherChildren) {
                Optional.ofNullable(child).ifPresent(children::add);
            }
        }
        return this;
    }

    public DataGrid addOtherChild(String... otherChildren) {
        if (ArrayUtils.isNotEmpty(otherChildren)) {
            List<DataGridComponent> children = getChildren();
            if (children == null) {
                children = new ArrayList<>();
                setChildren(children);
            }
            for (String childStr : otherChildren) {
                if (StringUtils.isNotBlank(childStr)) {
                    switch (childStr) {
                        case EDIT: children.add(new DataGridComponent().setTagName(TagConst.datagridItem)
                                    .setKey(DataGrid.EDIT_STR)
                                    .setIcon(WithIcon.EDIT));break;
                        case DELETE: children.add(new DataGridComponent()
                                    .setTagName(TagConst.datagridItem)
                                    .setKey(DataGrid.DELETE_STR)
                                    .setIcon(WithIcon.DELETE));break;
                        case PAGE: children.add(new DataGridComponent()
                                    .setTagName(TagConst.dataGridToolPaging)
                                    .setKey(DataGrid.DELETE_STR)
                                    .setIcon(WithIcon.DELETE));break;
                        default: break;
                    }
                }
            }
        }
        return this;
    }

    /*********************接口的实现*********************/
    private boolean allFields;
    private List<String> hiddenItems;
    private List<String> skipItems;

    private List<DataGridComponent> children;

    private int span = 1;
    private boolean allSpan = false;
    private boolean fit;

    private String fieldName;
    private String tagName = "dataGrid";
    private String id;
    private String key;
    private String cssClass;
    private String cssStyle;


}
