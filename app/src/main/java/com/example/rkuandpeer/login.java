package com.example.rkuandpeer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class login extends AppCompatActivity {
    private EditText UserEmail, UserPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        UserEmail = (EditText) findViewById(R.id.login_email);
        UserPassword = (EditText) findViewById(R.id.login_password);
        Button loginButton = (Button) findViewById(R.id.login_button);
        loadingBar=new ProgressDialog(login.this);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowingUserToLogin();


            }




            private void AllowingUserToLogin() {
                String email = UserEmail.getText().toString();
                String password = UserPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(login.this, "please write your email...", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(login.this, "please write your password...", Toast.LENGTH_SHORT).show();
                }
                else {

                    loadingBar.setTitle("Login");
                    loadingBar.setMessage("please wait,while logging");
                    loadingBar.show();
                    loadingBar.setCanceledOnTouchOutside(true);


                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {

                                        loadingBar.dismiss();

                                        Intent LoginIntent = new Intent(login.this, MainActivity.class);
                                        startActivity(LoginIntent);
                                        Toast.makeText(login.this, "Login Sucessful", Toast.LENGTH_SHORT).show();

                                    }
                                    else
                                    {

                                        String message= Objects.requireNonNull(task.getException()).getMessage();
                                        Toast.makeText(login.this, "Login Failed"+message, Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                }
                            });
                }
            }

        });
    }
}

