package com.yayayouji.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by oceancx on 16/7/9.
 */
public class ScreenUtils {
    public static DisplayMetrics dm;

    public static void init(Context context) {
        dm = context.getResources().getDisplayMetrics();
        DebugLog.e("dm:" + dm.toString());
    }
}
