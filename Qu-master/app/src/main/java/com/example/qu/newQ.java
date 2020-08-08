package com.example.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class newQ extends AppCompatActivity {

    static String newQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_q);
        final EditText a;
        Button btn_submit;
        btn_submit=findViewById(R.id.submit);
        a = findViewById(R.id.newQuiz);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newQuiz=a.getText().toString();
                startActivity(new Intent(getApplicationContext(),NewGame.class));
            }
        });


    }
}
