package com.yayayouji.main;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yayayouji.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Chanllege Mission
 * Created by oceancx on 16/3/6.
 */
public class HomePageCardDetail extends AppCompatActivity {

    @BindView(R.id.question_body)
    TextView mQuestionBody;

    @BindView(R.id.follow)
    Button mFollow;

    @BindView(R.id.card_part2)
    ViewGroup mCardPart2;


    CircleImageView mFab;

    Scene mScene1, mScene2;

    @BindView(R.id.scene_root)
    ViewGroup mSceneRoot;

    @BindView(R.id.scene_card)
    ViewGroup mSceneCard;

    Transition mChangeBoundsTransition;
    boolean toggle = false;

    int right, top, bottom, left;

    SpannableString genSpan(String origin_str) {
        SpannableString sstr;
        sstr = new SpannableString(origin_str);
        sstr.setSpan(new URLSpan("") {
            @Override
            public void onClick(View widget) {
                onTopButtonClick();
            }
        }, origin_str.length() - 4, origin_str.length(), Spanned.SPAN_MARK_MARK);
        return sstr;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_card_detail_aty);
        ButterKnife.bind(this);

        mFab = (CircleImageView) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTopButtonClick();
            }
        });


        mChangeBoundsTransition = new ChangeBounds();
        mQuestionBody.setText(genSpan(getString(R.string.zhihu_body1)));
        mQuestionBody.setMovementMethod(LinkMovementMethod.getInstance());

        left = mQuestionBody.getPaddingLeft();
        right = mQuestionBody.getPaddingRight();
        top = mQuestionBody.getPaddingTop();
        bottom = mQuestionBody.getPaddingBottom();


        mChangeBoundsTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                mQuestionBody.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                mQuestionBody.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onTopButtonClick() {
        if (toggle) {
            mFab.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSceneCard.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, R.id.zhihu_title);

            mSceneCard.setLayoutParams(params);
            mCardPart2.removeView(mQuestionBody);
            mQuestionBody.setTextAppearance(this, android.R.style.TextAppearance_Material_Body1);
            mQuestionBody.setText(genSpan(getString(R.string.zhihu_body1)));

            mQuestionBody.setPadding(left, top, right, bottom);

            TransitionManager.beginDelayedTransition(mSceneRoot, mChangeBoundsTransition);
            mCardPart2.addView(mQuestionBody);
        } else {
            mFab.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSceneCard.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, R.id.toolbar);
            mSceneCard.setLayoutParams(params);
            mCardPart2.removeView(mQuestionBody);

            mQuestionBody.setTextAppearance(this, android.R.style.TextAppearance_Material_Title);
            mQuestionBody.setText(R.string.qm_content);
            mQuestionBody.setPadding(left, top + 16 * 3, right, bottom + 16 * 3);
            int left = mQuestionBody.getPaddingLeft();
            int right = mQuestionBody.getPaddingRight();
            int top = mQuestionBody.getPaddingTop();
            int bottom = mQuestionBody.getPaddingBottom();
            mQuestionBody.setPadding(left, top, right, bottom);

            TransitionManager.beginDelayedTransition(mSceneRoot, mChangeBoundsTransition);
            mCardPart2.addView(mQuestionBody);
        }
        toggle = !toggle;
    }


}
