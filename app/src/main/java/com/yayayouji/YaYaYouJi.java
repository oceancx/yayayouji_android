package com.yayayouji;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yayayouji.net.YYHttpClient;
import com.yayayouji.util.DebugLog;

import java.io.IOException;
import java.net.URLEncoder;


/**
 * Created by oceancx on 15/10/24.
 * <p/>
 * 此页面摘取 游记 读书 音乐 电影 各种内容 来展示给用户 也叫用户首页
 * <p/>
 * 其他页面可由navView来进入
 */
public class YaYaYouJi extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    Button post_bt, get_bt;
    TextView content_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.yayayouji);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        post_bt = (Button) findViewById(R.id.post_bt);
        get_bt = (Button) findViewById(R.id.get_bt);
        content_tv = (TextView) findViewById(R.id.result_tv);
        get_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetTask t = new NetTask();
                t.execute("http://192.168.56.15/index.php");
            }
        });

        post_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostTask task = new PostTask();
                String json = "{\"name\":3123,\"captain\":true,\"gender\":\"F\"}";
                task.execute("http://192.168.56.15/register0.php", json);
            }
        });

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    private class NetTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                return YYHttpClient.get(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            DebugLog.e(s);
            content_tv.setText(s);
        }
    }

    private class PostTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                DebugLog.e("param[0]:" + params[0] + " params[1]:" + params[1]);

                return YYHttpClient.post(params[0], URLEncoder.encode("name=3123&captain=on&gender=F", "utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            DebugLog.e(s);
            content_tv.setText(s);
        }
    }

}

