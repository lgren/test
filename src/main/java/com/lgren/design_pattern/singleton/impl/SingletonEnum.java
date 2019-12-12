package com.lgren.design_pattern.singleton.impl;

/**
 * 枚举方式实现单例(非懒加载推荐方式)
 * @author lgren
 * @since 2019-12-12 10:58
 */
public enum SingletonEnum {
    INSTANCE();

    public String method() {
        return "枚举方式实现单例(非懒加载推荐方式)";
    }
}
