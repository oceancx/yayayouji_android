package com.yayayouji.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yayayouji.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    View mCardPart2;


    @BindView(R.id.fab)
    FloatingActionButton mFab;

    SpannableString sstr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_card_detail_aty);
        ButterKnife.bind(this);


        String origin_str = getString(R.string.zhihu_body1);
        sstr = new SpannableString(origin_str);
        sstr.setSpan(new URLSpan("") {
            @Override
            public void onClick(View widget) {
                Toast.makeText(HomePageCardDetail.this, "clicked", Toast.LENGTH_SHORT).show();
                mQuestionBody.setText(R.string.zhihu_body2);
                mQuestionBody.setTextAppearance(HomePageCardDetail.this, android.R.style.TextAppearance_Material_Title);
                mFab.setVisibility(View.VISIBLE);
            }
        }, origin_str.length() - 4, origin_str.length(), Spanned.SPAN_MARK_MARK);
        mQuestionBody.setText(sstr);
        mQuestionBody.setMovementMethod(LinkMovementMethod.getInstance());


    }

    @OnClick(R.id.follow)
    public void onFollowClick() {
        mQuestionBody.setText(sstr);
        mFab.setVisibility(View.INVISIBLE);
        mQuestionBody.setTextAppearance(HomePageCardDetail.this, android.R.style.TextAppearance_Material_Body1);
    }
}
