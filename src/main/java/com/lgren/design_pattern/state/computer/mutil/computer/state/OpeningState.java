package com.lgren.design_pattern.state.computer.mutil.computer.state;

import com.lgren.design_pattern.state.computer.mutil.computer.ComputerStateContext;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 5:02 下午
 */
public class OpeningState extends State {
    public OpeningState(ComputerStateContext context) {
        super(context);
    }

    @Override
    public void turnOn() {
        System.out.println("正在开机...");
    }

    @Override
    public void shutdown() {
        System.out.println("正在开机中, 不能关机...");
    }

}
