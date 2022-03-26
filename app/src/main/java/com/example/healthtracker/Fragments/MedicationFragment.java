package com.example.healthtracker.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.healthtracker.Medication;
import com.example.healthtracker.R;
import com.example.healthtracker.RecyclerAdapterMedication;

import java.util.ArrayList;

public class MedicationFragment extends Fragment {
    private ArrayList<Medication> medications;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medication, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_MedicationFragment);

        medications = new ArrayList<Medication>();

        setMedications();

        return view;
    }

    private void setMedications() {
        Medication tylenol = new Medication("Tylenol", "painkiller","tylenol.jpg", 6030000L);
        Medication metformin = new Medication("Metformin", "insulin treatment", "metformin.jpg" , 63000000L);
        Medication albuterol = new Medication("Albuterol", "asthma treatment", "albuterol.jpg" , 64800000L);
        Medication atorvastatin = new Medication("Atorvastatin", "high cholesterol treatment", "atorvastatin.jpg" , 68400000L);
        medications.add(0, tylenol);
        medications.add(1, metformin);
        medications.add(2, albuterol);
        medications.add(3, atorvastatin);

        setAdapter();
    }

    private void setAdapter() {
        RecyclerAdapterMedication adapter = new RecyclerAdapterMedication(medications);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setOnFlingListener(null);
        snapHelper.attachToRecyclerView(recyclerView);
    }
}