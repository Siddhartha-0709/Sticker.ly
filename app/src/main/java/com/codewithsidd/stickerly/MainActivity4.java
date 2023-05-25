package com.codewithsidd.stickerly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        progressBar=findViewById(R.id.progressBar3);
    }
    public void newNote(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        String receivedValue = intent.getStringExtra("userID");
        Toast.makeText(this, "UserID-"+receivedValue, Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(this, MainActivity5.class);
        intent2.putExtra("userID", receivedValue);
        progressBar.setVisibility(View.INVISIBLE);
        startActivity(intent2);
    }
    public void viewNote(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Fetching Account Data\nPlease Wait", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        String receivedValue = intent.getStringExtra("userID");
        Intent intent2 = new Intent(this, MainActivity6.class);
        intent2.putExtra("userID", receivedValue);
        progressBar.setVisibility(View.INVISIBLE);
        startActivity(intent2);
    }

}