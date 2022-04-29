package com.prempal.chittoo.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.prempal.chittoo.Adapters.MainViewPagerAdapter;
import com.prempal.chittoo.Fragments.CertificateFragment;
import com.prempal.chittoo.Fragments.ChatFragment;
import com.prempal.chittoo.Fragments.ProfileFragment;
import com.prempal.chittoo.Fragments.ScoreFragment;
import com.prempal.chittoo.R;

public class MainActivity extends FragmentActivity {

//    ChipNavigationBar bottomNavigationView;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    private static final int NUM_PAGES = 4;
    private MainViewPagerAdapter pagerAdapter;
    private String[] titles = new String[]{"Certificate", "Profile", "Chat", "Score"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        bottomNavigationView = findViewById(R.id.bottomNavigationBarMain);
//        bottomNavigationView.setItemSelected(R.id.profileFragment, true);
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new ProfileFragment()).commit();
//        bottomMenu();

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        pagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(pagerAdapter);
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(titles[position])).attach();
    }

    public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) {
                view.setAlpha(0f);

            } else if (position <= 1) {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else {
                view.setAlpha(0f);
            }
        }
    }
//    private void bottomMenu() {
//        bottomNavigationView.setOnItemSelectedListener
//                (new ChipNavigationBar.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(int i) {
//                        Fragment fragment = null;
//                        switch (i){
//                            case R.id.certificateFragment:
//                                fragment = new CertificateFragment();
//                                break;
//
//                            case R.id.profileFragment:
//                                fragment = new ProfileFragment();
//                                break;
//
//                            case R.id.chatFragment:
//                                fragment = new ChatFragment();
//                                break;
//
//                            case R.id.scoreFragment:
//                                fragment = new ScoreFragment();
//                                break;
//                        }
//                        assert fragment != null;
//                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
//                    }
//                });
//    }
}