package com.example.specialmessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText username,email,password,confirm;
    Button btn;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.SignUpUsername);
        email = findViewById(R.id.SignUpEmail);
        password = findViewById(R.id.SignUpPassword);
        confirm = findViewById(R.id.SignUpConfirmPassword);
        btn = findViewById(R.id.SignUpButton2);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef  = database.getReference("Users");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpMethod(email.getText().toString(),password.getText().toString(),username.getText().toString());

            }
        });
    }
    private void signUpMethod(final String email, String password, final String username)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("myTag", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            myRef.child(user.getUid()).child("email").setValue(email);
                            myRef.child(user.getUid()).child("username").setValue(username);
                            startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("myTag", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
