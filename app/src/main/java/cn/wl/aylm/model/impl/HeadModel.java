package cn.wl.aylm.model.impl;

import android.app.Activity;
import android.content.Context;

import com.utils.SharedPreferencesUtils;

import java.io.IOException;

import cn.wl.aylm.model.IHeadModel;
import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.model.response.RespnoseResultBean;
import cn.wl.aylm.net.BaseNetEntity;
import cn.wl.aylm.net.OkHttpResponseCallback;
import cn.wl.aylm.net.RequestUrl;
import cn.wl.aylm.net.response.UploadPictureRespBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.model.impl
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class HeadModel implements IHeadModel{
    Context context;
    public HeadModel(Context context){
        this.context=context;
    }
    @Override
    public void uploadHead(String cropImagePath, final HttpTaskCallback<UploadPictureRespBean> callback) {
        BaseNetEntity baseNetEntity = new BaseNetEntity();
        baseNetEntity.uploadOneImg(cropImagePath, RequestUrl.Upload_More_Pic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String error= e.getMessage();
                callback.onTaskFailed(error);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //特别注意:response.body().string()只会被执行一次，在第一次必须要用string接收，否则
                //会出现null,坑了一波
                String str = response.body().string();
                //开始转换
                UploadPictureRespBean bean = BaseNetEntity.JSONToObject(str, UploadPictureRespBean.class);
                //回调
                callback.onTaskSuccess(bean);
                System.out.println("上传照片成功：response = ");
            }
        });
    }

    @Override
    public void updateHead(String headUrl, final HttpTaskCallback<RespnoseResultBean> callback) {
        //开始提交订单
        final BaseNetEntity baseNetEntity = new BaseNetEntity();
        //这里将对象转换成为字符串
        final String mPersonInfoCommitUrl = RequestUrl.getRequestPath(RequestUrl.Commit_PersonInfo)+
                SharedPreferencesUtils.getString(context, "memlogin", "") +
                "&Photo=" + headUrl;
        baseNetEntity.get((Activity) context, mPersonInfoCommitUrl, "正在加载数据", true,
                new OkHttpResponseCallback<RespnoseResultBean>(RespnoseResultBean.class) {
                    @Override
                    public void onSuccess(RespnoseResultBean bean) {
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
