package com.lgren.design_pattern.state.computer.single;

import com.lgren.design_pattern.state.computer.single.state.*;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 4:55 下午
 */
public class Context {
    public final State OPEN = new OpenState(this);
    public final State CLOSE = new CloseState(this);
    public final State HAS_OPENED_BROWSER = new HasOpenedBrowserState(this);
    public final State BROWSER_ENLARGE2 = new BrowserEnlarge2State(this);
    public final State BROWSER_ENLARGE4 = new BrowserEnlarge4State(this);
    public final State BROWSER_ENLARGE8 = new BrowserEnlarge8State(this);

    @Getter@Setter
    private State nowState = CLOSE;


    public void turnOn() {
        nowState.turnOn();
    }

    public void shutdown() {
        nowState.shutdown();
    }

    public void openBrowser() {
        nowState.openBrowser();
    }

    public void closeBrowser() {
        nowState.closeBrowser();
    }

    public void enlargeBrowser() {
        nowState.enlargeBrowser();
    }
}
