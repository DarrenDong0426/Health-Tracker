package com.example.healthtracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RecyclerAdapterDiet extends RecyclerView.Adapter<RecyclerAdapterDiet.MyViewHolder> {

    private DatabaseReference mFirebase;
    private StorageReference mStorage;
    private Uri imageUri;

    private Long time;

    private int largestKey;

    Context context;

    private ArrayList<Meal> meals;

    public RecyclerAdapterDiet(ArrayList<Meal> meals) {
        this.meals = meals;
        mFirebase = FirebaseDatabase.getInstance().getReference();
        mStorage = FirebaseStorage.getInstance().getReference();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, calories, proteins, fats, carbs;
        private ImageView image;

        public MyViewHolder(final View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.diet_title);
            calories = (TextView) view.findViewById(R.id.diet_calories);
            proteins = (TextView) view.findViewById(R.id.diet_proteins);
            fats = (TextView) view.findViewById(R.id.diet_fats);
            carbs = (TextView) view.findViewById(R.id.diet_carbs);
            image = (ImageView) view.findViewById(R.id.diet_image);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapterDiet.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterDiet.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(meals.get(position).getTitle());
        holder.calories.setText("Calories: " + meals.get(position).getCalories());
        holder.proteins.setText("Proteins: " + meals.get(position).getProteins());
        holder.fats.setText("Fats: " + meals.get(position).getFats());
        holder.carbs.setText("Carbs: " + meals.get(position).getCarbs());

        if (position == 0) {
            holder.image.setImageResource(R.drawable.banana_greek_yogurt);
        }
        if (position == 1) {
            holder.image.setImageResource(R.drawable.perna_perfect);
        }
        if (position == 2) {
            holder.image.setImageResource(R.drawable.avocado_toast);
        }
        if (position == 3) {
            holder.image.setImageResource(R.drawable.mint_choco_protein_shake);
        }
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        context = recyclerView.getContext();
    }

}