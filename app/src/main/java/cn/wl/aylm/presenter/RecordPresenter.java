package cn.wl.aylm.presenter;

import java.util.List;

import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.model.impl.RecordModel;
import cn.wl.aylm.net.response.AllOrderRespBean;
import cn.wl.aylm.ui.view.IRecordFragmentView;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.presenter
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class RecordPresenter {
    private IRecordFragmentView mMyfmView;
    private RecordModel mHomeModel;

    public RecordPresenter(IRecordFragmentView homeView) {
        mMyfmView = homeView;
        mHomeModel = new RecordModel(mMyfmView.getContext());
    }

    public void getRecordData(String paramUrl,String mType,int cur,int pager) {
        mMyfmView.showLoading();
        mHomeModel.getRecordData(paramUrl, mType, cur, pager, new HttpTaskCallback<List<AllOrderRespBean.DataBean>>() {
            @Override
            public void onTaskFailed(String errorInfo) {
                mMyfmView.hideLoading();
                mMyfmView.completeFresh();
                mMyfmView.showMsg(errorInfo);
            }

            @Override
            public void onTaskSuccess(List<AllOrderRespBean.DataBean> data) {
                mMyfmView.hideLoading();
                mMyfmView.completeFresh();
                mMyfmView.showRecordList(data);
            }
        });

}
}
