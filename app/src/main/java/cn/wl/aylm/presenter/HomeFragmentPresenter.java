package cn.wl.aylm.presenter;

import cn.wl.aylm.model.callback.HttpTaskCallback;
import cn.wl.aylm.model.impl.HomeFragmentModel;
import cn.wl.aylm.net.response.NearbyRespBean;
import cn.wl.aylm.ui.view.IHomeFragmentView;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.presenter
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description  附近的店
 */

public class HomeFragmentPresenter {
    private IHomeFragmentView mHomeView;
    private HomeFragmentModel mHomeModel;

    public HomeFragmentPresenter(IHomeFragmentView homeView) {
        mHomeView= homeView;
        mHomeModel = new HomeFragmentModel(mHomeView.getContext());
    }

    public void getNearlyData(){
        mHomeView.showLoading();
        mHomeModel.getNearlyData(new HttpTaskCallback<NearbyRespBean>() {
            @Override
            public void onTaskFailed(String errorInfo) {
                mHomeView.hideLoading();
                mHomeView.showMsg(errorInfo);
            }

            @Override
            public void onTaskSuccess(NearbyRespBean data) {
                mHomeView.hideLoading();
                mHomeView.showNearlyAllData(data);
            }
        });
    }
}
