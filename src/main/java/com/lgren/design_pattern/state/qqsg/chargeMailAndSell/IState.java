package com.lgren.design_pattern.state.qqsg.chargeMailAndSell;

/**
 * TODO
 * @author lgren
 * @since 2019-09-24 18:04
 */
public interface IState {
    /**
     * 收取所有物品
     */
    default void chargeGoods() {
        System.out.println("当前状态不能收取所有物品");
    }

    /**
     * 删除无用邮件
     */
    default void deleteUseLess() {
        System.out.println("当前状态不能删除无用邮件");
    }

    /**
     * 找到卖东西界面
     */
    default void findShop() {
        System.out.println("当前状态不能找到卖东西界面");
    }

    /**
     * 将所有物品分页
     */
    default void initGoodsPage() {
        System.out.println("当前状态不能将所有物品分页");
    }

    /**
     * 找到需要卖的物品
     */
    default void findGoods() {
        System.out.println("当前状态不能找到需要卖的物品");
    }

    /**
     * 卖物品
     */
    default void sellGoods() {
        System.out.println("当前状态不能卖物品");
    }

    /**
     * 重置
     */
    void reset();

    void stateName();

}
