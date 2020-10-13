package com.example.demo.classthree;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

/**
 * @ClassName: HttpLinkDemo
 * @Author: maolinnan
 * @Date: 2020/10/12 15:30
 * @Description:
 */
public class HttpDemo {
    /**
     *访问url，获取内容
     * @param urlStr
     * @return
     */
    public static String httpGet(String urlStr){
        StringBuilder sb = new StringBuilder();
        try{
            //添加https信任
            SSLContext sslcontext = SSLContext.getInstance("SSL");//第一个参数为协议,第二个参数为提供者(可以缺省)
            TrustManager[] tm = {new HttpX509TrustManager()};
            sslcontext.init(null, tm, new SecureRandom());
            HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
                public boolean verify(String s, SSLSession sslsession) {
                    System.out.println("WARNING: Hostname is not matched for cert.");
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.connect();
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String temp;
                while ((temp = reader.readLine()) != null) {
                    sb.append(temp);
                }
                reader.close();
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return sb.toString();
    }
}