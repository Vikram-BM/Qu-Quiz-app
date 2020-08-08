package com.example.qu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class result extends AppCompatActivity {

    TextView score,total;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        score=findViewById(R.id.score);
        total=findViewById(R.id.total);
        String str=getUsername(this);
        ref= FirebaseDatabase.getInstance().getReference().child("Users").child(str);
        ValueEventListener resuts=ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ScoreData obj=dataSnapshot.getValue(ScoreData.class);
                score.setText(obj.getScore());
                total.setText(obj.getTotal());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void End(View view)
    {
        startActivity(new Intent(getApplicationContext(),HomePage.class));
    }
    public static String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        return prefs.getString("username", "");

    }
}
