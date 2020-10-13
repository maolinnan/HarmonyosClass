package com.example.demo.classthree;

import com.example.demo.DemoAbilityPackage;
import ohos.app.Environment;
import okhttp3.*;
import retrofit2.Retrofit;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * 提供获取Retrofit对象的方法
 */
public class ApiManager {
    private static final String BUSINESS_BASE_HTTP_URL = "http://www.baidu.com";

    private static Retrofit instance;
    private static OkHttpClient mOkHttpClient;

    private ApiManager(){}

    public static Retrofit get(){
        if (instance == null){
            synchronized (ApiManager.class){
                if (instance == null){
                    setClient();
                    instance = new Retrofit.Builder().baseUrl(BUSINESS_BASE_HTTP_URL).
                            addConverterFactory(ApiResponseConverterFactory.create()).client(mOkHttpClient).build();
                }
            }
        }
        return instance;
    }

    private static void setClient(){
        if (mOkHttpClient != null){
            return;
        }
        Cache cache = new Cache(new File(getRootPath(Environment.DIRECTORY_DOCUMENTS),"HttpCache"),1024*1024*100);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .followRedirects(false)//关闭重定向
//                .addInterceptor(new AppendUrlParamIntercepter())
                .cache(cache)
                .retryOnConnectionFailure(false)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .readTimeout(8,TimeUnit.SECONDS)
                .writeTimeout(8,TimeUnit.SECONDS)
                .connectTimeout(8, TimeUnit.SECONDS);
//                .protocols(Collections.singletonList(Protocol.HTTP_1_1));
        mOkHttpClient = builder.build();
        mOkHttpClient.dispatcher().setMaxRequests(100);
    }

    private static String getRootPath(String dirs) {
        String path = DemoAbilityPackage.getInstance().getCacheDir() + "/" + dirs;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }
}
