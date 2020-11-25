package com.example.demo.slice;

import com.example.demo.ResourceTable;
import com.example.demo.class4.OrmDBTest;
import com.example.demo.class4.OrmUser;
import com.example.demo.class4.User;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.window.dialog.ToastDialog;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;
import ohos.data.rdb.*;
import ohos.data.resultset.ResultSet;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.LinkedList;
import java.util.List;

public class ClassFourAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    private Button rdbCreateDb;
    private Button rdbInsert;
    private Button rdbDelete;
    private Button rdbUpdata;
    private Button rdbQuery;
    private Button ormCreateDb;
    private Button ormInsert;
    private Button ormDelete;
    private Button ormUpdata;
    private Button ormQuery;


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_class_four_slice_layout);
        initView();
        initListener();
    }

    private void initView(){
        rdbCreateDb = (Button) findComponentById(ResourceTable.Id_rdb_created);
        rdbInsert = (Button) findComponentById(ResourceTable.Id_rdb_insert);
        rdbDelete = (Button) findComponentById(ResourceTable.Id_rdb_delete);
        rdbUpdata = (Button) findComponentById(ResourceTable.Id_rdb_updata);
        rdbQuery = (Button) findComponentById(ResourceTable.Id_rdb_query);
        ormCreateDb = (Button) findComponentById(ResourceTable.Id_orm_created);
        ormInsert = (Button) findComponentById(ResourceTable.Id_orm_insert);
        ormDelete = (Button) findComponentById(ResourceTable.Id_orm_delete);
        ormUpdata = (Button) findComponentById(ResourceTable.Id_orm_updata);
        ormQuery = (Button) findComponentById(ResourceTable.Id_orm_query);
    }

    private void initListener(){
        rdbCreateDb.setClickedListener(this);
        rdbInsert.setClickedListener(this);
        rdbDelete.setClickedListener(this);
        rdbUpdata.setClickedListener(this);
        rdbQuery.setClickedListener(this);
        ormCreateDb.setClickedListener(this);
        ormInsert.setClickedListener(this);
        ormDelete.setClickedListener(this);
        ormUpdata.setClickedListener(this);
        ormQuery.setClickedListener(this);
    }

    @Override
    public void onClick(Component component) {
        int id = component.getId();
        switch (id){
            case ResourceTable.Id_rdb_created:
                rdbCreateDb();
                break;
            case ResourceTable.Id_rdb_insert:
                rdbInsert();
                break;
            case ResourceTable.Id_rdb_delete:
                rdbDelete();
                break;
            case ResourceTable.Id_rdb_updata:
                rdbUpdata();
                break;
            case ResourceTable.Id_rdb_query:
                rdbQuery();
                break;
            case ResourceTable.Id_orm_created:
                ormCreateDb();
                break;
            case ResourceTable.Id_orm_insert:
                ormInsert();
                break;
            case ResourceTable.Id_orm_delete:
                ormDelete();
                break;
            case ResourceTable.Id_orm_updata:
                ormUpdata();
                break;
            case ResourceTable.Id_orm_query:
                ormQuery();
                break;
        }
    }

    /**
     * 查询对象关系型数据库数据
     */
    private OrmUser ormQuery() {
        OrmPredicates ormPredicates = ormCreateDb().where(OrmUser.class).equalTo("userId","1");
        List<OrmUser> ormUsers =  ormCreateDb().query(ormPredicates);
        if (ormUsers.size() > 0){
            HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "对象关系型数据库查询到userId=1的数据,username = " +ormUsers.get(0).getUserName());
            return ormUsers.get(0);
        }
        return null;
    }

    /**
     * 更新对象关系型数据库数据
     */
    private void ormUpdata() {
        OrmUser ormUser = ormQuery();
        if (ormUser == null){
            return;
        }
        ormUser.setUserName("name2");
        ormCreateDb().update(ormUser);
        ormCreateDb().flush();
    }

    /**
     * 删除对象关系型数据库数据
     */
    private void ormDelete() {
        OrmUser ormUser = ormQuery();
        if (ormUser == null){
            return;
        }
        ormCreateDb().delete(ormUser);
        ormCreateDb().flush();
    }

    /**
     * 插入对象关系型数据库数据
     */
    private void ormInsert() {
        OrmUser ormUser = new OrmUser();
        ormUser.setUserId(1);
        ormUser.setUserName("name1");

        boolean isSuccess = ormCreateDb().insert(ormUser);
        ormCreateDb().flush();
        ToastDialog toastDialog = new ToastDialog(getContext());
        if (isSuccess) {
            toastDialog.setText("对象关系型数据库插入数据成功！");
        }else{
            toastDialog.setText("对象关系型数据库插入数据失败！");
        }
        toastDialog.show();
    }

    /**
     * 创建对象关系型数据库
     */
    private OrmContext ormCreateDb() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        OrmContext ormContext = databaseHelper.getOrmContext("OrmDBTest", "OrmDBTest.db", OrmDBTest.class);
        return ormContext;
    }

    /**
     * 查询关系型数据库数据
     */
    private List<User> rdbQuery() {
        List<User>  result = new LinkedList<>();

        String[] columns = new String[]{"userId","userName"};
        RdbPredicates rdbPredicates = new RdbPredicates("User");
        ResultSet resultSet = rdbCreateDb().query(rdbPredicates,columns);
        while (resultSet.goToNextRow()){
            int userId = resultSet.getInt(resultSet.getColumnIndexForName("userId"));
            String userName = resultSet.getString(resultSet.getColumnIndexForName("userName"));
            User user = new User(userId,userName);
            result.add(user);
            HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "查询到userId=" + userId + "  userName=" +userName);
        }
        return result;
    }

    /**
     * 更新关系型数据库数据
     */
    private void rdbUpdata() {
        RdbPredicates rdbPredicates = new RdbPredicates("User").equalTo("userName","name2");
        ValuesBucket values = new ValuesBucket();
        values.putString("userName","name3");
        //更新数据
        rdbCreateDb().update(values,rdbPredicates);
        //查询数据是否更新完成
        rdbQuery();
    }

    /**
     * 删除关系型数据库数据
     */
    private void rdbDelete() {
        RdbPredicates rdbPredicates = new RdbPredicates("User").equalTo("userName","name1");
        int i = rdbCreateDb().delete(rdbPredicates);
        ToastDialog toastDialog = new ToastDialog(getContext());
        toastDialog.setText("删除关系型数据库数据成功！删除了userName=name1的数据！");
        toastDialog.show();
    }

    /**
     * 插入关系型数据库数据
     */
    private void rdbInsert() {
        ValuesBucket values = new ValuesBucket();
        values.putInteger("userId",1);
        values.putString("userName","name1");
        long id = rdbCreateDb().insert("User",values);
        ToastDialog toastDialog = new ToastDialog(getContext());
        if (id > 0){
            toastDialog.setText("插入关系型数据库成功！");
        }else{
            toastDialog.setText("插入关系型数据库失败,应该是已存在有相同userid的数据了");
        }
        toastDialog.show();

        try {
            //数据重复了，有可能插入异常
            rdbCreateDb().executeSql("insert into User (userId,userName) values (2,'name2')");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 创建关系型数据库
     */
    private RdbStore rdbCreateDb() {
        StoreConfig config = StoreConfig.newDefaultConfig("RdbStore.db");
        DatabaseHelper helper = new DatabaseHelper(getContext());
        RdbStore rdbStore = helper.getRdbStore(config, 1, new RdbOpenCallback() {
            @Override
            public void onCreate(RdbStore rdbStore) {
                //创建数据表
                rdbStore.executeSql("create table if not exists User(userId integer primary key autoincrement,userName text)");
            }

            @Override
            public void onUpgrade(RdbStore rdbStore, int i, int i1) {
                //升级数据库
            }
        });
        return rdbStore;
    }
}
