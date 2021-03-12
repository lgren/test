package com.lgren.design_pattern.state.computer.single.state;

import com.lgren.design_pattern.state.computer.single.Context;
import com.lgren.design_pattern.state.computer.single.State;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 5:02 下午
 */
public class OpenState extends State {
    public OpenState(Context context) {
        super(context);
    }

    @Override
    public void turnOn() {
        System.out.println("已经开机");
    }

    @Override
    public void shutdown() {
        System.out.println("关机");
        super.context.setNowState(super.context.CLOSE);
    }

    @Override
    public void openBrowser() {
        System.out.println("打开浏览器");
        super.context.setNowState(super.context.HAS_OPENED_BROWSER);
    }

}
