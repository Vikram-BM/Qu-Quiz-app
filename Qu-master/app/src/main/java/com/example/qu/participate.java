package com.example.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class participate extends AppCompatActivity {

    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate);
    }

    public void new_game(View view)
    {
        EditText edt=findViewById(R.id.enter);
        str=edt.getText().toString();
        QuizDATA obj=new QuizDATA(str);
        startActivity(new Intent(getApplicationContext(),InGame.class));
    }
}
