package com.lgren.design_pattern.decorator;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄技能装饰类
 * @author lgren
 * @since 2020-06-29 5:12 下午
 */
public class HeroSkillSkinDecorator extends HeroSkillDecorator {
    private List<String> skinList = new LinkedList<>();
    private String useSkin;

    public HeroSkillSkinDecorator(Hero hero) {
        super(hero);
    }

    public void buySkin(String skinName) {
        skinList.add(skinName);
        System.out.println("购买: " + skinName);
    }

    public void useSkin(String skinName) {
        if (skinList.contains(skinName)) {
            useSkin = skinName;
            System.out.println("使用皮肤: " + skinName);
        } else {
            System.out.println("未购买皮肤: " + skinName);

        }
    }

    @Override
    public boolean useSkill(String skillName) {
        boolean isUse = super.useSkill(skillName);
        if (isUse && useSkin != null) {
            System.out.printf("在皮肤(%s)下技能(%s)展示特效\n", useSkin, skillName);
        }
        return isUse;
    }
}
