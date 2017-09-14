package cn.wl.aylm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.utils.view.MyListview;

import java.util.List;

import butterknife.InjectView;
import cn.wl.aylm.R;
import cn.wl.aylm.adapter.RecordItemAdapter;
import cn.wl.aylm.base.BaseFragment;
import cn.wl.aylm.net.response.AllOrderRespBean;
import cn.wl.aylm.presenter.RecordPresenter;
import cn.wl.aylm.ui.view.IRecordFragmentView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.fragment
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class RecordItemFragment extends BaseFragment implements IRecordFragmentView{
    @InjectView(R.id.diagnose_listview)
    MyListview diagnoseListview;
    @InjectView(R.id.ptr_frame_layout)
    PtrClassicFrameLayout ptrFrameLayout;

    RecordItemAdapter mRecordItemAdapter;
    private String mType;
    private String paramUrl;
    private int currentPage = 1;
    private int mPager=3;
    RecordPresenter mRecordPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecordPresenter = new RecordPresenter(this);
        Bundle arguments = getArguments();
        mType = arguments.getString("type");
        paramUrl=arguments.getString("paramurl");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recoed_array;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initViews();
        initEvents();
        mRecordPresenter.getRecordData(paramUrl,mType,1,mPager);
    }

    private void initEvents() {
        ptrFrameLayout.setLastUpdateTimeRelateObject(this);

        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                mRecordPresenter.getRecordData(paramUrl,mType,++currentPage,mPager);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mRecordPresenter.getRecordData(paramUrl,mType,1,mPager);
            }
        });
    }

    private void initViews() {
        mRecordItemAdapter = new RecordItemAdapter(getContext(), mType);
        diagnoseListview.setAdapter(mRecordItemAdapter);
    }

    @Override
    public void showRecordList(List<AllOrderRespBean.DataBean> data) {
        mRecordItemAdapter.addDatas( data);
        ptrFrameLayout.refreshComplete();
    }

    @Override
    public void completeFresh() {
        ptrFrameLayout.refreshComplete();
    }
}
