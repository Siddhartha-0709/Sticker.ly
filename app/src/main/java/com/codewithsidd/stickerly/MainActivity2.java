package com.codewithsidd.stickerly;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        progressBar = findViewById(R.id.progressBar2);
//        progressBar.setVisibility(View.INVISIBLE);

    }

    public void account_exists(View view)
    {
        Toast.makeText(this, "Please Log In to Your Account", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
    public void submit(View view) {
        progressBar.setVisibility(View.VISIBLE);
//        Toast.makeText(this, "Creating Account Please Wait", Toast.LENGTH_SHORT).show();
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        String emailText = email.getText().toString();
        EditText pwd = findViewById(R.id.editTextTextPassword2);
        String password = pwd.getText().toString();


        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(emailText, password)
                .addOnCompleteListener(this, task -> {
                    // Hide the progress bar
                    progressBar.setVisibility(View.INVISIBLE);

                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(this, MainActivity4.class);
                        assert user != null;
                        intent.putExtra("userID", user.getUid());
                        startActivity(intent);
                        Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(this, "Some Error Occured\nPassword less than 6 characters.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    }
