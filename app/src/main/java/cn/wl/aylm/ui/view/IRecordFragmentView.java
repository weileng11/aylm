package cn.wl.aylm.ui.view;

import java.util.List;

import cn.wl.aylm.base.IBaseView;
import cn.wl.aylm.net.response.AllOrderRespBean;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.ui.view
 * time:2017/9/14.
 * contact：weileng143@163.com
 *
 * @description
 */

public interface IRecordFragmentView extends IBaseView{
    void showRecordList(List<AllOrderRespBean.DataBean> data);
    void completeFresh();
}
