package cn.wl.aylm.ui.view;


import cn.wl.aylm.base.IBaseView;

/**
 * Author:Bruce
 * time:2017/8/29.
 * contact：weileng143@163.com
 *
 * @description
 */

public interface ILoginView extends IBaseView {
    boolean isRememberPassword();

    void startActMain();

    void setAccount(String account);

    void setPassword(String password);
}
