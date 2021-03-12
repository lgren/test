package com.lgren.design_pattern.state.computer.mutil.computer.state;

import com.lgren.design_pattern.state.computer.mutil.computer.ComputerStateContext;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 5:02 下午
 */
public class CloseState extends State {
    public CloseState(ComputerStateContext context) {
        super(context);
    }

    @Override
    public void turnOn() {
        System.out.println("开机");
        super.context.setNowState(super.context.OPENING);
    }

    @Override
    public void shutdown() {
        System.out.println("已经关机");
    }
}
