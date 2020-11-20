package com.example.demo.class5;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * @ClassName: BoxUser
 * @Author: maolinnan
 * @Date: 2020/11/19 18:12
 * @Description:
 */
@Entity
public class BoxUser {
    @Id public long id;
    public int userId;
    public String userName;
}