package com.lgren.design_pattern.state.qqsg.chargeMailAndSell;

import com.lgren.design_pattern.state.qqsg.chargeMailAndSell.variousState.ChargeGoods;
import com.lgren.design_pattern.state.qqsg.chargeMailAndSell.variousState.DeleteUseLess;

/**
 * TODO
 * @author lgren
 * @since 2019-09-24 18:18
 */
public class RunMain implements IState {
    private IState chargeGoods = new ChargeGoods(this);
    private IState deleteUseLess = new DeleteUseLess(this);

    private IState state = chargeGoods;

    @Override
    public void chargeGoods() {
        state.chargeGoods();
    }

    @Override
    public void deleteUseLess() {
        state.deleteUseLess();
    }

    @Override
    public void findShop() {
        state.findShop();
    }

    @Override
    public void initGoodsPage() {
        state.initGoodsPage();
    }

    @Override
    public void findGoods() {
        state.findGoods();
    }

    @Override
    public void sellGoods() {
        state.sellGoods();
    }

    @Override
    public void reset() {
        state.reset();
    }

    @Override
    public void stateName() {

    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }
}
