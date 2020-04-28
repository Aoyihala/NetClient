# NetClient
基于retrofit+eventbus的高效网络请求工具
## 1.前言
> - 常见使用的网络加事件响应框架是retrofit+rxjava,虽然rxjava有着链式编程的优势，代码逻辑清晰明了，但是本人实在是忍受不了在业务逻辑复杂的情况下
使用rxjava照成的代码臃肿的情况,所以写了这个整合网络请求工具
> - eventbus是真的不错
## 2.设计模式
> - 标准的mvc
## 3.使用相关
> - 看一下核心类的相关注解
> - 因为是自用所以不做相关介绍
## 4.部分注解
### 类BaseCallBack,基本网络处理回调
 - 该类是将对应继承了BaseEvent的子类传入
 - 每个子类又将数据传入父类的构造方法
 - 也就是直接实现了父类构造方法
 - 并用eventbus进行传递
 - 作用是在请求成功后保存需要的内容，如json序列化后的实体了  
 ![BaseCallBack](https://github.com/Aoyihala/NetClient/blob/master/app/src/main/java/com/easy/west/westclient/api/base/callback/BaseCallBack.java)
### 类BaseEevent,基础网络请求返回实例
<p><code>
public class BaseEvent<T> {
    //实体
    private T t;
    //是否成功
    private boolean ok;
    //网络请求状态码
    private int code = 1;
    //唯一请求标示码
    private String uuid;
    //错误实体描述
    private ErrorBean errorBean;
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
        return this.ok=null!=t;
    }

    /**
     * 获取错误实体
     * @return 错误实体
     */
    public ErrorBean getErrorBean() {
        return errorBean;
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
</code></p>
### retrofit和eventbus的传送门
> - EventBus <https://github.com/greenrobot/EventBus>
> - Retrofit <https://github.com/square/retrofit>
