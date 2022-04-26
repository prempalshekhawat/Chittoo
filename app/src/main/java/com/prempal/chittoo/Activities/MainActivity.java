package com.prempal.chittoo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.prempal.chittoo.Fragments.CertificateFragment;
import com.prempal.chittoo.Fragments.ChatFragment;
import com.prempal.chittoo.Fragments.ProfileFragment;
import com.prempal.chittoo.Fragments.ScoreFragment;
import com.prempal.chittoo.R;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationBarMain);
        bottomNavigationView.setItemSelected(R.id.profileFragment, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new ProfileFragment()).commit();
        bottomMenu();
    }

    private void bottomMenu() {
        bottomNavigationView.setOnItemSelectedListener
                (new ChipNavigationBar.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int i) {
                        Fragment fragment = null;
                        switch (i){
                            case R.id.certificateFragment:
                                fragment = new CertificateFragment();
                                break;

                            case R.id.profileFragment:
                                fragment = new ProfileFragment();
                                break;

                            case R.id.chatFragment:
                                fragment = new ChatFragment();
                                break;

                            case R.id.scoreFragment:
                                fragment = new ScoreFragment();
                                break;
                        }
                        assert fragment != null;
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
                    }
                });
    }
}