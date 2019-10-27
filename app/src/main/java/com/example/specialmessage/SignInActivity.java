package com.example.specialmessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
   Button btn1;
   EditText Email,Password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        Email  = findViewById(R.id.SignInEmail);
        Password = findViewById(R.id.SignInPassword);
        btn1 = findViewById(R.id.SignInButton2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SignInMethod(Email.getText().toString(),Password.getText().toString());
            }
        });
    }
    private void SignInMethod(String email,String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                            intent.putExtra("id",mAuth.getCurrentUser().getUid());
                            startActivity(intent);
                            finish();

                        } else {

                        }

                        // ...
                    }
                });
    }
}
