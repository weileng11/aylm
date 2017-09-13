package cn.wl.aylm.model;

import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.net.response.NearbyRespBean;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.model
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public interface IHomeFragmentModel {
    void getNearlyData(HttpTaskCallback<NearbyRespBean> callback);
}
