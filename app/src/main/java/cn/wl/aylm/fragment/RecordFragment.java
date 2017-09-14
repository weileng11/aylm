package cn.wl.aylm.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import cn.wl.aylm.R;
import cn.wl.aylm.adapter.MyFragmentPagerAdapter;
import cn.wl.aylm.base.BaseFragment;
import cn.wl.aylm.widget.TopBarLayout;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.fragment
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class RecordFragment extends BaseFragment {
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.pager)
    ViewPager viewPager;
    @InjectView(R.id.my_order_tab_layout)
    TabLayout tabLayout;
    private List<String> titles;
    private List<String> states;
    private List<String> paramUrl;
    private List<Fragment> fragments;
    private PagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        topbar.setTxvTitleName(getActivity().getResources().getString(R.string.record));
        topbar.setTxvLeftShow();
        initBase();
        initViewPager();
    }

    private void initBase() {
        titles = new ArrayList<>();
        states = new ArrayList<>();
        paramUrl=new ArrayList<>();
        titles.add("全部");
        titles.add("待付款");
        titles.add("待使用");
        titles.add("待评价");
        states.add("1");
        states.add("2");
        states.add("3");
        states.add("4");

        paramUrl.add(null);
        paramUrl.add("&PaymentStatus=0");
        paramUrl.add("&oderstatus=1");
        paramUrl.add("&oderstatus=5");
    }

    private void initViewPager() {
        fragments = new ArrayList<>();
        int size = states.size();
        for (int i = 0; i < size; i++) {
            RecordItemFragment  fragment = new RecordItemFragment();
            Bundle bundle = new Bundle();
//            bundle.putString("state", states.get(i));
            bundle.putString("type",states.get(i));
            bundle.putString("paramurl", paramUrl.get(i));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        adapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(fragments.size() - 1);
        tabLayout.setupWithViewPager(viewPager);
    }
}
