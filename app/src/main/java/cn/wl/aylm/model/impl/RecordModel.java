package cn.wl.aylm.model.impl;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

import cn.wl.aylm.model.IRecordModel;
import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.net.BaseNetEntity;
import cn.wl.aylm.net.OkHttpResponseCallback;
import cn.wl.aylm.net.RequestUrl;
import cn.wl.aylm.net.response.AllOrderRespBean;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.model.impl
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class RecordModel implements IRecordModel{
    Context context;
    public RecordModel(Context context){
        this.context=context;
    }

    @Override
    public void getRecordData(String paramUrl, String mType, int mycurrentPage, int mPageSize, final HttpTaskCallback<List<AllOrderRespBean.DataBean>> callback) {
        String patmentBuyUrl = "";
        if (TextUtils.equals(mType, "1")) {
            patmentBuyUrl = RequestUrl.Get_Diagnostic_Record + "&PageNo=" + mycurrentPage + "&PageSize=" + mPageSize;
        } else {
            patmentBuyUrl = RequestUrl.Get_Diagnostic_Record + "&IsBack=0"
                    + paramUrl + "&PageNo=" + mycurrentPage + "&PageSize=" + mPageSize;
        }
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.get((Activity) context, patmentBuyUrl, "正在请求数据",
                true, new OkHttpResponseCallback<AllOrderRespBean>(AllOrderRespBean.class) {
                    @Override
                    public void onSuccess(AllOrderRespBean bean) {
                        //回调
                        callback.onTaskSuccess(bean.data);
                    }

                    @Override
                    public void onFailError(String e) {
                        callback.onTaskFailed(e);
                    }

                });
    }
}
