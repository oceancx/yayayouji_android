package com.yayayouji.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.yayayouji.util.DebugLog;

/**
 * Created by oceancx on 16/3/10.
 */
public class QMViewBH extends CoordinatorLayout.Behavior<RecyclerView> {

    private int mDependencyOffset;

    public QMViewBH() {
    }

    //Required to attach behavior via XML
    public QMViewBH(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, RecyclerView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        DebugLog.e("onMeasureChild");
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
        DebugLog.e("onLayoutChild");
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, RecyclerView child, View dependency) {
        DebugLog.e("onDependentViewRemoved");
        super.onDependentViewRemoved(parent, child, dependency);

    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        DebugLog.e("onDependentViewChanged");
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
