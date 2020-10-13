package com.example.demo;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilityPackage;

public class DemoAbilityPackage extends AbilityPackage {
    private static Ability mainAbility;//应用程序主界面
    //全局application实例
    private static DemoAbilityPackage baseApplication;
    //对界面的计数
    private int abilityCount = 0;

    @Override
    public void onInitialize() {
        super.onInitialize();
        if (baseApplication == null) {//确保只初始化一次
            baseApplication = this;
        }
    }

    /**
     * 获取application实例
     *
     * @return
     */
    public static DemoAbilityPackage getInstance() {
        return baseApplication;
    }

    /**
     * 设置和获取主界面
     *
     * @param ability
     */
    public static void setMainAbility(Ability ability) {
        mainAbility = ability;
    }

    public static Ability getMainAbility() {
        return mainAbility;
    }
}
