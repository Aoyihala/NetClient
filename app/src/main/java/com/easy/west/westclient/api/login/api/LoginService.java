package com.easy.west.westclient.api.login.api;

import com.easy.west.westclient.api.login.bean.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * restfull登录
 * Created by admin on 2018/5/16.
 */

public interface LoginService{

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @POST("/users/login")
    Call<User> login(@Field("phone") String username, @Field("pwd") String password);

}
