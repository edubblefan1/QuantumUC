package com.example.quantumuc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EmailPasswordActivity extends Activity {
    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //reload();     updates UI assuming user is already logged in
        }
    }

    private void createAccount(String email, String password){
        int aIndex = email.indexOf('@');
        StringBuilder domain = null;

        for(int c = aIndex + 1; c < email.length(); c++){
            domain.append(email.charAt(c));
        }

        if(domain.equals("ucmerced.edu")) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG, "User made Successfully!");
                                FirebaseUser user = mAuth.getCurrentUser();

                                //updateUI(user);   used for displaying next screen after success
                            } else{
                                Log.w(TAG, "User Made Fail", task.getException());
                                Toast.makeText(EmailPasswordActivity.this, "CODE ERROR", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else{
            Toast.makeText(this, "Not a UC Merced Email", Toast.LENGTH_LONG).show();
        }
    }

    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "Sign in Successfull!");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user) directs to home page
                        } else{
                            Log.w(TAG, "Sign in Fail");
                            Toast.makeText(EmailPasswordActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }
}
