package com.example.admin.bookstore.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.bookstore.Fragments.SachBCFragments;
import com.example.admin.bookstore.Fragments.SachNBFragments;
import com.example.admin.bookstore.Fragments.SachNNFragments;

import java.util.List;

/**
 * Created by Admin on 4/4/2017.
 */

public class ViewPagerAdapters extends FragmentPagerAdapter{
    List<Fragment> list;

    public ViewPagerAdapters(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }



    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
