package com.yayayouji.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by oceancx on 16/3/22.
 */
public abstract class RecyclerViewHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_HEADER = 1;
    private final int TYPE_NORMAL = 2;

    private View header_view;


    public void addHeader(View header_view) {

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else
            return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            onBindHeaderViewHolder(holder, position);
        } else {
            onBindNormalViewHolder(holder, position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TYPE_HEADER == viewType) {
            return onCreateHeaderViewHolder(parent, viewType);
        } else {
            return onCreateNormalViewHolder(parent, viewType);
        }
    }


    public RecyclerView.ViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    public void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    public int getHeaderItemCount() {
        return 0;
    }


    public int getNormalItemCount() {
        return 0;
    }
}
