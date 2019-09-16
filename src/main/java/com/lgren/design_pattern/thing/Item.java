package com.lgren.design_pattern.thing;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-09-28 16:23
 **/
public class Item {
    private Commodity commodity;
    private Integer number;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    Item(Commodity commodity, Integer number) {
        this.commodity = commodity;
        this.number = number;
    }

    @Override
    public String toString() {
        return "{" +
                "\"commodity\":" + commodity +
                ",\"number\":" + number +
                "}";
    }
}
