package com.lgren.poi.poi3_17.new_read;

import lombok.Data;

/**
 * TODO
 * @author lgren
 * @create 2019-05-17 16:57
 **/
@Data
public class BaseClass {
    @ReadExcelField("ID")
    private String id;
    @ReadExcelField("名字")
    private String name;
    @ReadExcelField("年龄")
    private Integer age;
    @ReadExcelField("生日")
    private Integer birth;
    @ReadExcelField("绩效")
    private Double num;
    @ReadExcelField
    private String like;
    @ReadExcelField("男")
    private Boolean gender;
    @ReadExcelField("备注")
    private String remark;

}
