package com.yayayouji.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.yayayouji.R;
import com.yayayouji.main.YaYaMain;
import com.yayayouji.module.english.YaYaEnglish;
import com.yayayouji.module.music.YaYaMusic;
import com.yayayouji.module.travelnote.YaYaTravelNote;
import com.yayayouji.util.DebugLog;

/**
 * 处理drawer
 * Created by oceancx on 15/10/24.
 */
public class BaseActivity extends AppCompatActivity {


    static int sel_menu_index = 0;
    DrawerLayout mDrawerLayout;
    int[] nav_menu_ids = {R.id.ym_nav_menu_main_page, R.id.ym_nav_menu_travel_note, R.id.ym_nav_menu_music, R.id.ym_nav_menu_english_learning};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.ym_drawer);

        NavigationView navigationView = (NavigationView) findViewById(R.id.yaya_main_nav);

        if (navigationView != null) {
            MenuItem menu = navigationView.getMenu().getItem(sel_menu_index);
            menu.setChecked(true);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    int id = item.getItemId();

                    mDrawerLayout.closeDrawers();
                    for (int i = 0; i < nav_menu_ids.length; i++) {
                        if (id == nav_menu_ids[i]) {
                            sel_menu_index=i;
                            DebugLog.e("id : " + id + " i :" + i);
                            startModelActivity(i);
                            // replaceFragment(i);
                            break;
                        }
                    }
                    return true;
                }
            });

        }
    }

    private void startModelActivity(int index) {
        Intent intent = new Intent();
        switch (index) {
            case 0: {
                intent.setClass(this, YaYaMain.class);
            }
            break;
            case 1: {
                intent.setClass(this, YaYaTravelNote.class);
            }
            break;
            case 2: {
                intent.setClass(this, YaYaMusic.class);
            }
            break;
            case 3: {
                intent.setClass(this, YaYaEnglish.class);
            }
            break;
        }
        startActivity(intent);
        finish();
    }


}
