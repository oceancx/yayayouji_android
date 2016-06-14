package com.yayayouji.module.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yayayouji.R;
import com.yayayouji.base.BaseFragment;

/**
 * 展示成GoogleMusic的样子
 * 网格布局
 * Created by oceancx on 16/3/20.
 */
public class YaYaMusicFg extends BaseFragment {

    RecyclerView yaya_music_fg_rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yaya_music_fg, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        yaya_music_fg_rv = (RecyclerView) findViewById(R.id.yaya_music_fg_rv);
        yaya_music_fg_rv.setAdapter(new YaYaMusicFgAdapter());
        yaya_music_fg_rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private class YaYaMusicFgAdapter extends RecyclerView.Adapter<VH> {

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yaya_music_fg_item, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoMusicDetailActivity();
                }
            });
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    private void gotoMusicDetailActivity() {
        Intent intent = new Intent(getActivity(),YaYaMusicDetail.class);
        startActivity(intent);
    }

    private class VH extends RecyclerView.ViewHolder {

        public VH(View itemView) {
            super(itemView);
        }
    }
}
