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

public class RecyclerAdapterMedication extends RecyclerView.Adapter<RecyclerAdapterMedication.MyViewHolder> {

    private DatabaseReference mFirebase;
    private StorageReference mStorage;
    private Uri imageUri;

    private Long time;

    private int largestKey;

    Context context;

    private ArrayList<Medication> medications;

    public RecyclerAdapterMedication(ArrayList<Medication> medications) {
        this.medications = medications;
        mFirebase = FirebaseDatabase.getInstance().getReference();
        mStorage = FirebaseStorage.getInstance().getReference();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView timer, title, desc;
        private ImageView image;
        private Button remove;

        public MyViewHolder(final View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.med_title);
            timer = (TextView) view.findViewById(R.id.timer);
            desc = (TextView) view.findViewById(R.id.descString);
            image = (ImageView) view.findViewById(R.id.image);
            remove = (Button) view.findViewById(R.id.remove);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapterMedication.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medication_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterMedication.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("test", medications.get(position).getTitle());
        holder.title.setText(medications.get(position).getTitle());
        holder.desc.setText(medications.get(position).getDesc());

        Long currentTime = System.currentTimeMillis();
        Long millis = medications.get(position).getDue() + currentTime;

        int seconds = (int) (millis / 1000) % 60 ;
        int minutes = (int) ((millis / (1000*60)) % 60);
        int hours   = (int) ((millis / (1000*60*60)) % 24);

        String timeLeft = hours + ":" + minutes + ":" + seconds;

        holder.timer.setText(timeLeft);

        if (position == 0) {
            holder.image.setImageResource(R.drawable.tylenol);
        }
        if (position == 1) {
            holder.image.setImageResource(R.drawable.metformin);
        }
        if (position == 2) {
            holder.image.setImageResource(R.drawable.albuterol);
        }
        if (position == 3) {
            holder.image.setImageResource(R.drawable.atorvastatin);
        }

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medications.remove(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        context = recyclerView.getContext();
    }

}