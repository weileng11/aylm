package cn.wl.aylm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.adapter
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> mFragmentList;

    public MyViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragmentList) {
        super(fm);
        mFragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
