package com.yayayouji.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yayayouji.global.YaYaBundleKey;

/**
 * Created by oceancx on 16/3/21.
 */
public class ImagePagerAdapter extends FragmentPagerAdapter {
    String[] imgUrls = {
            "http://file12.mafengwo.net/M00/9F/C9/wKgBpU4dhneBIobOAAoG55h1z3M18.groupinfo.w310.jpeg",
            "http://file20.mafengwo.net/M00/53/1C/wKgB3FGm0_KAAb0jAAsiMNT3qxk91.groupinfo.w310.jpeg",
            "http://file21.mafengwo.net/M00/84/A7/wKgB3FDDBtiAVy4eAAYWxt_hcCI30.groupinfo.w310.jpeg",
            "http://file8.mafengwo.net/M00/5B/53/wKgByU-wzDyDnPtKAAV5PZnbn5E13.groupinfo.w310.jpeg",
            "http://file20.mafengwo.net/M00/56/86/wKgB3FCnYfSAakS4AEDJhY9X1uY78.groupinfo.w310.jpeg",
            "http://file2.mafengwo.net/M00/F0/20/wKgBm04aKIebqGk-AAO_gCGGYd446.groupinfo.w310.jpeg",
            "http://file7.mafengwo.net/M00/35/02/wKgByU_y59O-tALrAAnjKaOeV8A92.groupinfo.w310.jpeg",
            "http://file25.mafengwo.net/M00/00/C0/wKgB4lL1Cf-AfYmCAAiYubWpl_477.groupinfo.w310.jpeg",
            "http://file21.mafengwo.net/M00/F5/A9/wKgB3FCXWPWAPm-sAAsuXZDHX9I53.groupinfo.w310.jpeg",
            "http://file4.mafengwo.net/M00/62/DB/wKgBm04Y_87Yc7tzAAGHhkE54og84.groupinfo.w310.jpeg",
            "http://file26.mafengwo.net/M00/38/EB/wKgB4lKmoyOAdJwWAAyyRmElAAY57.groupinfo.w310.jpeg",
            "http://file21.mafengwo.net/M00/EB/ED/wKgB21B0QATdrQ4-AAa9V_96uas78.groupinfo.w310.jpeg",
            "http://file21.mafengwo.net/M00/09/A9/wKgB3FGQec2AS-h_AAeihwnYS0s27.groupinfo.w310.jpeg",
            "http://file11.mafengwo.net/M00/88/92/wKgBpU5vqrqzYZA2AADAVWvzNbQ83.groupinfo.w310.jpeg",
            "http://file5.mafengwo.net/M00/93/3A/wKgBjE8JK4SXf8qzAAJarVYv5FQ06.groupinfo.w310.jpeg",
            "http://file21.mafengwo.net/M00/3F/DF/wKgB21BhzeLuPpMqAANYKIcG8rY42.groupinfo.w310.jpeg",
            "http://file20.mafengwo.net/M00/EB/F6/wKgB21B0QAbQEM5hAAPrmtdMmAM50.groupinfo.w310.jpeg",
            "http://file26.mafengwo.net/M00/C2/A7/wKgB4lL01xaATftwAB3UyYP1EAo031.groupinfo.w310.gif",
            "http://file20.mafengwo.net/M00/AB/D6/wKgB3FE6tyuAIAKOABIwyEpGtIo08.groupinfo.w310.jpeg",
            "http://file26.mafengwo.net/M00/BA/E5/wKgB4lOaYk-APVAIAADv55bSLfU73.groupinfo.w310.jpeg"
    };

    public ImagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return imgUrls.length;
    }

    @Override
    public Fragment getItem(int position) {
        ImageFragment imageFg = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(YaYaBundleKey.POS, position);
        imageFg.setArguments(bundle);
        return imageFg;
    }


    private class ImageFragment extends Fragment {
        ImageView im;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            im = new ImageView(container.getContext());
            im.setScaleType(ImageView.ScaleType.FIT_XY);

            return im;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Bundle args = getArguments();
            int pos = args.getInt(YaYaBundleKey.POS, 0);
            Glide.with(getActivity()).load(imgUrls[pos]).into(im);
        }
    }
}
