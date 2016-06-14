package com.yayayouji.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yayayouji.R;
import com.yayayouji.base.BaseFragment;

/**
 * Created by oceancx on 16/3/23.
 */
public class YaYaLetterFg extends BaseFragment {

    RecyclerView yaya_music_fg_rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yaya_letter_fg, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
