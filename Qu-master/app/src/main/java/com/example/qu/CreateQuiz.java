package com.example.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
    }

    public void btn_newQuiz(View view)
    {
        startActivity(new Intent(getApplicationContext(),newQ.class));
    }

    public void btn_updateexisting(View view)
    {
        startActivity(new Intent(getApplicationContext(),Previous.class));
    }
}
