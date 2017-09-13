package cn.wl.aylm.model.impl;

import android.content.Context;

import cn.wl.aylm.model.IHomeFragmentModel;
import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.net.BaseNetEntity;
import cn.wl.aylm.net.OkHttpResponseCallback;
import cn.wl.aylm.net.RequestUrl;
import cn.wl.aylm.net.response.NearbyRespBean;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.presenter
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class HomeFragmentModel implements IHomeFragmentModel {
    private Context mContext;

    public HomeFragmentModel(Context context) {
        mContext = context;
    }

    @Override
    public void getNearlyData(final HttpTaskCallback<NearbyRespBean> callback) {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get2("正在加载数据", false, RequestUrl.getRequestPath(RequestUrl.Get_Nearby_Store), new OkHttpResponseCallback<NearbyRespBean>(NearbyRespBean.class) {
            @Override
            public void onSuccess(NearbyRespBean bean) {
                //回调
                callback.onTaskSuccess(bean);
            }

            @Override
            public void onFailError(String e) {
                callback.onTaskFailed(e);
            }
        });
    }
}
