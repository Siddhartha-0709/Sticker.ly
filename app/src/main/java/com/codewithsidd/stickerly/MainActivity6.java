package com.codewithsidd.stickerly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Intent intent = getIntent();
        String receivedValue = intent.getStringExtra("userID");
        search_database_by_uID(receivedValue);
    }

    public void search_database_by_uID(String userId) {
        // Get a reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = database.getReference("User and Data");

        // Create a query to find all entries for the specified user ID
        Query userEntriesQuery = rootRef.orderByChild("uid").equalTo(userId);

        // Execute the query and retrieve the results
        userEntriesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LinearLayout buttonContainer = findViewById(R.id.buttonContainer); // LinearLayout container in your activity_main6 layout
                buttonContainer.removeAllViews(); // Clear any existing views

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve the data for each entry
                    String heading = childSnapshot.child("heading").getValue(String.class);

                    Button myButton = new Button(MainActivity6.this);
                    myButton.setText(heading);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    layoutParams.setMargins(0, 16, 0, 0); // Add margin to create spacing between buttons
                    myButton.setLayoutParams(layoutParams);

                    // Set a click listener for the button
                    myButton.setOnClickListener(view -> {
                        String notes = childSnapshot.child("notes").getValue(String.class);
                        Intent intent = new Intent(MainActivity6.this, MainActivity7.class);
                        intent.putExtra("notes", notes);
                        startActivity(intent);
                    });

                    // Add the button to the LinearLayout container
                    buttonContainer.addView(myButton);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur
                // ...
            }
        });
    }
}
