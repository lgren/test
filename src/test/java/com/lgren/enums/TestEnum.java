package com.lgren.enums;

/**
 * TODO
 * @author Lgren
 * @create 2018-08-28 10:36
 **/
public enum TestEnum implements IKVEnum{
    ONE(1,"一"),
    TWO(2,"二"),
    TREE(3,"三"),
    FOUR(4,"四"),
    FIVE(5,"五"),
    SIX(6,"六");
    private Integer value;
    private String name;
    TestEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
    @Override
    public Integer getValue() {
        return this.value;
    }
    @Override
    public void setValue(Integer value) {
        this.value = value;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
}

