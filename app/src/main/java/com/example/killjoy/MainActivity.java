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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {





    EditText email ;
    EditText password ;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.ed_I_email);
        password = findViewById(R.id.ed_I_password);
    }

    public void OpenSignupPage(View view) {
        startActivity(new Intent(MainActivity.this,SignupActivity.class));
    }

    public void onSignIn(View view) {
        singIn();

    }

    private void singIn() {


        final String e = email.getText().toString().trim();
        final String p = password.getText().toString().trim();


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
        if(p.length()<6){
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

        mAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()){
                        Toast.makeText(MainActivity.this,"   Successful LogIn  ",Toast.LENGTH_LONG ).show();

                        Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                        intent.putExtra("EMAIL",e);
                        intent.putExtra("PASSWORD",p);
                        startActivity(intent);



                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this,"   Verify Your Email   ",Toast.LENGTH_LONG ).show();

                    }

                }else {

                    Toast.makeText(MainActivity.this,"   Connection Issue   ",Toast.LENGTH_LONG ).show();
                }

                progressBar.setVisibility(View.GONE);

            }
        });




    }

    public void onReset(View view) {
        startActivity(new Intent(MainActivity.this,MainActivity2.class));

    }
}