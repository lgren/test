package com.lgren.design_pattern.state.qqsg.chargeMailAndSell.variousState;

import com.lgren.design_pattern.state.qqsg.chargeMailAndSell.IState;
import com.lgren.design_pattern.state.qqsg.chargeMailAndSell.XY;
import com.lgren.design_pattern.state.qqsg.chargeMailAndSell.RunMain;

/**
 * 收取所有物品
 * @author lgren
 * @since 2019-09-24 18:19
 */
public class ChargeGoods implements IState {
    private RunMain runMain;

    @Override
    public void chargeGoods() {
        System.out.printf("点击邮箱,X:%d Y:%d%n", XY.MAIL.X, XY.MAIL.Y);
        System.out.println("收取邮件物品");
    }

    @Override
    public void reset() {

    }

    @Override
    public void stateName() {
        System.out.println();
    }

    public ChargeGoods(RunMain runMain) {
        this.runMain = runMain;
    }
}
