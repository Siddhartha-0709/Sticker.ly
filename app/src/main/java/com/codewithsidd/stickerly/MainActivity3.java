package com.codewithsidd.stickerly;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity3 extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        progressBar = findViewById(R.id.progressBar);

    }
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            currentUser.reload();
//        }
//    }
    public void submit_login(View view)
    {
        progressBar.setVisibility(View.VISIBLE);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        String emailText = email.getText().toString();
        EditText pwd = findViewById(R.id.editTextTextPassword2);
        String password = pwd.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(emailText, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        assert user != null;
                        Intent intent = new Intent(this,MainActivity4.class);
                        intent.putExtra("userID", user.getUid());
                        startActivity(intent);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity3.this, "Authentication failed",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}