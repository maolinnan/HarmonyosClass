package com.example.demo.slice;

import com.example.demo.ResourceTable;
import com.example.demo.class5.BoxUser;
import com.example.demo.class5.BoxUser_;
import com.example.demo.class5.MyObjectBox;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import java.util.List;

public class ClassFiveAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    private BoxStore boxStore;

    private Button boxCreateDb;
    private Button boxInsert;
    private Button boxDelete;
    private Button boxUpdata;
    private Button boxQuery;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_class_five_slice_layout);
        initView();
        initListener();
    }

    private void initView(){
        boxCreateDb = (Button) findComponentById(ResourceTable.Id_box_created);
        boxInsert = (Button) findComponentById(ResourceTable.Id_box_insert);
        boxDelete = (Button) findComponentById(ResourceTable.Id_box_delete);
        boxUpdata = (Button) findComponentById(ResourceTable.Id_box_updata);
        boxQuery = (Button) findComponentById(ResourceTable.Id_box_query);
    }

    private void initListener(){
        boxCreateDb.setClickedListener(this);
        boxInsert.setClickedListener(this);
        boxDelete.setClickedListener(this);
        boxUpdata.setClickedListener(this);
        boxQuery.setClickedListener(this);
    }

    @Override
    public void onClick(Component component) {
        int id = component.getId();
        switch (id){
            case ResourceTable.Id_box_created:
                boxCreateDb();
                break;
            case ResourceTable.Id_box_insert:
                boxInsert();
                break;
            case ResourceTable.Id_box_delete:
                boxDelete();
                break;
            case ResourceTable.Id_box_updata:
                boxUpdata();
                break;
            case ResourceTable.Id_box_query:
                boxQuery();
                break;
        }
    }

    private void boxQuery() {
       List<BoxUser> result =  boxCreateDb().query().build().find();
       HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "NoSQl查询到的数据条数：" +result.size());
       for(int i = 0 ; i < result.size() ; i ++){
           HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "NoSQl查询到的数据内容：" +result.get(i).userId + "====="+i+"====" + result.get(i).userName);
       }
    }

    private void boxUpdata() {
        List<BoxUser> result =  boxCreateDb().query().build().find();
        for(int i = 0 ; i < result.size() ; i ++){
            BoxUser boxUser = result.get(i);
            boxUser.userName = "usernameUpdata";
        }
        boxCreateDb().put(result);
        HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "NoSQl更新数据成功");
    }

    private void boxDelete() {
        boxCreateDb().query().equal(BoxUser_.userName,"name1").build().remove();
        HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "NoSQl插入数据删除成功");
    }

    private void boxInsert() {
        BoxUser boxUser = new BoxUser();
        boxUser.userId = 1;
        boxUser.userName = "name1";
        boxCreateDb().put(boxUser);
        HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "NoSQl插入数据成功");
    }

    private Box boxCreateDb() {
        if (boxStore == null) {
            boxStore = MyObjectBox.builder().androidContext(getApplicationContext()).build();
            HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "NoSQl初始化数据库成功");
        }
        Box box = boxStore.boxFor(BoxUser.class);
        return box;
    }
}
