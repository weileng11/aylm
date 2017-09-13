package cn.wl.aylm;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

import cn.wl.aylm.ui.ActLogin;

/**
 * Author:Bruce
 * Package:cn.wl.aylm
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class AylmApplication extends Application{

    private volatile static AylmApplication baseApplicationContext = null;
    // 存储打开的activity，用于重新启动activity时候用
    public static ArrayList<Activity> activityList = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplicationContext = this;
    }

    public static AylmApplication getInstance() {
        if(baseApplicationContext==null){
            synchronized (AylmApplication.class){
                if(baseApplicationContext==null){
                    baseApplicationContext=new AylmApplication();
                }
            }
        }
        return (AylmApplication) baseApplicationContext;
    }


    /**
     * 销毁全部界面
     */
    public void finishAllActivity() {
        if (activityList != null) {
            for (Activity activity : activityList) {
                if (!activity.isFinishing() && !(activity instanceof ActLogin)) {
                    activity.finish();
                }
            }
            activityList.clear();
        }
    }
}
