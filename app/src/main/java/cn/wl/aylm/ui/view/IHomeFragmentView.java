package cn.wl.aylm.ui.view;

import cn.wl.aylm.base.IBaseView;
import cn.wl.aylm.net.response.NearbyRespBean;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.ui.view
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description  获取附近的店数据
 */

public interface IHomeFragmentView extends IBaseView{
    void showNearlyAllData(NearbyRespBean data);
}
