package com.example.demo.classone;

import retrofit2.Call;
import retrofit2.http.*;

/**
 * 服务端访问接口
 */
public interface BusinessApiService {
    /**
     * 获取网页信息
     * @param url
     * @return
     */
    @GET()
    Call<String> getHtmlContent(@Url String url);
}
