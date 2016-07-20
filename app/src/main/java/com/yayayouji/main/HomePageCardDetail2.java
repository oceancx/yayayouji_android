package com.yayayouji.main;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yayayouji.R;
import com.yayayouji.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by oceancx on 16/7/10.
 */
public class HomePageCardDetail2 extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.card_part2)
    ViewGroup mCardPart2;

    CircleImageView mFab;

    @BindView(R.id.question_body)
    TextView mQuestionBody;

    @BindView(R.id.scene_root)
    ViewGroup mSceneRoot;

    @BindView(R.id.scene_card)
    ViewGroup mSceneCard;

    @BindView(R.id.bottom_layer)
    FrameLayout mBottomLayer;

    Transition mChangeBoundsTransition;
    boolean toggle = false;

    int right, top, bottom, left;


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.zhihu_title)
    TextView mZhihuTitle;


    View part1, part2, part3;

    int mItemCount = 10;

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

    @OnClick(R.id.zhihu_title)
    public void onTitleClick() {
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setRefreshing(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSwipeRefreshLayout.setRefreshing(false);
                            mItemCount += 10;
                            mRecycler.getAdapter().notifyDataSetChanged();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_card_detail2);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitle("");
        part1 = mBottomLayer;
        part2 = mSceneCard;
        part3 = mRecycler;

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

        LinearLayoutManager lm = new LinearLayoutManager(this);

        mRecycler.setLayoutManager(lm);
        mRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {

            Paint paint;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0, 0, 0, 3);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                final int left = parent.getPaddingLeft() + 24;
                final int right = parent.getWidth() - 24;

                if (paint == null) {
                    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setColor(0xffdcdcdc);
                }
                for (int i = 1; i < parent.getChildCount() - 1; i++) {
                    View child = parent.getChildAt(i);
                    RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) child.getLayoutParams();
                    int top = child.getBottom() + p.bottomMargin;
                    int bottom = top + 3;
                    paint.setColor(0xffdcdcdc);
                    c.drawRect(left, top, right - 120 - 8 * 3 - 16 * 3, bottom, paint);
                    paint.setColor(0xffffffff);
                    c.drawRect(right - 120 - 8 * 3 - 16 * 3, top, right, bottom, paint);
                }
            }

        });
        mRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mRecycler.setAdapter(new AnswerAdapter());
        mSwipeRefreshLayout.setEnabled(false);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        mItemCount += 10;
                        mRecycler.getAdapter().notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }.execute();
            }
        });
        mSceneRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mSceneRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mSceneCard.getLayoutParams();
                params.topMargin = mZhihuTitle.getBottom();
                mSceneCard.setLayoutParams(params);

                FrameLayout ll = (FrameLayout) mRecycler.findViewHolderForAdapterPosition(0).itemView;
                ViewGroup.LayoutParams lp = ll.getLayoutParams();
                lp.height = mZhihuTitle.getBottom() + mSceneCard.getMeasuredHeight() + ll.getChildAt(0).getMeasuredHeight() + 16 * 3;

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.question_main_menu, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onTopButtonClick() {
        if (toggle) {
            mFab.setVisibility(View.INVISIBLE);
//            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mSceneCard.getLayoutParams();
//            params.addRule(RelativeLayout.BELOW, R.id.zhihu_title);
//            mSceneCard.setLayoutParams(params);

            mCardPart2.removeView(mQuestionBody);
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mSceneCard.getLayoutParams();
            params.topMargin = mZhihuTitle.getBottom();
            mSceneCard.setLayoutParams(params);

            mQuestionBody.setTextAppearance(this, android.R.style.TextAppearance_Material_Body1);
            mQuestionBody.setText(genSpan(getString(R.string.zhihu_body1)));

            mQuestionBody.setPadding(left, top, right, bottom);

            TransitionManager.beginDelayedTransition(mSceneRoot, mChangeBoundsTransition);
            mCardPart2.addView(mQuestionBody);
        } else {
            mFab.setVisibility(View.VISIBLE);

//            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSceneCard.getLayoutParams();
//            params.addRule(RelativeLayout.BELOW, R.id.toolbar);
//            mSceneCard.setLayoutParams(params);

//            mSceneCard.setTop(mToolbar.getBottom());

            mCardPart2.removeView(mQuestionBody);
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mSceneCard.getLayoutParams();
            params.topMargin = mToolbar.getBottom();
            mSceneCard.setLayoutParams(params);

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

    private class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        String[] urls = {
                "https://pic3.zhimg.com/f76a28272b98f91def72bfbdbf4e71c6_m.jpg", "https://pic2.zhimg.com/042dc350abbfeca87112d324ac9f32c9_m.jpg", "https://pic4.zhimg.com/5488b187b78a042e89c1a08c9d732f37_m.png", "https://pic2.zhimg.com/6e611a1a2ca4eb96e59cf52b745a9115_m.jpg"
        };
        private int TYPE_HEAD = 0;
        private int TYPE_ITEM = TYPE_HEAD + 1;


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
            if (getItemViewType(position) == TYPE_ITEM) {
                VH holder = (VH) vh;
                Glide.with(holder.imageView.getContext()).load(urls[position % urls.length]).into(holder.imageView);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEAD) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_question_count, parent, false);
                return new HeaderVH(view);
            } else if (viewType == TYPE_ITEM) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item, parent, false);
                return new VH(view);
            }
            return null;
        }


        @Override
        public int getItemCount() {
            return mItemCount;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return TYPE_HEAD;
            else
                return TYPE_ITEM;
        }
    }

    private class VH extends RecyclerView.ViewHolder {
        ImageView imageView;

        public VH(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.face);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private class HeaderVH extends RecyclerView.ViewHolder {

        public HeaderVH(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
