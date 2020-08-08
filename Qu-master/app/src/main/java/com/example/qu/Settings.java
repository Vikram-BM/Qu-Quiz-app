package com.example.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void btn_editusername(View view)
    {
        startActivity(new Intent(getApplicationContext(),Username.class));
    }

    public void btn_editpassword(View view)
    {
        startActivity(new Intent(getApplicationContext(),Password.class));
    }

    public void btn_back1(View view)
    {
        startActivity(new Intent(getApplicationContext(),HomePage.class));
    }

}
