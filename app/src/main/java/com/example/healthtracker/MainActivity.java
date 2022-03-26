package com.example.healthtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextInputLayout email, password;
    private Button resetPassword, login, signup;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        email = (TextInputLayout) findViewById(R.id.email);
        password = (TextInputLayout) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(this);

        error = (TextView) findViewById(R.id.error_LoginTextView);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup:
                startActivity(new Intent(this, SignupActivity.class));
                break;
            case R.id.login:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String emailString = email.getEditText().getText().toString().trim();
        String passwordString = password.getEditText().getText().toString().trim();

        if (emailString.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }else{email.setError(null);}

        if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            email.setError("Enter a valid email!");
            email.requestFocus();
            return;
        }else{email.setError(null);}

        if (passwordString.isEmpty()) {
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }else{password.setError(null);}

        mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));

                    //if (user.isEmailVerified()) {
                    //startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
                    //}else{
                    //startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
                    //user.sendEmailVerification();
                    //error.setTextColor(Color.parseColor("#FFFF00"));
                    //error.setText("A Verification Link Has Been Sent\nPlease Verify Your Email To Login");
                    //password.getEditText().getText().clear();
                    //}
                } else {
                    error.setTextColor(Color.parseColor("#FF0000"));
                    error.setText("Failed to login! Try again!");
                    password.getEditText().getText().clear();
                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}