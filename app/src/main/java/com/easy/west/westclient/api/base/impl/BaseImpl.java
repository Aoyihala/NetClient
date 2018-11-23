package com.easy.west.westclient.api.base.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.easy.west.westclient.Constants;
import com.easy.west.westclient.factory.GsonConverterFactory;

import java.lang.reflect.ParameterizedType;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * 服务的基类
 * 该类获取对应的service并用retrofit开启
 * Created by admin on 2018/3/28.
 */

public class BaseImpl<Service>
{
    private static Retrofit mRetrofit;
    protected Service service;

    public BaseImpl(@NonNull Context context)
    {
        //初始化retrofit
        initretrofit();
        //启动后返回一个实例
       service = mRetrofit.create(getServiceClass());
    }


    /**
     * 获取service类
     * @return
     */
    private Class<Service> getServiceClass() {
        return (Class<Service>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private void initretrofit()
    {
        if (mRetrofit!=null)
        {
            return;
        }
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 配置 client
        OkHttpClient client = new OkHttpClient.Builder()
                //.addInterceptor(interceptor)                // 设置拦截器
                .retryOnConnectionFailure(true)             // 是否重试
                .connectTimeout(5, TimeUnit.SECONDS)        // 连接超时事件
                .addNetworkInterceptor(loggingInterceptor)   // 打印请求日志
                //.authenticator(mAuthenticator)              // 认证失败自动刷新token
                .build();

        // 配置 Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)                         // 设置 base url
                .client(client)                                     // 设置 client
                .addConverterFactory(GsonConverterFactory.create()) // 设置 Json 转换工具
                .build();
    }


}
