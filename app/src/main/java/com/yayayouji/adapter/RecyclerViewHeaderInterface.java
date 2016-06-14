package com.yayayouji.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by oceancx on 16/3/22.
 */
public interface RecyclerViewHeaderInterface {

    public RecyclerView.ViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType);


    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);


    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position);


    public void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position);


    public int getHeaderItemCount();


    public int getNormalItemCount();
}


