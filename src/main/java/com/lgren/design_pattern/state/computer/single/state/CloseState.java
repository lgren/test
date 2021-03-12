package com.lgren.design_pattern.state.computer.single.state;

import com.lgren.design_pattern.state.computer.single.Context;
import com.lgren.design_pattern.state.computer.single.State;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 5:02 下午
 */
public class CloseState extends State {
    public CloseState(Context context) {
        super(context);
    }

    @Override
    public void turnOn() {
        System.out.println("开机");
        super.context.setNowState(super.context.OPEN);
    }

    @Override
    public void shutdown() {
        System.out.println("已经关机");
    }
}
