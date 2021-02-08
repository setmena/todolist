package com.example.splashactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public void SignIn (View View){
        EditText Email = (EditText) findViewById(R.id.userEmail1);
        EditText Password = (EditText) findViewById(R.id.userPassword1);
        String email = (String) Email.getText().toString();
        String password = (String) Password.getText().toString();
        if (email.isEmpty()){
            Toast.makeText(this, "Enter your mail", Toast.LENGTH_SHORT).show();
        }
        else if (password.isEmpty()){
            Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent ToDoListIntent = new Intent(getApplicationContext(), ToDoList.class);
                                startActivity(ToDoListIntent);
                            } else {
                                Toast.makeText(SignIn.this, "Sign In Failed",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
    public void SignUp (View View){
        Intent SignUpIntent = new Intent(getApplicationContext(), Authentication.class);
        startActivity(SignUpIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
    }
}