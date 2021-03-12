package com.lgren.design_pattern.state.computer.mutil.computer.state;

import com.lgren.design_pattern.state.computer.mutil.computer.ComputerStateContext;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 4:55 下午
 */
public abstract class State {
    protected ComputerStateContext context;

    public State(ComputerStateContext context) {
        this.context = context;
    }

    public void turnOn() {
        System.out.println("当前状态不能开机!");
    }

    public void shutdown() {
        System.out.println("当前状态不能关机!");
    }

}
