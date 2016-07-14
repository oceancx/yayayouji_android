package com.yayayouji.main;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yayayouji.R;
import com.yayayouji.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oceancx on 16/7/10.
 */
public class HomePageCardDetail2 extends BaseActivity {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.zhihu_title)
    TextView mTitle;

    int mItemCount = 10;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_card_detail2);
        ButterKnife.bind(this);

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
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
                for (int i = 0; i < parent.getChildCount() - 1; i++) {
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
        mRecycler.setAdapter(new AnswerAdapter());

    }

    private class AnswerAdapter extends RecyclerView.Adapter<VH> {

        String[] urls = {
                "https://pic3.zhimg.com/f76a28272b98f91def72bfbdbf4e71c6_m.jpg", "https://pic2.zhimg.com/042dc350abbfeca87112d324ac9f32c9_m.jpg", "https://pic4.zhimg.com/5488b187b78a042e89c1a08c9d732f37_m.png", "https://pic2.zhimg.com/6e611a1a2ca4eb96e59cf52b745a9115_m.jpg"
        };

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            Glide.with(holder.imageView.getContext()).load(urls[position % urls.length]).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }
    }

    private class VH extends RecyclerView.ViewHolder {
        ImageView imageView;

        public VH(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.face);
        }
    }
}
