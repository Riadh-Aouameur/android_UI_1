package com.example.killjoy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity2 extends AppCompatActivity {
    FloatingActionButton  floatingActionButton;
    EditText email ;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main2);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.pg);

        floatingActionButton = findViewById(R.id.btn);
        email = findViewById(R.id.ed_R_email);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

    }

    private void resetPassword() {

        final String e = email.getText().toString().trim();


        if(e.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;

        }



        if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
            email.setError("please provide valid email");
            email.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {


                if(task.isSuccessful()){
                    Toast.makeText(MainActivity2.this,"  Verify Your Email  ",Toast.LENGTH_LONG ).show();

                }else {

                    Toast.makeText(MainActivity2.this,"   Try Again !   ",Toast.LENGTH_LONG ).show();

                }
                progressBar.setVisibility(View.GONE);
            }
        });


    }

    public void OpenSignupPage(View view) {
        startActivity(new Intent(MainActivity2.this,MainActivity.class));

    }
}