package com.yayayouji.module.english;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.yayayouji.R;
import com.yayayouji.base.BaseFragment;

/**
 * 英语概览页
 * 每个Item是一个小视频
 * 详情页就是 视频+笔记
 * 用 ExpandableListView 炫技 - -
 * 对视频进行分类
 * Created by oceancx on 16/3/20.
 */
public class YaYaEnglishFg extends BaseFragment {

    //    RecyclerView yaya_english_fg_rv;
    ExpandableListView yaya_english_fg_eplv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yaya_english_fg, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        yaya_english_fg_rv = (RecyclerView) findViewById(R.id.yaya_english_fg_rv);
        yaya_english_fg_eplv = (ExpandableListView) findViewById(R.id.yaya_english_fg_eplv);
        YaYaEnglishFgAdapter adapter = new YaYaEnglishFgAdapter();
        yaya_english_fg_eplv.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            yaya_english_fg_eplv.expandGroup(i);
        }
        yaya_english_fg_eplv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (yaya_english_fg_eplv.isGroupExpanded(groupPosition))
                    yaya_english_fg_eplv.expandGroup(groupPosition);
                return true;
            }
        });
    }

    private class YaYaEnglishFgAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return 10;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 5;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.yaya_english_fg_item_group, parent, false);
            }
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.yaya_english_fg_item_child, parent, false);
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoEnglishDetailActivity();
                }
            });
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

    private void gotoEnglishDetailActivity() {
        Intent intent =new Intent(getActivity(),YaYaEnglishDetail.class);
        startActivity(intent);
    }


}

