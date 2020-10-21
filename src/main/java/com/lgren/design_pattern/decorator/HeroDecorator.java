package com.lgren.design_pattern.decorator;

import lombok.Getter;

/**
 * 英雄装饰类
 * @author lgren
 * @since 2020-06-29 5:12 下午
 */
public abstract class HeroDecorator {
    @Getter
    private Hero hero;

    public HeroDecorator(Hero hero) {
        this.hero = hero;
    }
}
