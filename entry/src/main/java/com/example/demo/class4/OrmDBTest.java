package com.example.demo.class4;

import ohos.data.orm.OrmDatabase;
import ohos.data.orm.annotation.Database;
import ohos.data.rdb.RdbOpenCallback;
import ohos.data.rdb.RdbStore;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

/**
 * @ClassName: OrmDataBase
 * @Author: maolinnan
 * @Date: 2020/11/19 17:11
 * @Description:对象关系型数据库
 */
@Database(entities = {OrmUser.class}, version = 1)
public abstract class OrmDBTest extends OrmDatabase {
    @Override
    public int getVersion() {
        return 1;//当前数据库版本号为1
    }

    @Override
    public RdbOpenCallback getHelper() {
        return new RdbOpenCallback() {
            @Override

            public void onCreate(RdbStore rdbStore) {
                HiLog.warn(new HiLogLabel(HiLog.LOG_APP, 0, "===demo==="), "对象关系映射数据库创建成功！");
            }

            @Override
            public void onUpgrade(RdbStore rdbStore, int oldVersion, int newVersion) {
               //数据库升级
            }
        };
    }
}