package com.IMRUIGOD.weatherdemo;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
