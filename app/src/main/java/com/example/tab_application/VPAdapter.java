package com.example.tab_application;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> items;
    private ArrayList<String> itext = new ArrayList<String>();

    public VPAdapter(FragmentManager fm) {
        super(fm);

        items = new ArrayList<Fragment>();
        items.add(new Fragment_Contact());
        items.add(new Fragment_Gallery());
        items.add(new Fragment_Calendar());

        itext.add("Contacts");
        itext.add("Gallery");
        itext.add("Calendar");
    }
    public CharSequence getPageTitle(int position){
        return itext.get(position);
    }
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }
    @Override
    public int getCount() {
        return items.size();
    }
}

