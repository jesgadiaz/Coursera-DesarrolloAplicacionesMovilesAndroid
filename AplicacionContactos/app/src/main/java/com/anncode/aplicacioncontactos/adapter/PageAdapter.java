package com.anncode.aplicacioncontactos.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 20/04/16.
 */
public class PageAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;

    public PageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /*@Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        //fragments.add(fragment);
        return fragment;
    }*/

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
        //return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //@Override
    //public void destroyItem(ViewGroup container, int position, Object object) {
    //    super.destroyItem(container, position, object);
    //    ((ViewPager) container).removeView((LinearLayout) object);
    //}

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //fragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
