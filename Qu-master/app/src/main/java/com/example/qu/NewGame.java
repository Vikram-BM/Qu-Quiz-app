package com.example.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewGame extends AppCompatActivity {

    EditText Question,Answer,option1,option2,option3,option4;
    Button post,end;
    int number=1;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        Question=findViewById(R.id.que);
        Answer=findViewById(R.id.ans);
        option1=findViewById(R.id.opt1);
        option2=findViewById(R.id.opt2);
        option3=findViewById(R.id.opt3);
        option4=findViewById(R.id.opt4);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        post=findViewById(R.id.post);
        end=findViewById(R.id.end);


    }

    public void add(View view) {
        String q = Question.getText().toString();
        String a = Answer.getText().toString();
        String o1 = option1.getText().toString();
        String o2 = option2.getText().toString();
        String o3 = option3.getText().toString();
        String o4 = option4.getText().toString();
        if(TextUtils.isEmpty(q))
        {
            Toast.makeText(NewGame.this, "Question Empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(a))
        {
            Toast.makeText(NewGame.this, "Answer Empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(o1))
        {
            Toast.makeText(NewGame.this, "Option1 Empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(o2))
        {
            Toast.makeText(NewGame.this, "Option2 Empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(o3))
        {
            Toast.makeText(NewGame.this, "Option3 Empty", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(o4))
        {
            Toast.makeText(NewGame.this, "Option4 Empty", Toast.LENGTH_SHORT).show();
        }

        if(!TextUtils.isEmpty(q)&&!TextUtils.isEmpty(a)&&!TextUtils.isEmpty(o1)&&!TextUtils.isEmpty(o2)&&!TextUtils.isEmpty(o3)&&!TextUtils.isEmpty(o4)){
            newQ obj=new newQ();
            writeNewUser(obj.newQuiz,Integer.toString(number), q, o1, o2, o3, o4, a);
            Question.setText(null);
            Answer.setText(null);
            option1.setText(null);
            option2.setText(null);
            option3.setText(null);
            option4.setText(null);
            number = number + 1;
        }
    }

    public void end(View view)
    {
        newQ obj=new newQ();
        mDatabase.child("Quiz Data").child(obj.newQuiz+"X").setValue(Integer.toString(number-1));
        startActivity(new Intent(getApplicationContext(),HomePage.class));
    }
    private void writeNewUser(String name,String userId, String question, String option1,String option2,String option3,String option4,String answer) {
        store user = new store(question,option1,option2,option3,option4,answer);

        mDatabase.child("Quiz Data").child(name).child(userId).setValue(user);
    }

}
