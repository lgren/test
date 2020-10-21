package com.lgren.design_pattern.decorator;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄技能装饰类
 * @author lgren
 * @since 2020-06-29 5:12 下午
 */
public class HeroSkillDecorator extends HeroDecorator {
    private List<String> skillList = new LinkedList<>();

    public HeroSkillDecorator(Hero hero) {
        super(hero);
    }

    public void learnSkill(String skillName) {
        skillList.add(skillName);
        System.out.println("学习: " + skillName);
    }

    public boolean useSkill(String skillName) {
        boolean isLearn = skillList.contains(skillName);
        System.out.println(isLearn ? ("使用技能: " + skillName) : ("未学习技能: " + skillName));
        return isLearn;
    }
}
