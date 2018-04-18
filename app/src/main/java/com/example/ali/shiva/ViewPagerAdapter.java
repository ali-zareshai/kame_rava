package com.example.ali.shiva;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FragmentSetp1.getInstance();
            case 1:
                return FragmentSetp2.getInstance();
            case 2:
                return FragmentSetp3.getInstance();
            case 3:
                return FragmentSetp4.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
