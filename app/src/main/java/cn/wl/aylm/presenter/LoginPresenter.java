package cn.wl.aylm.presenter;


import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.model.impl.LoginModel;
import cn.wl.aylm.model.response.LoginResponse;
import cn.wl.aylm.ui.view.ILoginView;

/**
 * Author:Bruce
 * time:2017/8/29.
 * contact：weileng143@163.com
 *
 * @description
 */

public class LoginPresenter {
    private ILoginView mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenter(ILoginView loginView) {
        mLoginView = loginView;
        mLoginModel = new LoginModel(mLoginView.getContext());
    }

    public void login(String account, final String password) {
        mLoginView.showLoading("登录中...");
        mLoginModel.login(account, password, new HttpTaskCallback<LoginResponse>() {

            @Override
            public void onTaskFailed(String errorInfo) {
                mLoginView.hideLoading();
                mLoginView.showMsg(errorInfo);
            }

            @Override
            public void onTaskSuccess(LoginResponse data) {
                mLoginView.hideLoading();
                //进入主页
                mLoginView.startActMain();
            }
        });
    }
}
