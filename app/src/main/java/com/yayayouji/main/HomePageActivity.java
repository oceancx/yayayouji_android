package com.yayayouji.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yayayouji.R;
import com.yayayouji.base.BaseActivity;

/**
 * Created by oceancx on 16/3/6.
 */
public class HomePageActivity extends BaseActivity {


    RecyclerView hp_rv;
    HomePageAdapter mAdapter;
    Toolbar hp_toolbar;

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_aty);

        initTitle();

        hp_rv = (RecyclerView) findViewById(R.id.home_page_rv);
        mAdapter = new HomePageAdapter();
        hp_rv.setLayoutManager(new LinearLayoutManager(this));
        hp_rv.setAdapter(mAdapter);
    }

    private void initTitle() {
        hp_toolbar = (Toolbar) findViewById(R.id.hp_toolbar);
        setSupportActionBar(hp_toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.hp_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                hp_toolbar,
                0,
                0) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    //设置顶部图标
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private class VH extends RecyclerView.ViewHolder {


        public VH(View itemView) {
            super(itemView);
        }
    }

    private class HomePageAdapter extends RecyclerView.Adapter<VH> {
        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_page_card_item, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    /**
                     * 进入问题详情页
                     */
                    Intent intent = new Intent(HomePageActivity.this, HomePageCardDetail.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }
}
