package com.example.healthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.fragment.app.FragmentManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainMenuActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    ViewPager2 viewPager;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        chipNavigationBar = findViewById(R.id.bottom_navigation);

        viewPager = findViewById(R.id.viewPager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fragmentManager, getLifecycle());
        viewPager.setAdapter(fragmentAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                int iid = 0;

                switch (position) {
                    case 0:
                        iid = 2131231254;
                        break;
                    case 1:
                        iid = 2131231255;
                        break;
                    case 2:
                        iid = 2131231256;
                        break;
                }

                chipNavigationBar.setItemSelected(iid, true);
            }
        });

        bottomMenu();
    }

    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                int iid = 0;
                switch (i) {
                    case R.id.bottom_nav_bmi:
                        viewPager.setCurrentItem(0);
                        iid = chipNavigationBar.getSelectedItemId();
                        Log.d("search id", "Search " + String.valueOf(iid));
                        break;
                    case R.id.bottom_nav_diet:
                        viewPager.setCurrentItem(1);
                        iid = chipNavigationBar.getSelectedItemId();
                        Log.d("wishlist id", "Wishlist " + String.valueOf(iid));
                        break;
                    case R.id.bottom_nav_medication:
                        viewPager.setCurrentItem(2);
                        iid = chipNavigationBar.getSelectedItemId();
                        Log.d("pending id", "Pending " + String.valueOf(iid));
                        break;
                }
            }
        });
    }

}
