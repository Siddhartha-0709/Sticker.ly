package com.codewithsidd.stickerly;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity7 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        Intent intent = getIntent();
        String receivedValue = intent.getStringExtra("notes");
        textView = findViewById(R.id.textView4); // Initialize the textView variable
        textView.setText(receivedValue);

    }
}
