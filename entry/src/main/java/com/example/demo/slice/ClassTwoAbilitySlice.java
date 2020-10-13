package com.example.demo.slice;

import com.example.demo.classthree.BusinessApiManager;
import com.example.demo.classtwo.RxBus;
import com.example.demo.classtwo.Subscribe;
import com.example.demo.classtwo.ThreadMode;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.DirectionalLayout.LayoutConfig;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassTwoAbilitySlice extends AbilitySlice {

    private DirectionalLayout myLayout = new DirectionalLayout(this);

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        RxBus.get().register(this);//注测rxbus
        LayoutConfig config = new LayoutConfig(LayoutConfig.MATCH_PARENT, LayoutConfig.MATCH_PARENT);
        myLayout.setLayoutConfig(config);
        ShapeElement element = new ShapeElement();
        element.setRgbColor(new RgbColor(255, 255, 255));
        myLayout.setBackground(element);

        Text text = new Text(this);
        text.setLayoutConfig(config);
        text.setText("Hello World");
        text.setTextColor(new Color(0xFF000000));
        text.setTextSize(50);
        text.setTextAlignment(TextAlignment.CENTER);
        myLayout.addComponent(text);
        super.setUIContent(myLayout);

        text.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                RxBus.get().send(new RxbusEvent());//发送事件
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        RxBus.get().unRegister(this);//注销rxbus
    }
    /**
     * 接收事件
     * @param rxbusEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void rxBusRxbusEvent(RxbusEvent rxbusEvent) {
        if (rxbusEvent == null) {
            return;
        }
        //执行对应操作
        HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "已经接受到事件！");
    }

    public class RxbusEvent {}
}
