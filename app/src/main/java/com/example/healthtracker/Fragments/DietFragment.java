package com.example.healthtracker.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.healthtracker.Meal;
import com.example.healthtracker.R;
import com.example.healthtracker.RecyclerAdapterDiet;


import java.util.ArrayList;

public class DietFragment extends Fragment {
    private ArrayList<Meal> meals;
    private RecyclerView recyclerView;
    private TextView calories, proteins, fats, carbs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_DietFragment);
        calories = (TextView) view.findViewById(R.id.calories);
        proteins = (TextView) view.findViewById(R.id.protein);
        fats = (TextView) view.findViewById(R.id.fats);
        carbs = (TextView) view.findViewById(R.id.carbs);



        meals = new ArrayList<Meal>();

        setMeals();

        return view;
    }

    private void setMeals() {
        Meal sheepraSpecial = new Meal("Banana Greek Yogurt", "banana_greek_yogurt.jpg", 100, 8, 2, 13);
        Meal pernasPerfectMeal = new Meal("Fish Bones Stir-Fried in Fish Oil", "perna_perfect.jpg", 25, 0, 2, 0);
        Meal basicPerson = new Meal("Avocado Toast", "avocado_toast.jpg", 195, 5, 11, 20);
        Meal bipraShake = new Meal("Mint-Choco Protein Shake", "mint_choco_protein_shake.jpg" , 280, 40, 9, 9);

        meals.add(0, sheepraSpecial);
        meals.add(1, pernasPerfectMeal);
        meals.add(2, basicPerson);
        meals.add(3, bipraShake);

        setAdapter();
    }

    private void setAdapter() {
        RecyclerAdapterDiet adapter = new RecyclerAdapterDiet(meals);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setOnFlingListener(null);
        snapHelper.attachToRecyclerView(recyclerView);
    }
}