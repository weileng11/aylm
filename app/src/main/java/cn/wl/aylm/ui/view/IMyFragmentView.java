package cn.wl.aylm.ui.view;

import cn.wl.aylm.base.IBaseView;
import cn.wl.aylm.model.response.RespnoseResultBean;
import cn.wl.aylm.net.response.UploadPictureRespBean;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.ui.view
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public interface IMyFragmentView extends IBaseView{
       void uploadHeadImage(UploadPictureRespBean  data);
       void updateHeadImage(RespnoseResultBean data);


}
