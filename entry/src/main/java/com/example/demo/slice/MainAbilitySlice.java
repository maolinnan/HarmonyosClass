package com.example.demo.slice;

import com.example.demo.classone.BusinessApiManager;
import com.example.demo.classone.HttpDemo;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.DirectionalLayout.LayoutConfig;
import ohos.agp.components.Text;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAbilitySlice extends AbilitySlice {

    private DirectionalLayout myLayout = new DirectionalLayout(this);

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
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
                BusinessApiManager.get().getHtmlContent("https://www.baidu.com").enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (!response.isSuccessful() || response.body() == null){
                            onFailure(null,null);
                            return;
                        }
                        String result = response.body();
                        HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "网页返回结果："+result);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
                        HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "网页访问异常");
                    }
                });


//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String result = HttpDemo.httpGet("http://www.baidu.com");
//                        HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "网页返回结果："+result);
//                    }
//                }).start();
            }
        });
    }
}
