package com.yayayouji.behavior;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Created by oceancx on 2016/7/22.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class ViewUtilsLollipop {

    private static final int[] STATE_LIST_ANIM_ATTRS = new int[]{android.R.attr.stateListAnimator};

    static void setBoundsViewOutlineProvider(View view) {
        view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

    static void setStateListAnimatorFromAttrs(View view, AttributeSet attrs,
                                              int defStyleAttr, int defStyleRes) {
        final Context context = view.getContext();
        final TypedArray a = context.obtainStyledAttributes(attrs, STATE_LIST_ANIM_ATTRS,
                defStyleAttr, defStyleRes);
        try {
            if (a.hasValue(0)) {
                StateListAnimator sla = AnimatorInflater.loadStateListAnimator(context,
                        a.getResourceId(0, 0));
                view.setStateListAnimator(sla);
            }
        } finally {
            a.recycle();
        }
    }

    /**
     * Creates and sets a {@link StateListAnimator} with a custom elevation value
     */
    static void setDefaultAppBarLayoutStateListAnimator(final View view,
                                                        final float targetElevation) {
        final StateListAnimator sla = new StateListAnimator();

        // Enabled, collapsible and collapsed == elevated
        sla.addState(new int[]{android.R.attr.enabled, android.support.design.R.attr.state_collapsible,
                        android.support.design.R.attr.state_collapsed},
                ObjectAnimator.ofFloat(view, "elevation", targetElevation));

        // Enabled and collapsible, but not collapsed != elevated
        sla.addState(new int[]{android.R.attr.enabled, android.support.design.R.attr.state_collapsible,
                        -android.support.design.R.attr.state_collapsed},
                ObjectAnimator.ofFloat(view, "elevation", 0f));

        // Enabled but not collapsible == elevated
        sla.addState(new int[]{android.R.attr.enabled, -android.support.design.R.attr.state_collapsible},
                ObjectAnimator.ofFloat(view, "elevation", targetElevation));

        // Default, none elevated state
        sla.addState(new int[0], ObjectAnimator.ofFloat(view, "elevation", 0));

        view.setStateListAnimator(sla);
    }

}
