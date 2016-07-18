package com.yayayouji.main;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by oceancx on 16/7/16.
 */
@CoordinatorLayout.DefaultBehavior(LayerTopBehavior.class)
public class TopLayout extends FrameLayout {
    public TopLayout(Context context) {
        super(context);
    }

    public TopLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TopLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
