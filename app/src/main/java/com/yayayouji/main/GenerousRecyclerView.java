package com.yayayouji.main;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by oceancx on 2016/7/19.
 */
public class GenerousRecyclerView extends RecyclerView {

    public int mScrollY = 0;
    private boolean mEating = false;


    public GenerousRecyclerView(Context context) {
        this(context, null);
    }


    public GenerousRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return true;
    }
}