package com.example.demo.class4;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

/**
 * @ClassName: OrmUser
 * @Author: maolinnan
 * @Date: 2020/11/19 17:10
 * @Description:
 */
@Entity(tableName = "OrmUser")
public class OrmUser extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}