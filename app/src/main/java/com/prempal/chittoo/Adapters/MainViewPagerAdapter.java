package com.prempal.chittoo.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.prempal.chittoo.Fragments.CertificateFragment;
import com.prempal.chittoo.Fragments.ChatFragment;
import com.prempal.chittoo.Fragments.ProfileFragment;
import com.prempal.chittoo.Fragments.ScoreFragment;

public class MainViewPagerAdapter extends FragmentStateAdapter {

    public MainViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new ProfileFragment();
        switch (position) {
            case 0:
                return new CertificateFragment();
            case 1:
                return new ProfileFragment();
            case 2:
                return new ChatFragment();
            case 3:
                return new ScoreFragment();

        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
