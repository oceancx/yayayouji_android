package com.yayayouji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yayayouji.R;
import com.yayayouji.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oceancx on 16/7/9.
 */
public class MainPageFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page_fragment, container, false);
        ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mToolbar.setTitle(getClass().getSimpleName());


    }
}
