package com.example.healthtracker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthtracker.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class BMIFragment extends Fragment {

    TextView result;
    TextInputLayout heightFt, heightIn, weight;
    Button calculate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_bmi, container, false);

        result = (TextView) v.findViewById(R.id.result);
        heightFt = (TextInputLayout) v.findViewById(R.id.heightFT_border);
        heightIn = (TextInputLayout) v.findViewById(R.id.heightIN_border);
        weight = (TextInputLayout) v.findViewById(R.id.weight_border);
        calculate = (Button) v.findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int feet = Integer.valueOf(heightFt.getEditText().getText().toString().trim());
                int inch = Integer.valueOf(heightIn.getEditText().getText().toString().trim());
                int w = Integer.valueOf(weight.getEditText().getText().toString().trim());
                int bmi = (int) getBMI(feet, inch, w);
                result.setText(bmi + " (" + healthStatus(bmi) +')');

            }
        });
        return v;
    }

    public static double getBMI(int heightInFeet, int heightInInches, double weight)
    {
        //get the BMI of the human
        double h = (heightInFeet * 12) + heightInInches;
        double bmi = (weight / (h * h)) * 703;
        return bmi;
    }

    //Determine if they are cool or not
    public static String healthStatus(double bmi)
    {
        if (bmi < 18.5)
        {
            return "Underweight";
        }
        else if (bmi >= 18.5 && bmi < 25.0)
        {
            return "Healthy weight";
        }
        else if (bmi >= 25.0 && bmi < 30.0)
        {
            return "Overweight";
        }
        else
        {
            return "Obese";
        }
    }




}