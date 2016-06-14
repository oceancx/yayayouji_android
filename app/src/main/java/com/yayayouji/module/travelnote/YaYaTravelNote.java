package com.yayayouji.module.travelnote;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yayayouji.R;
import com.yayayouji.base.BaseActivity;

/**
 *
 * Created by oceancx on 16/3/21.
 */
public class YaYaTravelNote extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yaya_travel_note_aty);

    }


    private class TravelNoteAdapter extends RecyclerView.Adapter<VH> {
        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yaya_travel_note_card_item, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }


    private class VH extends RecyclerView.ViewHolder {
        public VH(View itemView) {
            super(itemView);
        }
    }
}
