package com.codewithsidd.stickerly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {

    EditText headingEditText;
    EditText noteEditText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        headingEditText = findViewById(R.id.heading);
        noteEditText = findViewById(R.id.notes);
        progressBar= findViewById(R.id.progressBar5);
    }

    public static class MyFile {
        private String heading;
        private String notes;
        private String uID;

        public MyFile() {
            // Default constructor required for Firebase Realtime Database
        }

        public MyFile(String heading, String notes, String uID) {
            this.heading = heading;
            this.notes = notes;
            this.uID = uID;
        }

        public String getHeading() {
            return heading;
        }

        public void setHeading(String heading) {
            this.heading = heading;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getUID() {
            return uID;
        }

        public void setUID(String uID) {
            this.uID = uID;
        }
    }
    public void pushtoDatabase(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        String headingValue = headingEditText.getText().toString();
        String noteValue = noteEditText.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User and Data"); // Replace with your desired node name
//        Toast.makeText(this, "Referenced Database", Toast.LENGTH_SHORT).show();

        MyFile ob = new MyFile(headingValue, noteValue, userID);
        myRef.push().setValue(ob);
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "Note saved to Database", Toast.LENGTH_SHORT).show();
        Intent intent5 = new Intent(this, MainActivity4.class);
        intent5.putExtra("userID",userID);
        startActivity(intent5);
    }
}
