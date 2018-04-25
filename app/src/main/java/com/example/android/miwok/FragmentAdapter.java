package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter{
    private Context context;
    public FragmentAdapter(Context context,FragmentManager fm){
        super(fm);
        this.context = context;
    }
    @Override
    public Fragment getItem(int position) {
        if(position==0) {
            return new NumbersFragment();
        }else if(position==1){
            return new ColorsFragment();
        }else if(position==2){
            return new FamilyMembersFragment();
        }else{
            return new PhrasesFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return context.getString(R.string.category_numbers);
        }else if (position == 1){
            return context.getString(R.string.category_colors);
        }else if(position ==2){
            return context.getString(R.string.category_family);
        }else {
            return context.getString(R.string.category_phrases);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
