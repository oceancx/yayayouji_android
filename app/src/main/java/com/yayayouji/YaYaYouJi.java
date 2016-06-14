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

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


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

        ShareSDK.initSDK(this);

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
                showShare();
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

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题：微信、QQ（新浪微博不需要标题）
        oks.setTitle("我是分享标题");  //最多30个字符

        // text是分享文本：所有平台都需要这个字段
        oks.setText("我是分享文本，啦啦啦~http://uestcbmi.com/");  //最多40个字符

        // imagePath是图片的本地路径：除Linked-In以外的平台都支持此参数
        //oks.setImagePath(Environment.getExternalStorageDirectory() + "/meinv.jpg");//确保SDcard下面存在此张图片

        //网络图片的url：所有平台
        oks.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul

        // url：仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情

        // Url：仅在QQ空间使用
        oks.setTitleUrl("http://www.baidu.com");  //网友点进链接后，可以看到分享的详情

        // 启动分享GUI
        oks.show(this);
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

