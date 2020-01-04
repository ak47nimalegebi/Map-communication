package com.bawei6.usermodule.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class UserLianPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    private List<String> list2;

    public UserLianPagerAdapter(@NonNull FragmentManager fm, List<Fragment> list, List<String> list2) {
        super(fm);
        this.list = list;
        this.list2 = list2;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list2.get(position);
    }
}
