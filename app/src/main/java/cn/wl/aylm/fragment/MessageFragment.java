package cn.wl.aylm.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.utils.view.MyListview;

import java.util.ArrayList;

import butterknife.InjectView;
import cn.wl.aylm.R;
import cn.wl.aylm.adapter.MessageAdapter;
import cn.wl.aylm.base.BaseFragment;
import cn.wl.aylm.bean.PublicInfo;
import cn.wl.aylm.widget.TopBarLayout;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.fragment
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class MessageFragment extends BaseFragment {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.message_lv)
    MyListview messageLv;

    private String[] homeList = new String[]{"我的资产", "客服消息", "优惠促销", "系统通知",
            "法律顾问"};
    private int[] ivList = new int[]{R.mipmap.message_my, R.mipmap.message_kf,
            R.mipmap.message_discounts, R.mipmap.message_notifaction, R.mipmap.message_law};
    private ArrayList<PublicInfo> pList = new ArrayList<>();
    MessageAdapter messageAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fraggment_message;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        topbar.setTxvTitleName("消息");
        topbar.setTxvLeftShow();
        messageAdapter=new MessageAdapter(getActivity());
        messageLv.setAdapter(messageAdapter);
        setListViewValue();
    }

    /**
     * 设置gv文字和图片的参数
     *
     * @return
     */
    public void setListViewValue() {
        for (int i = 0; i < homeList.length; i++) {
            PublicInfo pi = new PublicInfo(homeList[i], ivList[i]);
            pList.add(pi);
        }
        Log.i("INFO", "总数据" + pList.size());
        //设置界面参数值
       messageAdapter.setData(pList);
        messageLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PublicInfo pi = (PublicInfo) messageAdapter.getItem(i);
//                if (pi.getName().equals("我的资产")) {
//                    showActivity(getActivity(), MyPropertyActivity.class);
//                } else if (pi.getName().equals("客服消息")) {
//
//                } else if (pi.getName().equals("优惠促销")) {
//                    showActivity(getActivity(), DiscountPromotionActivity.class);
//                } else if (pi.getName().equals("系统通知")) {
//
//                } else if (pi.getName().equals("法律顾问")) {
//                    showActivity(getActivity(), CounselActivity.class);
//                }

            }
        });
    }
}
