package com.lgren.design_pattern.strategy.implement1.attribute.gender;

public interface GenderMale extends Gender {
    @Override
    default String getGender() {
        return "公";
    }
}
