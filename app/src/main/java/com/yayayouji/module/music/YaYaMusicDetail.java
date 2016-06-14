package com.yayayouji.module.music;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.yayayouji.R;
import com.yayayouji.base.BaseActivity;

/**
 * Created by oceancx on 16/3/23.
 */
public class YaYaMusicDetail extends BaseActivity {
    ViewPager music_pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yaya_music_detail_aty);
        music_pager = (ViewPager) findViewById(R.id.music_pager);
        music_pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {


            Fragment[] fragments = new Fragment[]{
                    new MusicPagerSongListFg(), new MusicPagerPlayFg(), new MusicPagerSongInfoFg()
            };

            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
        music_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        music_pager.setCurrentItem(1);

    }
}
