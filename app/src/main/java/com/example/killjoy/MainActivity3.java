package com.example.killjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView= findViewById(R.id.text);
        textView.setText("EMAIL : "+getIntent().getStringExtra("EMAIL")+" \nPASSWORD : "+getIntent().getStringExtra("PASSWORD"));


    }
}