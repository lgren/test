package com.lgren.jyyh_jsp;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lgren.jyyh_jsp.base.Div;
import com.lgren.jyyh_jsp.tag.div.Panel;
import com.lgren.jyyh_jsp.tag.form.FromComponent;
import com.lgren.jyyh_jsp.tag.other.Button;
import com.lgren.jyyh_jsp.tag.other.ButtonLayout;
import com.lgren.jyyh_jsp.tag.span.DataGrid;
import org.junit.Test;

import java.util.List;

import static com.lgren.jyyh_jsp.base.WithAlign.LEFT;

/**
 * TODO
 * @author lgren
 * @create 2019-06-20 17:29
 **/
public class TestMain {
    @Test
    public void main1() {
        List<Div> body = Lists.newArrayList(new Panel(), new Panel());
        ((Panel) body.get(0)).setCols(4).setId("panel_query").setKey("查询条件")
                .addChild(
                        new FromComponent(TagConst.text, "ssc001", "编号").setId("query_%s").setTagName("dto['%s']"),
                        new FromComponent(TagConst.text, "ssc002", "名字").setId("query_%s").setTagName("dto['%s']"),
                        new FromComponent(TagConst.selectInput, "aae100", "标识").setId("query_%s").setTagName("dto['%s']"),
                        new FromComponent(TagConst.selectInput, "ssc008", "标识").setId("query_%s").setTagName("dto['%s']"),
                        new ButtonLayout().addChild(
                                new Button("查询", "fnPage"),
                                new Button("重置", null).setType("resetPage")));
        ((Panel) body.get(1)).setKey("查询块列表(最多添加8个有效事件)").setFit(true)
                .addChild(
                        new ButtonLayout().setAlign(LEFT).addChild(
                                new Button("新增", "fnAddOrEdit(null, null)"),
                                new Button("批量删除", "fnDelete(null, null)")),
                        new DataGrid(1).setId("dg_$cap_table$").addHiddenItems("ssc001", "aae011", "yab003").addSkipItem()
                                .addOtherChild(DataGrid.EDIT, DataGrid.DELETE, DataGrid.PAGE));
        JspData<Div> jspData = new JspData<Div>().setBody(body);
        System.out.println(jspData);
        System.out.println(JSON.toJSONString(jspData));
    }
    @Test
    public void main2() {
        Panel panel = new Panel();
        System.out.println(panel.getTagBegin());
    }

}
