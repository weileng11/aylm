package cn.wl.aylm.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhy.autolayout.AutoLinearLayout;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.wl.aylm.R;
import cn.wl.aylm.base.BaseActivity;
import cn.wl.aylm.presenter.LoginPresenter;
import cn.wl.aylm.ui.view.ILoginView;
import cn.wl.aylm.widget.TopBarLayout;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.ui
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class ActLogin extends BaseActivity implements ILoginView, TextWatcher {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.ed_phone)
    EditText edUsername;
    @InjectView(R.id.ed_pwd)
    EditText edUserpwd;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.ll_content)
    AutoLinearLayout llContent;
    @InjectView(R.id.btn_for_pwd)
    Button btnForPwd;
    @InjectView(R.id.btn_register)
    Button btnRegister;
    @InjectView(R.id.ll_bottom)
    AutoLinearLayout llBottom;

    private LoginPresenter mLoginPresenter;

    /**
     * 初始化界面布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.act_login;
    }

    /**
     * 监听
     */
    @Override
    protected void listenter() {
        edUsername.addTextChangedListener(this);
        edUserpwd.addTextChangedListener(this);
    }

    /**
     * 初始化界面数据
     */
    @Override
    protected void initData() {
        topbar.setTxvTitleName("登录");
        topbar.setTxvLeftShow();
        //目前测试，设置默认的值
        edUsername.setText("13828821554");
        edUserpwd.setText("123456");

        mLoginPresenter = new LoginPresenter(ActLogin.this);
        canNext();
    }

    private void canNext() {
        //如果帐号程度不是11位数字,密码长度小于6,则登录按钮不可用
        if (edUsername.length() != 11 || edUserpwd.length() < 6) {
            btnLogin.setEnabled(false);
        } else {
            btnLogin.setEnabled(true);
        }
    }

    @Override
    public boolean isRememberPassword() {
        return false;
    }

    @Override
    public void startActMain() {
        skipActivity(this, ActMain.class);
    }

    @Override
    public void setAccount(String account) {
        edUsername.setText(account);
    }

    @Override
    public void setPassword(String password) {
        edUserpwd.setText(password);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //输入变化时检查登录按钮是否可用
        canNext();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @OnClick({R.id.btn_login, R.id.btn_for_pwd, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //登录
                mLoginPresenter.login(edUsername.getText().toString(),edUserpwd.getText().toString());
                break;
            case R.id.btn_for_pwd:
                break;
            case R.id.btn_register:
                break;
        }
    }
}
