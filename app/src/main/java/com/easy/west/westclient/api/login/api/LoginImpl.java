package com.easy.west.westclient.api.login.api;

import android.content.Context;
import android.support.annotation.NonNull;

import com.easy.west.westclient.api.base.callback.BaseCallBack;
import com.easy.west.westclient.api.base.impl.BaseImpl;
import com.easy.west.westclient.api.login.bean.User;
import com.easy.west.westclient.api.login.event.LoginEvent;
import com.easy.west.westclient.util.UUIDGenerator;

/**
 * 登录实现
 * Created by admin on 2018/5/16.
 */

public class LoginImpl extends BaseImpl<LoginService> implements LoginApi{
    public LoginImpl(@NonNull Context context) {
        super(context);
    }

    /**
     * 登录
     * @param phone 账号
     * @param password 密码
     * @return
     */
    @Override
    public String login(String phone, String password) {
        String uuid = UUIDGenerator.getUUID();
        service.login(phone,password).enqueue(new BaseCallBack<>(new LoginEvent(uuid)));
        return uuid;
    }
}
