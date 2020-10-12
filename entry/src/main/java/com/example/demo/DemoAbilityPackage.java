package com.example.demo;

import ohos.aafwk.ability.AbilityPackage;

public class DemoAbilityPackage extends AbilityPackage {
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
}
