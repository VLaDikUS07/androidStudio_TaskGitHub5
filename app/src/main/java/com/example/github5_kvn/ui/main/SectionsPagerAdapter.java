package com.example.github5_kvn.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.github5_kvn.FirstGitHub;
import com.example.github5_kvn.FourthGitHub;
import com.example.github5_kvn.R;
import com.example.github5_kvn.SecondGitHub;
import com.example.github5_kvn.ThirdGitHub;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            FirstGitHub tab1 = new FirstGitHub();
            return tab1;
        }
        else if (position == 1) {
            SecondGitHub tab2 = new SecondGitHub();
            return tab2;
        }
        else if (position == 2) {
            ThirdGitHub tab3 = new ThirdGitHub();
            return tab3;
        }
        else if (position == 3) {
            FourthGitHub tab4 = new FourthGitHub();
            return tab4;
        }

        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}