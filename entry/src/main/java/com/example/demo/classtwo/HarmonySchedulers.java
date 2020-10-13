package com.example.demo.classtwo;

import com.example.demo.DemoAbilityPackage;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ohos.app.dispatcher.TaskDispatcher;

import java.util.concurrent.Executor;

public class HarmonySchedulers implements Executor {

    private static HarmonySchedulers instance;

    private final Scheduler mMainScheduler;
    private TaskDispatcher uiTaskDispatcher;

    private HarmonySchedulers() {
        mMainScheduler = Schedulers.from(this);
    }

    public static synchronized Scheduler mainThread() {
        if (instance == null) {
            instance = new HarmonySchedulers();
        }
        return instance.mMainScheduler;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        if (uiTaskDispatcher == null) {
            uiTaskDispatcher = DemoAbilityPackage.getMainAbility().getUITaskDispatcher();//注意，这里要用Ability来获取UI线程的任务发射器，Ability自己想办法获取
        }
        uiTaskDispatcher.delayDispatch(command, 10);
    }
}
