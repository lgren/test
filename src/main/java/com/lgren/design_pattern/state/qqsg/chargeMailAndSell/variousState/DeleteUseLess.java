package com.lgren.design_pattern.state.qqsg.chargeMailAndSell.variousState;

import com.lgren.design_pattern.state.qqsg.chargeMailAndSell.IState;
import com.lgren.design_pattern.state.qqsg.chargeMailAndSell.RunMain;

/**
 * 删除无用邮件
 * @author lgren
 * @since 2019-09-24 18:19
 */
public class DeleteUseLess implements IState {
    private RunMain runMain;

    @Override
    public void deleteUseLess() {
        System.out.println("删除邮件物品");
    }

    @Override
    public void reset() {

    }

    @Override
    public void stateName() {
        System.out.println();
    }

    public DeleteUseLess(RunMain runMain) {
        this.runMain = runMain;
    }

}
