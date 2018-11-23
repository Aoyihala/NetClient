package com.easy.west.westclient.api.base.event;

/**
 * 基类
 * 主要起作用的就是该类
 * Created by admin on 2018/3/28.
 */

public class BaseEvent<T> {
    //实体
    private T t;
    //是否成功
    private boolean ok;
    //网络请求状态码
    private int code = 1;
    //唯一请求标示码
    private String uuid;
    public BaseEvent(String uuid)
    {
        this.uuid = uuid;
    }
    /**
     * 构造方法传入
     * @param t 实体
     * @param code 状态码
     */
    public BaseEvent(String uuid, T t, int code) {
        this.t = t;
        this.uuid = uuid;
        this.code = code;
    }

    public BaseEvent setEvent(int code,T t)
    {
        this.ok = null!=t;
        this.code = code;
        this.t = t;
        return this;
    }

    /**
     * 是否获取到实体类
     *
     * @return 判断
     */
    public boolean isOk() {
        return this.ok = null != t;
    }

    /**
     * 获取到实体
     *
     * @return 实体
     */
    public T getBean() {
        return t;
    }

    /**
     * 取得网络状态码
     * @return 状态码
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取返回码详情
     *
     * @return 描述信息
     */
    public String getCodeDescribe() {
        switch (code) {
        case -1:
            return "可能是网络未连接";
        case 200:
        case 201:
            return "请求成功，或执行成功。";
        case 400:
            return "参数不符合 API 的要求、或者数据格式验证没有通过";
        case 401:
            return "用户认证失败，或缺少认证信息，比如 access_token 过期，或没传，可以尝试用 refresh_token 方式获得新的 access_token";
        case 403:
            return "当前用户对资源没有操作权限";
        case 404:
            return "资源不存在";
        case 500:
            return "服务器异常";
        case 402:
            return "用户尚未登录";
        default:
            return "未知异常(" + code + ")";
    }
}
}
