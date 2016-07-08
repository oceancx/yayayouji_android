package com.yayayouji;

import android.app.Application;

import com.yayayouji.util.ScreenUtils;

/**
 * Created by oceancx on 16/7/9.
 */
public class YYApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ScreenUtils.init(this);
    }
}
