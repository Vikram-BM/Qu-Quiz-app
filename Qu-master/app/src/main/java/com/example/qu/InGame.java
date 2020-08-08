package com.example.qu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qu.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InGame extends AppCompatActivity {

    Button b1,b2,b3,b4,nex,finish;
    TextView question;
    int correct=0;
    int wrong=0;
    DatabaseReference ref,mDatabase;
    int number=1;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        b1=findViewById(R.id.option1);
        b2=findViewById(R.id.option2);
        b3=findViewById(R.id.option3);
        b4=findViewById(R.id.option4);
        question=findViewById(R.id.questions);
        nex=findViewById(R.id.next);
        updatequestion();
        nex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                updatequestion();

            }
        });

    }

    private void updatequestion() {

        b1.setBackgroundColor(Color.WHITE);
        b2.setBackgroundColor(Color.WHITE);
        b3.setBackgroundColor(Color.WHITE);
        b4.setBackgroundColor(Color.WHITE);
        QuizDATA obj=new QuizDATA();

        ref=FirebaseDatabase.getInstance().getReference().child("Quiz Data").child(obj.getStr()).child(Integer.toString(number));


        ValueEventListener results = ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final store obj = dataSnapshot.getValue(store.class);
                try {
                    question.setText(obj.getQuestion());
                    b1.setText(obj.getOption1());
                    b2.setText(obj.getOption2());
                    b3.setText(obj.getOption3());
                    b4.setText(obj.getOption4());

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b1.getText().toString().equals(obj.getAnswer())) {
                                b1.setBackgroundColor(Color.GREEN);
                                correct++;
                            } else {
                                wrong++;
                                b1.setBackgroundColor(Color.RED);
                                if (b2.getText().toString().equals(obj.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(obj.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(obj.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }

                            }
                        }
                    });

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b2.getText().toString().equals(obj.getAnswer())) {
                                b2.setBackgroundColor(Color.GREEN);
                                correct++;
                            } else {
                                wrong++;
                                b2.setBackgroundColor(Color.RED);
                                if (b1.getText().toString().equals(obj.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(obj.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(obj.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }
                            }
                        }
                    });

                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b3.getText().toString().equals(obj.getAnswer())) {
                                b3.setBackgroundColor(Color.GREEN);
                                correct++;
                            } else {
                                wrong++;
                                b3.setBackgroundColor(Color.RED);
                                if (b2.getText().toString().equals(obj.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b1.getText().toString().equals(obj.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                } else if (b4.getText().toString().equals(obj.getAnswer())) {
                                    b4.setBackgroundColor(Color.GREEN);
                                }
                            }
                        }
                    });

                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b4.getText().toString().equals(obj.getAnswer())) {
                                b4.setBackgroundColor(Color.GREEN);
                                correct++;
                            } else {
                                wrong++;
                                b4.setBackgroundColor(Color.RED);
                                if (b2.getText().toString().equals(obj.getAnswer())) {
                                    b2.setBackgroundColor(Color.GREEN);
                                } else if (b3.getText().toString().equals(obj.getAnswer())) {
                                    b3.setBackgroundColor(Color.GREEN);
                                } else if (b1.getText().toString().equals(obj.getAnswer())) {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                            }
                        }
                    });
                }

                catch(NullPointerException abc)
                {
                    number--;
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    writeNewUser( correct, number);
                    startActivity(new Intent(getApplicationContext(),result.class));
                }
            }




            @Override
            public void onCancelled(DatabaseError databaseError) {

                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });


    }

    private void writeNewUser(int score,int total) {
        ScoreData user = new ScoreData();
        user.setScore(Integer.toString(score));
        user.setTotal(Integer.toString(total));
        String str=getUsername(this);
        mDatabase.child("Users").child(str).child("Score").setValue(Integer.toString(score));
        mDatabase.child("Users").child(str).child("Total").setValue(Integer.toString(total));

    }

    public void finish(View view)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        writeNewUser(correct, number);
        startActivity(new Intent(getApplicationContext(),result.class));
    }

    public static String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        return prefs.getString("username", "");

    }

            }


