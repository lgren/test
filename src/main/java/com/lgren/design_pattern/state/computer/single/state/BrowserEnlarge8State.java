package com.lgren.design_pattern.state.computer.single.state;

import com.lgren.design_pattern.state.computer.single.Context;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 5:33 下午
 */
public class BrowserEnlarge8State extends HasOpenedBrowserState {
    public BrowserEnlarge8State(Context context) {
        super(context);
    }

    @Override
    public void enlargeBrowser() {
        System.out.println("还原正常浏览器大小");
        super.context.setNowState(super.context.HAS_OPENED_BROWSER);
    }
}
