package com.example.comp3520finalproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AuthPagerAdapter extends FragmentStateAdapter {

    public AuthPagerAdapter(@NonNull FragmentActivity fa)
    {
        super(fa);
    }

    public Fragment createFragment(int position)
    {
        if (position == 0) return new fragment_login();
        else return new fragment_signup();
    }

    public int getItemCount()
    {
        return 2;
    }


}
