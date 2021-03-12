package com.lgren.design_pattern.state.computer.single.state;

import com.lgren.design_pattern.state.computer.single.Context;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 5:33 下午
 */
public class BrowserEnlarge4State extends HasOpenedBrowserState {
    public BrowserEnlarge4State(Context context) {
        super(context);
    }

    @Override
    public void enlargeBrowser() {
        System.out.println("放大浏览器为8倍");
        super.context.setNowState(super.context.BROWSER_ENLARGE8);
    }
}
