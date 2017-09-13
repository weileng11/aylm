package cn.wl.aylm.model;


import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.model.response.LoginResponse;

/**
 * Author:Bruce
 * time:2017/8/29.
 * contact：weileng143@163.com
 *
 * @description
 */

public interface ILoginModel {
    void login(String account, String password, HttpTaskCallback<LoginResponse> callback);
}
