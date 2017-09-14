package cn.wl.aylm.model;

import java.util.List;

import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.net.response.AllOrderRespBean;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.model
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public interface IRecordModel {
    void getRecordData(String recordUrl,String mType,int cur,int mPageSize,HttpTaskCallback<List<AllOrderRespBean.DataBean>> callback);

}
