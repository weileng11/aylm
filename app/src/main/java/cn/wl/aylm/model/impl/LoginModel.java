package cn.wl.aylm.model.impl;

import android.content.Context;

import cn.wl.aylm.model.ILoginModel;
import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.model.response.LoginResponse;
import cn.wl.aylm.net.BaseNetEntity;
import cn.wl.aylm.net.OkHttpResponseCallback;
import cn.wl.aylm.net.RequestUrl;
import cn.wl.aylm.utils.EncodeUtil;


/**
 * Author:Bruce
 * time:2017/8/29.
 * contact：weileng143@163.com
 *
 * @description
 */

public class LoginModel implements ILoginModel {
    private Context mContext;
    public LoginModel(Context context) {
        mContext = context;
    }
    @Override
    public void login(String account, String password, final HttpTaskCallback<LoginResponse> callback) {
        BaseNetEntity baseNetEntity=new BaseNetEntity();
        String loginUrl= RequestUrl.getRequestPath(RequestUrl.Login_Tooth)+ account +
                "&Pwd=" + EncodeUtil.encode_MD5(password) + "&password" +
                "=" + EncodeUtil.encode_MD5(password);
        baseNetEntity.get2("正在登录中", false,loginUrl, new OkHttpResponseCallback<LoginResponse>(LoginResponse.class) {
            @Override
            public void onSuccess(LoginResponse bean) {
              callback.onTaskSuccess(bean);
            }

            @Override
            public void onFailError(String e) {
                callback.onTaskFailed(e);
            }
        });
    }


}
