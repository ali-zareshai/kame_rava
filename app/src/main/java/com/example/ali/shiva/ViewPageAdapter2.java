package com.example.ali.shiva;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPageAdapter2 extends FragmentPagerAdapter {
    public ViewPageAdapter2(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "پیام روز";
            case 1:
                return "ذکر روز";
            case 2:
                return "مناسبت روز";
        }
        return "";
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return Fragment_mainAct_1.newInstance();
            case 1:
                return Fragment_mainAct_2.newInstance();
            case 2:
                return Fragment_mainAct_3.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

