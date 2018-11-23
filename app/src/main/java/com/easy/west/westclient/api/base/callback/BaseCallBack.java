package com.easy.west.westclient.api.base.callback;

import android.support.annotation.NonNull;


import com.easy.west.westclient.api.base.event.BaseEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *·该类是讲对应继承了BaseEvent的子类传入
 * 每个子类又将数据传入父类的构造方法
 * 也就是直接实现了父类构造方法
 * 并用eventbus开启
 * 子类的作用其实就是个标志
 * Created by admin on 2018/3/28.
 */

public class BaseCallBack<T> implements Callback<T>
{
    protected BaseEvent<T> event;                   // 事件

    public <Event extends BaseEvent<T>> BaseCallBack(@NonNull Event event) {
        this.event = event;
    }

    /**
     *
     *
     * @param call
     * @param response 响应
     */
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful())
        {
            String result = response.body().toString();
            if (result.equals("0"))
            {
                //只有0 表示没有内容
                EventBus.getDefault().post(event.setEvent(response.code(), null));
            }
            EventBus.getDefault().post(event.setEvent(response.code(), response.body()));
        }
        else {
            EventBus.getDefault().post(event.setEvent(response.code(), null));
        }
    }

    /**
     *
     *
     * @param call
     * @param t 失败原因
     */
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        EventBus.getDefault().post(event.setEvent(-1, null));
    }
}
