package com.yayayouji.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by oceancx on 16/3/20.
 */
public class BaseFragment extends Fragment {


    Activity aty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        aty = getActivity();
    }

    public View findViewById(int id) {
        return aty.findViewById(id);
    }

    @Override
    public Context getContext() {
        return aty;
    }
}
