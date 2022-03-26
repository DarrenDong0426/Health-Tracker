package com.example.healthtracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.healthtracker.Fragments.BMIFragment;
import com.example.healthtracker.Fragments.DietFragment;
import com.example.healthtracker.Fragments.MedicationFragment;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new BMIFragment();
            case 1:
                return new DietFragment();
            case 2:
                return new MedicationFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}