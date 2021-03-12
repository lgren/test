package com.lgren.design_pattern.state.computer.mutil.computer;

import com.lgren.design_pattern.state.computer.mutil.computer.state.*;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 * @author lgren
 * @since 2020-10-26 4:55 下午
 */
public class ComputerStateContext {
    public final State OPEN = new OpenState(this);
    public final State OPENING = new OpeningState(this);
    public final State CLOSE = new CloseState(this);

    @Getter
    @Setter
    private State nowState = CLOSE;


    public void turnOn() {
        nowState.turnOn();
    }

    public void shutdown() {
        nowState.shutdown();
    }
}
