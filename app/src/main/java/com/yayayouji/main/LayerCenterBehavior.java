package com.yayayouji.main;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.yayayouji.util.DebugLog;

/**
 * Created by oceancx on 16/7/16.
 */
public class LayerCenterBehavior extends CoordinatorLayout.Behavior<View> {
    public LayerCenterBehavior() {
    }

    public LayerCenterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        DebugLog.e("card view");
        return false;
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
//        View dependency = parent.findViewById(R.id.scene_card);
//        DebugLog.e("b:" + dependency.getBottom());
//        child.offsetTopAndBottom(dependency.getBottom());
//        int top = child.getTop() + dependency.getBottom();
//        int left = child.getLeft();
//        int right = left + child.getMeasuredWidth();
//        int bottom = top + child.getMeasuredHeight();
//        DebugLog.e("l:" + left + " t:" + top + " r:" + right + " b:" + bottom);
//        child.layout(child.getLeft(), dependency.getBottom(), child.getLeft() + child.getMeasuredWidth(), dependency.getBottom() + child.getMeasuredHeight());
        return false;
    }
}
