package com.yayayouji.main;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yayayouji.R;
import com.yayayouji.util.DebugLog;

/**
 * Created by oceancx on 16/7/16.
 */
public class LayerTopBehavior extends CoordinatorLayout.Behavior<View> {

    private static final int DIRECTION_UP = 1;
    private static final int DIRECTION_DOWN = -1;
    private int mScrollingDirection;

    TextView mZhihuTitle;
    View mBottomLayer;
    Toolbar mToolbar;
    private RecyclerView mDenpendencyView;


    private int mInitialOffset;
    private int mInitialBottomOffset;

    private ScrollerCompat mScroller;
    private FlingRunnable mFlingRunnable;
    private ObjectAnimator mAnimator;
    private boolean firstLayout = true;

    private int toolbar_h;
    private int mMinOffset, mMaxOffset;


    public LayerTopBehavior() {
    }

    public LayerTopBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if (dependency instanceof RecyclerView) {
            mDenpendencyView = (RecyclerView) dependency;
        }
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        if (firstLayout) {
            mZhihuTitle = (TextView) parent.findViewById(R.id.zhihu_title);
            mInitialOffset = mZhihuTitle.getBottom();
            mBottomLayer = parent.findViewById(R.id.bottom_layer_bg);
            mInitialBottomOffset = mBottomLayer.getTop();
            mToolbar = (Toolbar) parent.findViewById(R.id.toolbar);
            toolbar_h = mToolbar.getMeasuredHeight();
            firstLayout = false;

        }
        return false;
    }


    private void toggleToolbar(boolean isShow) {
        mToolbar.findViewById(R.id.toolbar_title).setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
        mToolbar.setBackgroundColor(isShow ? mToolbar.getContext().getResources().getColor(R.color.colorPrimary) : 0x00ffffff);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setElevation(isShow ? 4 * 3 : 0);
        }
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {

        if (dy > 0 && mScrollingDirection != DIRECTION_UP) {
            mScrollingDirection = DIRECTION_UP;
        } else if (dy < 0 && mScrollingDirection != DIRECTION_DOWN) {
            mScrollingDirection = DIRECTION_DOWN;
        }

        int top = child.getTop();
        toggleToolbar(top);
        zhihuTitleSetAlpha(top);

//        scroll(child, dy,
//                toolbar_h - child.getHeight(),
//                mInitialOffset);

        if (dy > 0) {
            scroll(child, dy,
                    toolbar_h - child.getHeight(),
                    mInitialOffset);
//            scroll(mBottomLayer, (int) (dy / 3.0 * 2), toolbar_h - mBottomLayer.getHeight(), mInitialBottomOffset);
        } else {
            RecyclerView.ViewHolder vh = mDenpendencyView.findViewHolderForAdapterPosition(1);
            if (vh != null && vh.itemView != null) {
                int dtop = vh.itemView.getTop();
                if (dtop + 180 >= toolbar_h) {
                    scroll(child, dy,
                            toolbar_h - child.getHeight(),
                            mInitialOffset);
//                    scroll(mBottomLayer, (int) (dy / 3.0 * 2), toolbar_h - mBottomLayer.getHeight(), mInitialBottomOffset);
                }
            }
        }
    }


    private void zhihuTitleSetAlpha(int top) {
        int totaloff = mInitialOffset - toolbar_h;
        float toff = clamp(mInitialOffset - top, 0, totaloff);
        mZhihuTitle.setAlpha(1 - toff / totaloff);

    }

    private void toggleToolbar(int top) {
        toggleToolbar(top <= toolbar_h);
    }

    //Scroll the view and return back the actual distance scrolled
    private int scroll(View child, int dy, int minOffset, int maxOffset) {
        final int initialOffset = child.getTop();
        //Clamped new position - initial position = offset change
        int delta = clamp(initialOffset - dy, minOffset, maxOffset) - initialOffset;
        child.offsetTopAndBottom(delta);

        return -delta;
    }


    private int clamp(int value, int min, int max) {
        if (value > max) {
            return max;
        } else if (value < min) {
            return min;
        } else {
            return value;
        }
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

//        scroll(child, dyUnconsumed,
//                toolbar_h - child.getHeight(),
//                mInitialOffset);

        int top = child.getTop();
        toggleToolbar(top);
        zhihuTitleSetAlpha(top);
        if (mFlingRunnable != null) {
            child.removeCallbacks(mFlingRunnable);
        }
        if (dyUnconsumed > 0) {
            scroll(child, dyUnconsumed,
                    toolbar_h - child.getHeight(),
                    mInitialOffset);
            scroll(mBottomLayer, (int) (dyUnconsumed / 3.0 * 2), toolbar_h - mBottomLayer.getHeight(), mInitialBottomOffset);
        } else {
            RecyclerView.ViewHolder vh = mDenpendencyView.findViewHolderForAdapterPosition(1);
            if (vh != null && vh.itemView != null) {
                int dtop = vh.itemView.getTop();
                if (dtop + 180 >= toolbar_h) {
                    scroll(child, dyUnconsumed,
                            toolbar_h - child.getHeight(),
                            mInitialOffset);
                    scroll(mBottomLayer, (int) (dyUnconsumed / 3.0 * 2), toolbar_h - mBottomLayer.getHeight(), mInitialBottomOffset);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        return super.onTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {


//        fling(coordinatorLayout,
//                child,
//                toolbar_h - child.getHeight(),
//                mInitialOffset,
//                -velocityY);
        return false;
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        return fling(coordinatorLayout,
                child,
                toolbar_h - child.getHeight(),
                mInitialOffset,
                -velocityY);

    }


    //Attempt to fling and return if successfully started
    private boolean fling(CoordinatorLayout parent, View layout,
                          int minOffset, int maxOffset, float velocityY) {
        if (mFlingRunnable != null) {
            layout.removeCallbacks(mFlingRunnable);
        }

        if (mScroller == null) {
            mScroller = ScrollerCompat.create(layout.getContext());
        }

        mScroller.fling(
                0, layout.getTop(), // curr
                0, Math.round(velocityY), // velocity.
                0, 0, // x
                minOffset, maxOffset); // y

        if (mScroller.computeScrollOffset()) {
//            toggleToolbar(layout.getTop());
            mFlingRunnable = new FlingRunnable(parent, layout);
            ViewCompat.postOnAnimation(layout, mFlingRunnable);
            return true;
        } else {
//            toggleToolbar(layout.getTop());
            mFlingRunnable = null;
            return false;
        }
    }


    //Helper to trigger hide/show animation
    private void restartAnimator(View target, float value) {
        if (mAnimator != null) {
            mAnimator.cancel();
            mAnimator = null;
        }

        mAnimator = ObjectAnimator
                .ofFloat(target, View.TRANSLATION_Y, value)
                .setDuration(250);
        mAnimator.start();
    }

    private float getTargetHideValue(ViewGroup parent, View target) {
        if (target instanceof AppBarLayout) {
            return -target.getHeight();
        } else if (target instanceof FloatingActionButton) {
            return parent.getHeight() - target.getTop();
        }

        return -target.getHeight();
    }

    private class FlingRunnable implements Runnable {
        private final CoordinatorLayout mParent;
        private final View mLayout;

        FlingRunnable(CoordinatorLayout parent, View layout) {
            mParent = parent;
            mLayout = layout;
        }

        @Override
        public void run() {
            if (mLayout != null && mScroller != null && mScroller.computeScrollOffset()) {
                int delta = mScroller.getCurrY() - mLayout.getTop();
                mLayout.offsetTopAndBottom(delta);

                // Post ourselves so that we run on the next animation
                ViewCompat.postOnAnimation(mLayout, this);
            }
        }
    }
}
