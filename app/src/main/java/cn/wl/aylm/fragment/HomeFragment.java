package cn.wl.aylm.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.utils.view.MyGridView;
import com.utils.view.MyListview;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;
import cn.wl.aylm.R;
import cn.wl.aylm.adapter.HomeAdapter;
import cn.wl.aylm.adapter.NearlyAdapter;
import cn.wl.aylm.base.BaseFragment;
import cn.wl.aylm.bean.PublicInfo;
import cn.wl.aylm.net.response.NearbyRespBean;
import cn.wl.aylm.presenter.HomeFragmentPresenter;
import cn.wl.aylm.ui.view.IHomeFragmentView;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.fragment
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class HomeFragment extends BaseFragment implements OnBannerListener,IHomeFragmentView{
    private static String Tag = "HomeFragment";

    @InjectView(R.id.tv_city)
    TextView tvCity;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.rl_title)
    AutoRelativeLayout rlTitle;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.home_gv)
    MyGridView homeGv;
    @InjectView(R.id.tv_jk)
    TextView tvJk;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.tv_y)
    TextView tvY;
    @InjectView(R.id.rl_health_knowledge)
    AutoRelativeLayout rlHealthKnowledge;
    @InjectView(R.id.iv_nearby)
    ImageView ivNearby;
    @InjectView(R.id.ll_nearby)
    AutoLinearLayout llNearby;
    @InjectView(R.id.home_listview)
    MyListview homeListview;
    @InjectView(R.id.ll_all_recode)
    AutoLinearLayout llAllRecode;

    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    private View mView;

    private String[] homeList = new String[]{"我要镶牙", "我要种牙", "其他项目", "找门诊",
            "找专家", "客服", "门诊寻求合作", "积分商城"};
    private int[] ivList = new int[]{R.mipmap.home_xtooth, R.mipmap.home_zytooth,
            R.mipmap.home_else, R.mipmap.home_recode, R.mipmap.home_specialist,
            R.mipmap.home_service, R.mipmap.home_collaborate, R.mipmap.home_store};
    private BaseAdapter mBaseAdapter;
    private ArrayList<PublicInfo> pList =new ArrayList<>();
    private HomeAdapter homeAdapter;

    private HomeFragmentPresenter mHomePresenter;
    //附近的店
    NearlyAdapter mNearlyAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mHomePresenter=new HomeFragmentPresenter(this);
        mView=view;
        //设置适配
        mNearlyAdapter=new NearlyAdapter(getActivity());
        homeListview.setAdapter(mNearlyAdapter);
        //设置图片轮播
        setBannerShow();
        //设置gv的图片和文字参数
        setPicAndTvValue();
        //获取附近的店
        mHomePresenter.getNearlyData();
    }

    /**
     * 设置gv文字和图片的参数
     *
     * @return
     */
    public void setPicAndTvValue() {
        for (int i = 0; i < homeList.length; i++) {
            PublicInfo pi = new PublicInfo(homeList[i], ivList[i]);
            pList.add(pi);
        }
        Log.i(Tag, "总数据" + pList.size());

        homeAdapter=new HomeAdapter(getActivity());
        homeGv.setAdapter(homeAdapter);
        homeAdapter.addDatas(pList);

        homeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PublicInfo pi = (PublicInfo) mBaseAdapter.getItem(position);
                if (pi.getName().equals("我要镶牙")) {
                    Bundle bundle=new Bundle();
                    bundle.putString("guid","856dc4d0-08bd-4cd9-b85d-3f391f203554");
//                    bundle.putString("type","1");
//                    showActivity(getActivity(), MyWantToothActivity.class,bundle);
                } else if (pi.getName().equals("我要种牙")) { //跟镶牙界面一样
                    Bundle bundle=new Bundle();
                    bundle.putString("guid","b9f7ce10-eb24-46ba-a5bd-a7c99fe3b377");
//                    bundle.putString("type","2");
//                    showActivity(getActivity(), MyWantToothActivity.class,bundle);
                } else if (pi.getName().equals("其他项目")) {
//                    showActivity(getActivity(), ElseProjectActivity.class);
                } else if (pi.getName().equals("找门诊")) {
//                    showActivity(getActivity(), OutpatientActivity.class);
                } else if (pi.getName().equals("找专家")) {
//                    showActivity(getActivity(), SeekSpecialistActivity.class);
                } else if (pi.getName().equals("客服")) {
//                    showActivity(getActivity(), ElseProjectActivity.class);
                } else if (pi.getName().equals("门诊寻求合作")) {
                    Bundle bundle=new Bundle();
                    bundle.putString("guid","b9f7ce10-eb24-46ba-a5bd-a7c99fe3b377");
//                    showActivity(getActivity(), MyWantToothActivity.class,bundle);
                } else if (pi.getName().equals("积分商城")) {
//                    showActivity(getActivity(), IntegralStoreActivity.class);
                }
            }
        });
    }

    /**
     * 图片轮播
     *
     * @return
     */
    public void setBannerShow() {
        //该数据只进行测试
        String[] urls = getActivity().getResources().getStringArray(R.array.url);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        String[] title = getActivity().getResources().getStringArray(R.array.title);
        List titlelist = Arrays.asList(title);
        titles = new ArrayList(titlelist);

        Banner banner = (Banner) mView.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置图片点击监听
        banner.setOnBannerListener(this);
        //设置标题的颜色

        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNearlyAllData(NearbyRespBean data) {
         mNearlyAdapter.setData(data.data);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

//        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//        @Override
//        public ImageView createImageView(Context context) {
//            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
//            return simpleDraweeView;
//        }
    }

}
