package com.example.demo.classthree;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * BaseResponse的转换器
 */
public class ApiResponseConverterFactory extends Converter.Factory {

    public static Converter.Factory create(){
        return new ApiResponseConverterFactory();
    }

    @Override
    public Converter<ResponseBody, String> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new StringResponseBodyConverter();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return null;
    }

    class StringResponseBodyConverter implements Converter<ResponseBody, String> {
        @Override
        public String convert(ResponseBody value) throws IOException {
            String s = value.string();
            return s;
        }
    }
}


