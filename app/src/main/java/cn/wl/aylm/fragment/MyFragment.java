package cn.wl.aylm.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.utils.SharedPreferencesUtils;
import com.utils.view.MyGridView;

import java.io.File;
import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.wl.aylm.R;
import cn.wl.aylm.adapter.MyFragmentAdapter;
import cn.wl.aylm.base.BaseFragment;
import cn.wl.aylm.bean.PublicInfo;
import cn.wl.aylm.model.response.RespnoseResultBean;
import cn.wl.aylm.model.upload.UploadHead;
import cn.wl.aylm.net.response.UploadPictureRespBean;
import cn.wl.aylm.presenter.HeadPresenter;
import cn.wl.aylm.ui.ActClipImage;
import cn.wl.aylm.ui.view.IMyFragmentView;
import cn.wl.aylm.widget.TopBarLayout;

import static android.app.Activity.RESULT_OK;
import static cn.wl.aylm.model.upload.UploadHead.READ_EXTERNAL_STORAGE_REQUEST_CODE;
import static cn.wl.aylm.model.upload.UploadHead.REQUEST_CAPTURE;
import static cn.wl.aylm.model.upload.UploadHead.REQUEST_CROP_PHOTO;
import static cn.wl.aylm.model.upload.UploadHead.REQUEST_PICK;
import static cn.wl.aylm.model.upload.UploadHead.WRITE_EXTERNAL_STORAGE_REQUEST_CODE;
import static cn.wl.aylm.model.upload.UploadHead.tempFile;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.fragment
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class MyFragment extends BaseFragment implements IMyFragmentView {
    private String Tag = "MyFragment";
    @InjectView(R.id.topbar)
    TopBarLayout topbar;
    @InjectView(R.id.img_head)
    ImageView imgHead;
    @InjectView(R.id.tv_user_name)
    TextView tvUserName;
    @InjectView(R.id.mygridview)
    MyGridView mygridview;

    private String[] personalList = new String[]{"我的团队", "我的钱包", "法律顾问", "账号安全", "个人信息"};
    private int[] ivList = new int[]{R.mipmap.iv_personal, R.mipmap.iv_wallet,
            R.mipmap.iv_fl, R.mipmap.iv_safe, R.mipmap.iv_information};
    private ArrayList<PublicInfo> pList = new ArrayList<>();
    MyFragmentAdapter homeAdapter;

    HeadPresenter mhHeadPresenter;
    private UploadPictureRespBean mUploadPictureRespBean;
    private Handler mHandler = new Handler();
    private MyRunnable mr = new MyRunnable();

    class MyRunnable implements Runnable {
        public void run() {
            System.out.println("上传照片成功：response666666 = ");
            if (UploadHead.type == 1) {
//                        imgHead.setImageBitmap(bitMap);
            } else {
                //更新个人信息
                mhHeadPresenter.updataHeadData(mUploadPictureRespBean.data.data.get(0));
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        topbar.setTxvTitleName("个人中心");
        topbar.setTxvLeftShow();
        topbar.setRigthViewTypeMode(TopBarLayout.RightViewTypeMode.ADD);
        topbar.setBtnRightDrawable(R.mipmap.setting);
//        topbar.setRightTxvOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //跳转到设置界面
//                showActivity(getActivity(), SettingActivity.class);
//            }
//        });
        mhHeadPresenter = new HeadPresenter(this);
        initData();
    }

    //初始化数据
    public void initData() {
        homeAdapter = new MyFragmentAdapter(getActivity());
        mygridview.setAdapter(homeAdapter);
        //设置登录时候的头像
        if (SharedPreferencesUtils.getString(getActivity(), "headimg", "") != null ||
                !SharedPreferencesUtils.getString(getActivity(), "headimg", "").equals("")) {
            Glide.with(getActivity()).load(SharedPreferencesUtils.getString(getActivity(), "headimg", "")).error(R.mipmap.iv_head).into(imgHead);
        }

        for (int i = 0; i < personalList.length; i++) {
            PublicInfo pi = new PublicInfo(personalList[i], ivList[i]);
            pList.add(pi);
        }
        //刷新适配
        homeAdapter.setData(pList);
        //gv点击
        setGridviewOnClick();
    }

    //gv点击
    private void setGridviewOnClick() {
        mygridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                PublicInfo pi = (PublicInfo) homeAdapter.getItem(i);
//                if (pi.getName().equals("我的团队")) {
//                    showActivity(getActivity(), MyTeamActivity.class);
//                } else if (pi.getName().equals("我的钱包")) {
//                    showActivity(getActivity(), MyWalletActivity.class);
//                } else if (pi.getName().equals("法律顾问")) {
//                    showActivity(getActivity(), CounselActivity.class);
//                } else if (pi.getName().equals("账号安全")) {
//                    showActivity(getActivity(), AccountSafetyActivity.class);
//                } else if (pi.getName().equals("个人信息")) {
//                    showActivity(getActivity(), PersonalInformationActivity.class);
//                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(Tag, "onResume");
        //设置名字
        if (SharedPreferencesUtils.getString(getActivity(), "myname", "") != null ||
                !SharedPreferencesUtils.getString(getActivity(), "myname", "").equals("")) {
            tvUserName.setText(SharedPreferencesUtils.getString(getActivity(), "myname", ""));
        }
    }

    @OnClick({R.id.img_head, R.id.tv_user_name})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_head:
                UploadHead.type = 2;
                uploadHeadImage();
                break;
            case R.id.tv_user_name:
//                showActivity(getActivity(), UpdateUserNameActivity.class);
                break;
        }
    }

    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(getActivity()).inflate(R.layout.act_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.5f;
        getActivity().getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getActivity().getWindow().setAttributes(params);
            }
        });

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    gotoCamera();
                }
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    gotoPhoto();
                }
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCamera();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            }
        }
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(UploadHead.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(getActivity(), ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    Log.d("evan", "**********camera uri*******" + Uri.fromFile(tempFile).toString());
                    Log.d("evan", "**********camera path*******" + UploadHead.getRealFilePathFromUri(getActivity(), Uri.fromFile(tempFile)));
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    Log.d("evan", "**********pick path*******" + UploadHead.getRealFilePathFromUri(getActivity(), uri));
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    Log.i("INFO", "上传图片的路径" + uri);
                    Log.i("INFO", "sdk" + Environment.getExternalStorageDirectory());
                    String cropImagePath = UploadHead.getRealFilePathFromUri(getActivity(), uri);
                    Log.i("INFO", "上传图片的路径2222" + cropImagePath);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    File file = new File(cropImagePath);
                    Log.i("INFO", "上传图片的路径5555" + file);
                    //                    Uri uri1=Uri.fromFile(new File(cropImagePath));

                    //此处后面可以将bitMap转为二进制上传后台网络
                    //根据路径上传图片
                    mhHeadPresenter.upLoadHead(cropImagePath);
                }
                break;
        }
    }

    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        UploadHead.type = 2;
        Intent intent = new Intent();
        intent.setClass(getActivity(), ActClipImage.class);
        intent.putExtra("type", UploadHead.type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    //上传头像
    @Override
    public void uploadHeadImage(UploadPictureRespBean data) {
        mUploadPictureRespBean = data;
        mHandler.postDelayed(mr, 1000);
    }

    //更新头像
    @Override
    public void updateHeadImage(RespnoseResultBean data) {
        Glide.with(getActivity()).load(mUploadPictureRespBean.data.data.get(0)).error(R.mipmap.iv_head).into(imgHead);
    }
}
