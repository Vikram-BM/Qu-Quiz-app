package com.example.qu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Username extends AppCompatActivity {

    EditText pass,conf;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        pass=findViewById(R.id.email1);
        conf=findViewById(R.id.email2);
        auth=FirebaseAuth.getInstance();
    }

    public void btn_back2(View View)
    {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

        if(pass.getText().toString().equals(conf.getText().toString())) {
            if (user != null) {
                user.updateEmail(pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Username.this, "Email Changed", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Settings.class));
                                } else {
                                    Toast.makeText(Username.this, "Email Changed Unsuccessful", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }

        else {

            Toast.makeText(Username.this, "Password Doesn't match", Toast.LENGTH_SHORT).show();


        }
    }
}
