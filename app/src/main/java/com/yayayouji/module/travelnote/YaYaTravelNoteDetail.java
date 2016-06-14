package com.yayayouji.module.travelnote;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yayayouji.R;
import com.yayayouji.adapter.RvSimpleHeaderAdapter;
import com.yayayouji.base.BaseActivity;
import com.yayayouji.util.DummyViewHolder;

/**
 * Created by oceancx on 16/3/22.
 */
public class YaYaTravelNoteDetail extends BaseActivity {
    RecyclerView tn_detail_rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yaya_travel_note_detail_aty);
        tn_detail_rv = (RecyclerView) findViewById(R.id.tn_detail_rv);
        TNDetailAdapter adapter = new TNDetailAdapter();
        tn_detail_rv.setLayoutManager(new LinearLayoutManager(this));
        View view = LayoutInflater.from(this).inflate(R.layout.tn_detail_item_type_header, tn_detail_rv, false);
        adapter.addHeader(view);
        tn_detail_rv.setAdapter(adapter);

    }

    private class TNDetailAdapter extends RvSimpleHeaderAdapter {

        int[] tn_detail_item_layout_res = {
                R.layout.tn_detail_item_type_black_text,
                R.layout.tn_detail_item_type_photo,
                R.layout.tn_detail_item_type_poi_name_below_photo,
                R.layout.tn_detail_item_type_orange_text,
                R.layout.tn_detail_item_type_poi_desc,
        };

        int TYPE_BLACK_TEXT = 1;
        int TYPE_PHOTO = 2;
        int TYPE_POI_NAME = 3;
        int TYPE_ORANGE_TEXT = 4;

        @Override
        public int getNormalItemViewType(int position) {
            if (position % 3 == 0) {
                return TYPE_BLACK_TEXT;
            } else if (position % 5 == 0)
                return TYPE_PHOTO;
            else if (position % 7 == 0)
                return TYPE_ORANGE_TEXT;
            else return TYPE_BLACK_TEXT;
        }

        @Override
        public void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position) {
            int viewType = getNormalItemViewType(position);
            if (viewType == TYPE_BLACK_TEXT) {

            } else if (viewType == TYPE_PHOTO) {

            } else if (viewType == TYPE_ORANGE_TEXT) {

            }
        }

        @Override
        public int getNormalItemCount() {
            return 20;
        }

        @Override
        public RecyclerView.ViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            if (viewType == TYPE_BLACK_TEXT) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tn_detail_item_type_black_text, parent, false);
            } else if (viewType == TYPE_PHOTO) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tn_detail_item_type_photo, parent, false);

            } else if (viewType == TYPE_ORANGE_TEXT) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tn_detail_item_type_orange_text, parent, false);
            }
            return new DummyViewHolder(view);
        }
    }

}
