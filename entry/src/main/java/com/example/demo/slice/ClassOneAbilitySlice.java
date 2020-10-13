package com.example.demo.slice;

import com.example.demo.ResourceTable;
import com.example.demo.classone.RoundRectView;
import com.example.demo.classthree.BusinessApiManager;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.DirectionalLayout.LayoutConfig;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.utils.TextAlignment;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.media.image.common.PixelFormat;
import ohos.media.image.common.Rect;
import ohos.media.image.common.Size;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.InputStream;

public class ClassOneAbilitySlice extends AbilitySlice {
    DirectionalLayout imageDirectionalLayout;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        initView();
    }

    private void initView(){
        setUIContent(ResourceTable.Layout_main_abilityslice_layout);
        imageDirectionalLayout = (DirectionalLayout)findComponentById(ResourceTable.Id_image_directional_layout);
        imageDirectionalLayout.setAlignment(LayoutAlignment.CENTER);
        RoundRectView rectView = new RoundRectView(this,new Size(200,200));
        rectView.putPixelMap(getPixelMap(ResourceTable.Media_test));//设置图片，ID需要先编译后才能识别到，当前图片资源放置在resources/base/media/test.png
        DirectionalLayout.LayoutConfig layoutConfig = new DirectionalLayout.LayoutConfig(200,200);
        imageDirectionalLayout.addComponent(rectView,layoutConfig);
    }

    /**
     * 通过资源ID获取位图对象
     **/
    private PixelMap getPixelMap(int drawableId) {
        InputStream drawableInputStream = null;
        try {
            drawableInputStream = this.getResourceManager().getResource(drawableId);
            ImageSource.SourceOptions sourceOptions = new ImageSource.SourceOptions();
            sourceOptions.formatHint = "image/png";
            ImageSource imageSource = ImageSource.create(drawableInputStream, sourceOptions);
            ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
            decodingOptions.desiredSize = new Size(0, 0);
            decodingOptions.desiredRegion = new Rect(0, 0, 0, 0);
            decodingOptions.desiredPixelFormat = PixelFormat.ARGB_8888;
            PixelMap pixelMap = imageSource.createPixelmap(decodingOptions);
            return pixelMap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (drawableInputStream != null){
                    drawableInputStream.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
