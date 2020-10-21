package com.lgren.design_pattern.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 英雄
 * @author lgren
 * @since 2020-06-29 5:09 下午
 */
@Data
@AllArgsConstructor
public class Hero {
    private String name;// 名字
    private int attack;// 攻击力
    private int defense;// 防御力

}
