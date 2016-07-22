package com.yayayouji.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yayayouji.R;
import com.yayayouji.behavior.AppBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oceancx on 2016/7/22.
 */
public class AppBarLayoutActivity extends AppCompatActivity {
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.expand)
    Button mExpand;
    boolean toggle = false;
    @BindView(R.id.recyclerView)
    RecyclerView rv;
    int mItemCount = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appbar_activity);
        ButterKnife.bind(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new AnswerAdapter());
    }

    @OnClick(R.id.expand)
    public void onClickExpand() {
        mAppbar.setExpanded(toggle = !toggle, true);
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
