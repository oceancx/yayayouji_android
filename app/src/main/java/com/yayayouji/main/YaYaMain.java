package com.yayayouji.main;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.yayayouji.R;
import com.yayayouji.adapter.ImagePagerAdapter;
import com.yayayouji.base.BaseActivity;
import com.yayayouji.module.english.YaYaEnglishFg;
import com.yayayouji.module.music.YaYaMusicFg;
import com.yayayouji.module.travelnote.fragment.YaYaTravelNoteFg;
import com.yayayouji.ui.DepthPageTransformer;
import com.yayayouji.util.DummyViewHolder;

import java.util.ArrayList;

/**
 * 丫丫首页
 * <p/>
 * 各个模块都是Activity
 * Created by oceancx on 16/3/20.
 */
public class YaYaMain extends BaseActivity {


    ActionBarDrawerToggle mDrawerToggle;
    Toolbar ym_toolbar;

    RecyclerView yaya_main_rv;
    boolean once = false;
    ViewPager ym_view_pager;


    TabLayout ym_tablayout;
    String[] titles = {""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yaya_main_aty);

        ym_toolbar = (Toolbar) findViewById(R.id.ym_toolbar);
        initTitle(ym_toolbar);

        setUpDrawerToggle();

//        yaya_main_rv = (RecyclerView) findViewById(R.id.yaya_main_rv);
//        YaYaMainRecyclerAdapter adapter = new YaYaMainRecyclerAdapter();
//        yaya_main_rv.setAdapter(adapter);
//        yaya_main_rv.setLayoutManager(new LinearLayoutManager(this));


        ym_view_pager = (ViewPager) findViewById(R.id.ym_viewpager);

        YMPagerAdapter pagerAdapter = new YMPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new YaYaTravelNoteFg(), "游记");
        pagerAdapter.addFragment(new YaYaMusicFg(), "音乐");
        pagerAdapter.addFragment(new YaYaEnglishFg(), "英语");
        //pagerAdapter.addFragment(new YaYaLetterFg(), "信");
        ym_view_pager.setAdapter(pagerAdapter);
        ym_tablayout = (TabLayout) findViewById(R.id.ym_tablayout);
        ym_tablayout.setupWithViewPager(ym_view_pager);



    }

    private void setUpDrawerToggle() {
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.ym_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, ym_toolbar,
                R.string.travelnote,
                R.string.travelnote) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initTitle(Toolbar toolbar) {
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayShowHomeEnabled(true);
        ab.setHomeButtonEnabled(true);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ym_menu, menu);
        return true;
    }

    /**
     * 主页Adapter
     * 各个模块的概览Fragment
     */
    static class YMPagerAdapter extends FragmentPagerAdapter {
        final ArrayList<Fragment> fragments = new ArrayList<>();
        final ArrayList<String> fgTitles = new ArrayList<>();

        public YMPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fg, String title) {
            fragments.add(fg);
            fgTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fgTitles.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    private class YaYaMainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        Context context;
        private int TYPE_HEADER = 1;

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return TYPE_HEADER;
            else
                return super.getItemViewType(position);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            context = parent.getContext();
            View view;
            if (viewType == TYPE_HEADER) {
                view = LayoutInflater.from(context).inflate(R.layout.yaya_main_header_item, parent, false);
                return new DummyViewHolder(view) {
                    @Override
                    public void doSomething() {
                        ViewPager pager = (ViewPager) itemView.findViewById(R.id.header_pager);
                        pager.setPageTransformer(true, new DepthPageTransformer());
                        pager.setAdapter(new ImagePagerAdapter(getSupportFragmentManager()));
                    }
                };
            } else {
                view = LayoutInflater.from(context).inflate(R.layout.yaya_main_item, parent, false);
            }
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//             holder.image
            if (getItemViewType(position) == TYPE_HEADER) {

            } else {
                VH vh = (VH) holder;
                //  Glide.with(context).load(imgUrls[position]).into(vh.image);
            }
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
