package cn.wl.aylm.model;

import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.model.response.RespnoseResultBean;
import cn.wl.aylm.net.response.UploadPictureRespBean;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.model
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public interface IHeadModel {
    void uploadHead(String imgUrl,HttpTaskCallback<UploadPictureRespBean> callback);
    void updateHead(String headUrl,HttpTaskCallback<RespnoseResultBean> callback);
}
