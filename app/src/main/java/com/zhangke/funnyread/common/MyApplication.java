package com.zhangke.funnyread.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by ZhangKe at 2016/12/9
 */
public class MyApplication extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContextObject(){
        return context;
    }
}
