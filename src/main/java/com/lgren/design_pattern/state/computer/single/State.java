package com.lgren.design_pattern.state.computer.single;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 4:55 下午
 */
public abstract class State {
    protected Context context;

    public State(Context context) {
        this.context = context;
    }

    public void turnOn() {
        System.out.println("当前状态不能开机!");
    }

    public void shutdown() {
        System.out.println("当前状态不能关机!");
    }

    public void openBrowser() {
        System.out.println("当前状态不能打开浏览器!");
    }

    public void closeBrowser() {
        System.out.println("当前状态不能关闭浏览器!");
    }

    public void enlargeBrowser() {
        System.out.println("当前状态不能放大浏览器!");
    }
}
