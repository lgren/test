package com.lgren.design_pattern.decorator;

/**
 * 总测试类
 * @author lgren
 * @since 2020-06-29 5:14 下午
 */
public class Main {
    public static void main(String[] args) {
        Hero xia = new Hero("霞", 100, 100);
        HeroSkillDecorator heroSkill = new HeroSkillDecorator(xia);
        heroSkill.learnSkill("Q");
        heroSkill.learnSkill("W");
        heroSkill.learnSkill("E");
        heroSkill.useSkill("Q");
        heroSkill.useSkill("R");
        heroSkill.learnSkill("R");
        heroSkill.useSkill("R");

    }
}
