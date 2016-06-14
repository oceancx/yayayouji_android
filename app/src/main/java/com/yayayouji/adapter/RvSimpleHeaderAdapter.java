package com.yayayouji.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yayayouji.util.DummyViewHolder;

/**
 * 很简单的 只有一个HEADER的Adapter
 * Created by oceancx on 16/3/22.
 */
public abstract class RvSimpleHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = -1;
    private static final int TYPE_NORMAL = 0;
    View header;

    public RvSimpleHeaderAdapter() {

    }

    public void addHeader(View header) {
        this.header = header;
        notifyDataSetChanged();

    }

    public View getHeader() {
        return header;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && header != null)
            return TYPE_HEADER;
        else if (header != null)
            return getNormalItemViewType(position - 1);
        else
            return super.getItemViewType(position);
    }

    public int getNormalItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return onCreateHeaderViewHolder(parent, viewType);
        } else {
            return onCreateNormalViewHolder(parent, viewType);
        }
    }

    /**
     * 可以重写 也可不重写
     *
     * @param parent
     * @param viewType
     * @return
     */
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return new DummyViewHolder(header);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            onBindHeaderViewHolder(holder, position);
        } else {
            onBindNormalViewHolder(holder, position - 1);
        }
    }


    /**
     * 可以重写 也可以不重写
     *
     * @param holder
     * @param position
     */
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (header == null ? 0 : 1) + getNormalItemCount();
    }

    public abstract void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position);

    public abstract int getNormalItemCount();

    public abstract RecyclerView.ViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType);
}
