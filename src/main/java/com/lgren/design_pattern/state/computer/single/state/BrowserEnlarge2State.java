package com.lgren.design_pattern.state.computer.single.state;

import com.lgren.design_pattern.state.computer.single.Context;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 5:33 下午
 */
public class BrowserEnlarge2State extends HasOpenedBrowserState {
    public BrowserEnlarge2State(Context context) {
        super(context);
    }

    @Override
    public void enlargeBrowser() {
        System.out.println("放大浏览器为4倍");
        super.context.setNowState(super.context.BROWSER_ENLARGE4);
    }
}
