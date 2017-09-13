package cn.wl.aylm.ui;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.InjectView;
import cn.wl.aylm.R;
import cn.wl.aylm.adapter.MyViewPagerAdapter;
import cn.wl.aylm.base.BaseActivity;
import cn.wl.aylm.fragment.HomeFragment;
import cn.wl.aylm.fragment.MessageFragment;
import cn.wl.aylm.fragment.MyFragment;
import cn.wl.aylm.fragment.RecordFragment;
import cn.wl.aylm.widget.HomeViewPager;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.ui
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class ActMain extends BaseActivity {
    @InjectView(R.id.rb_index)
    RadioButton rbIndex;
    @InjectView(R.id.rb_record)
    RadioButton rbRecord;
    @InjectView(R.id.rb_message)
    RadioButton rbMessage;
    @InjectView(R.id.rb_my)
    RadioButton rbMy;
    @InjectView(R.id.rd_group)
    RadioGroup  mGroup;
    @InjectView(R.id.m_viewpager)
    HomeViewPager mPager;

    private HomeFragment mHomeFg;
    private MessageFragment mMessageFg;
    private MyFragment mMyFg;
    private RecordFragment mRdFg;
    private ArrayList<Fragment> fragmentList;
    /**
     * 初始化界面布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.act_main;
    }

    /**
     * 监听
     */
    @Override
    protected void listenter() {
        mGroup.setOnCheckedChangeListener(new CheckedChangeListener());
        mPager.setOnPageChangeListener(new PageChangeListener());
    }

    /**
     * 初始化界面数据
     */
    @Override
    protected void initData() {
        mHomeFg = new HomeFragment();
        mRdFg = new RecordFragment();
        mMessageFg = new MessageFragment();
        mMyFg = new MyFragment();

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(mHomeFg);
        fragmentList.add(mRdFg);
        fragmentList.add(mMessageFg);
        fragmentList.add(mMyFg);

        // RadioGroup CheckedChange
        mGroup.check(R.id.rb_index);
        mPager.setAdapter(new MyViewPagerAdapter(this.getSupportFragmentManager(),fragmentList));
        mPager.setOffscreenPageLimit(4);
    }

    private class CheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_index:
                    mPager.setCurrentItem(0);
                    break;
                case R.id.rb_record:
                    mPager.setCurrentItem(1);
//                    RecordFragment.pager.setCurrentItem(0);
                    break;
                case R.id.rb_message:
                    mPager.setCurrentItem(2);
                    break;
                case R.id.rb_my:
                    mPager.setCurrentItem(3);
                    break;
            }
        }
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mGroup.check(R.id.rb_index);
                    break;
                case 1:
                    mGroup.check(R.id.rb_record);
                    break;
                case 2:
                    mGroup.check(R.id.rb_message);
                    break;
                case 3:
                    mGroup.check(R.id.rb_my);
                    break;
            }
        }

        public void onPageScrollStateChanged(int arg0) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }

    private int backKeyCount; // 返回按键次数
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // moveTaskToBack(false);
            if (backKeyCount == 0) {
                String tip = "再按一次退出应用";
                Toast.makeText(this, tip, Toast.LENGTH_LONG).show();
                backKeyCount++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        backKeyCount = 0;
                    }
                }, 1500);
            } else {
                backKeyCount = 0;
                finish(); //销毁主界面

            }
        }
        return false;
    }
}
