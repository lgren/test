package com.lgren.design_pattern.state.computer.single.state;

import com.lgren.design_pattern.state.computer.single.Context;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 5:02 下午
 */
public class HasOpenedBrowserState extends OpenState {
    public HasOpenedBrowserState(Context context) {
        super(context);
    }

    @Override
    public void openBrowser() {
        System.out.println("已经打开浏览器");
    }

    @Override
    public void closeBrowser() {
        System.out.println("关闭浏览器");
        super.context.setNowState(super.context.OPEN);
    }

    @Override
    public void enlargeBrowser() {
        System.out.println("放大浏览器为2倍");
        super.context.setNowState(super.context.BROWSER_ENLARGE2);
    }
}
