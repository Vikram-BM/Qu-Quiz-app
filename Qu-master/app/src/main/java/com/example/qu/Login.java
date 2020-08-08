package com.example.qu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    String username;
    String password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        auth = FirebaseAuth.getInstance();
        final EditText u,p;
        Button btn_signin;
        u=findViewById(R.id.user);
        p=findViewById(R.id.pass);
        btn_signin=findViewById(R.id.signin);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=u.getText().toString();
                password=p.getText().toString();
                auth = FirebaseAuth.getInstance();


                auth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {


                                    Toast.makeText(Login.this, "Authorization Failed", Toast.LENGTH_LONG).show();


                                } else {

                                    Intent intent = new Intent(Login.this, HomePage.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });


            }
        });


    }

    public void btn_signupForm(View view)
    {
        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }



}
