package com.easy.west.westclient.api.login.api;

/**
 * 登录接口
 * Created by admin on 2018/5/16.
 */

public interface LoginApi {

    /**
     * 登录
     * @param phone 账号
     * @param password 密码
     * @return 唯一识别码
     */
    String login(String phone,String password);
}
