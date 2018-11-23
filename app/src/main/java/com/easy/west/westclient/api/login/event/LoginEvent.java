package com.easy.west.westclient.api.login.event;

import com.easy.west.westclient.api.base.event.BaseEvent;
import com.easy.west.westclient.api.login.bean.User;

/**
 * 登录事件
 * Created by admin on 2018/5/16.
 */

public class LoginEvent extends BaseEvent<User> {
    public LoginEvent(String uuid) {
        super(uuid);
    }

    public LoginEvent(String uuid, User user, int code) {
        super(uuid, user, code);
    }
}
