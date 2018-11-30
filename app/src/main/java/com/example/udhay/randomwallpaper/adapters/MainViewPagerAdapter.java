package com.example.udhay.randomwallpaper.adapters;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.udhay.randomwallpaper.fragments.CollectionsFragment;
import com.example.udhay.randomwallpaper.fragments.WallpapersFragment;

import java.util.Arrays;
import java.util.List;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    List<String> fragmentTitle = Arrays.asList(WallpapersFragment.FRAGMENT_TAB_TITLE, CollectionsFragment.FRAGMENT_TAB_TITLE);

    List<? extends Fragment> fragments = Arrays.asList(WallpapersFragment.getWallPaperSFragment(
            WallpapersFragment.WALLPAPERS_FRAGMENT_ACTIONS.NEW_WALLPAPER_DISPLAY, 0
    ), CollectionsFragment.getCollectionSFragment(CollectionsFragment.COLLECTIONS_FRAGMENT_ACTIONS.NEW_COLLECTIONS_DISPLAY, null));

    public MainViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }

}
