package com.example.killjoy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText name ;
    EditText email ;
    EditText password ;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_signup);
         name = findViewById(R.id.ed_name);
         email = findViewById(R.id.ed_email);
         password = findViewById(R.id.ed_password);
         progressBar = findViewById(R.id.prg);
        mAuth = FirebaseAuth.getInstance();




    }

    public void OpenSignInPage(View view) {

        startActivity(new Intent(SignupActivity.this,MainActivity.class));
    }

    private void  SignUp(){

        final String n = name.getText().toString().trim();
        final String e = email.getText().toString().trim();
        final String p = password.getText().toString().trim();

        if(n.isEmpty()){
            name.setError("Name is required");
            name.requestFocus();
            return;

        }

        if(e.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;

        }

        if(p.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;

        }
        if(p.length()<-6){
            password.setError("Password is required more then 6 characters");
            password.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
            email.setError("please provide valid email");
            email.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(e,p)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(SignupActivity.this,"   Successful   ",Toast.LENGTH_LONG ).show();


                }else{
                    Toast.makeText(SignupActivity.this,"   Connection Issue   ",Toast.LENGTH_LONG ).show();

                }
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    public void onSignUp(View view) {

        SignUp();
    }
}
