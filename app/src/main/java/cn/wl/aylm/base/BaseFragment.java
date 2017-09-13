package cn.wl.aylm.base;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cn.wl.aylm.utils.T;

/**
 * Author:lt
 * time:2017/7/19.
 * contact：weileng143@163.com
 *
 * @description
 */

public abstract class BaseFragment extends Fragment {

    //    protected BaseActivity mActivity;
    //获取布局文件ID
    protected abstract int getLayoutId();

    protected abstract void initView(View view, Bundle savedInstanceState);


    /**
     * 跳转后，当前界面自销毁
     */
    public void skipActivity(Activity aty, Class<?> cls) {
        showActivity(aty, cls);
        aty.finish();
    }

    /**
     * 跳转后，当前界面自销毁
     */
    public void skipActivity(Activity aty, Intent it) {
        showActivity(aty, it);
        aty.finish();
    }

    /**
     * 跳转后，当前界面自销毁
     */
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        showActivity(aty, cls, extras);
        aty.finish();
    }

    /**
     * Activity跳转
     */
    public void showActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * Activity跳转
     */
    public void showActivity(Activity aty, Intent it) {
        aty.startActivity(it);
    }

    /**
     * Activity跳转
     */
    public void showActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.inject(this, view);
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //销毁定位
//        LoveToothApplication.getInstance().destroyLocation();
        ButterKnife.reset(this);
    }

    public void showMsg(String msg) {
        T.showShort(getActivity(), msg);
    }

    ProgressDialog dialog;

    public void showLoading() {
        showLoading("正在加载,请稍后...");
    }

    public void showLoading(String message) {
        // loadingMsg.setText(message);
        // loadingView.setVisibility(View.VISIBLE);
        if (dialog == null) {
            dialog = new ProgressDialog(getActivity());
            dialog.setCancelable(false);
        }
        dialog.setMessage(message);
        dialog.show();
    }

    public void hideLoading() {
        // loadingView.setVisibility(View.GONE);
        dialog.dismiss();
    }

    public void finish() {

    }
}
