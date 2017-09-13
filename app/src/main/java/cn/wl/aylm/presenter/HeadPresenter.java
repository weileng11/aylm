package cn.wl.aylm.presenter;

import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.model.impl.HeadModel;
import cn.wl.aylm.model.response.RespnoseResultBean;
import cn.wl.aylm.net.response.UploadPictureRespBean;
import cn.wl.aylm.ui.view.IMyFragmentView;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.presenter
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class HeadPresenter {
    private IMyFragmentView mMyfmView;
    private HeadModel mHomeModel;

    public  HeadPresenter(IMyFragmentView homeView) {
        mMyfmView= homeView;
        mHomeModel = new HeadModel(mMyfmView.getContext());
    }

    public void upLoadHead(String imgUrl){
        mMyfmView.showLoading("上传头像中");

        mHomeModel.uploadHead(imgUrl,new HttpTaskCallback<UploadPictureRespBean>() {

            @Override
            public void onTaskFailed(String errorInfo) {
                mMyfmView.hideLoading();
                mMyfmView.showMsg(errorInfo);
            }

            @Override
            public void onTaskSuccess(UploadPictureRespBean data) {
                mMyfmView.hideLoading();
                mMyfmView.uploadHeadImage(data);
            }
        });
    }

    public void updataHeadData(String uploadUrl){
        mHomeModel.updateHead(uploadUrl,new HttpTaskCallback<RespnoseResultBean>() {

            @Override
            public void onTaskFailed(String errorInfo) {
                mMyfmView.showMsg(errorInfo);
            }

            @Override
            public void onTaskSuccess(RespnoseResultBean data) {
                mMyfmView.updateHeadImage(data);
            }
        });
    }
}
