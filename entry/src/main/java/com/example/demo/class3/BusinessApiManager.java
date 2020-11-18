package com.example.demo.class3;

/**
 * 服务端访问接口管理
 */
public class BusinessApiManager {

    private static BusinessApiService instance;
    public static BusinessApiService get(){
        if (instance == null){
            synchronized (BusinessApiManager.class){
                if (instance == null){
                    instance = ApiManager.get().create(BusinessApiService.class);
                }
            }
        }
        return instance;
    }
}
