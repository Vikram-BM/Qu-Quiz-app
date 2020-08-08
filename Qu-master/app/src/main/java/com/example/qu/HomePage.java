package com.example.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

    }

    public void btn_login(View view)
    {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    public void btn_about(View view)
    {
        startActivity(new Intent(getApplicationContext(),About.class));
    }

    public void btn_settings(View view)
    {
        startActivity(new Intent(getApplicationContext(),Settings.class));
    }

    public void Game(View view)
    {
        startActivity(new Intent(getApplicationContext(),newQ.class));
    }

    public void btn_quiz(View view)
    {
        startActivity(new Intent(getApplicationContext(),participate.class));
    }
}
