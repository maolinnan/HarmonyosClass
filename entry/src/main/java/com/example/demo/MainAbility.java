package com.example.demo;

import com.example.demo.slice.ClassFiveAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        DemoAbilityPackage.setMainAbility(this);
//        super.setMainRoute(ClassOneAbilitySlice.class.getName());
//        super.setMainRoute(ClassTwoAbilitySlice.class.getName());
//        super.setMainRoute(ClassThreeAbilitySlice.class.getName());
//        super.setMainRoute(ClassFourAbilitySlice.class.getName());
        super.setMainRoute(ClassFiveAbilitySlice.class.getName());
    }
}
