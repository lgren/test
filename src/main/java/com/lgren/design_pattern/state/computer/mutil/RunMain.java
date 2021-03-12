package com.lgren.design_pattern.state.computer.mutil;

import com.lgren.design_pattern.state.computer.mutil.computer.ComputerStateContext;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 5:10 下午
 */
public class RunMain {
    public static void main(String[] args) {
        ComputerStateContext context = new ComputerStateContext();
        context.turnOn();

    }
}
