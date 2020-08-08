package com.example.qu;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {

    EditText txt_first,txt_last,txt_password,txt_email,txt_confirm;
    Button btn_signup;
    RadioButton male,female;
    static String f,l,e,p,c,g;
    FirebaseAuth mFirebase;
    DatabaseReference firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txt_first=findViewById(R.id.firstname);
        txt_last=findViewById(R.id.lastname);
        txt_email=findViewById(R.id.email);
        txt_password=findViewById(R.id.password);
        txt_confirm=findViewById(R.id.confirm);

        btn_signup= findViewById(R.id.signup);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        mFirebase=FirebaseAuth.getInstance();
        firebase = FirebaseDatabase.getInstance().getReference();
        btn_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                f=txt_first.getText().toString();
                l=txt_last.getText().toString();
                e=txt_email.getText().toString();
                p=txt_password.getText().toString();
                c=txt_confirm.getText().toString();
                g="";

                if(male.isChecked())
                {
                    g="Male";
                }
                if(female.isChecked())
                {
                    g="Female";
                }


                if(TextUtils.isEmpty(f))
                {
                    Toast.makeText(SignUp.this,"Please enter first name",Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(l))
                {
                    Toast.makeText(SignUp.this,"Please enter last name",Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(e))
                {
                    Toast.makeText(SignUp.this,"Please enter email",Toast.LENGTH_SHORT).show();
                }


                if(TextUtils.isEmpty(p))
                {
                    Toast.makeText(SignUp.this,"Please enter password",Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(c))
                {
                    Toast.makeText(SignUp.this,"Confirm password",Toast.LENGTH_SHORT).show();
                }

                if(!TextUtils.equals(p,c))
                {
                    Toast.makeText(SignUp.this,"Passwords Do not match",Toast.LENGTH_SHORT).show();
                }
                if(!TextUtils.isEmpty(f)&&!TextUtils.isEmpty(l)&&!TextUtils.isEmpty(e)&&!TextUtils.isEmpty(p)&&!TextUtils.isEmpty(c)){
                mFirebase.createUserWithEmailAndPassword(e, p)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    writeNewUser(f,l,e);
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                }
                            }});






            }}
        });
    }


        public void writeNewUser(String firstName,String lastName, String username)
        {
            username=username.substring(0,username.indexOf("@"));
            UserData obj=new UserData(firstName,lastName,username);
            firebase.child("Users").child(username).setValue(obj);
            setUsername(this,username);

        }

    public static void setUsername(Context context, String username) {
        SharedPreferences prefs = context.getSharedPreferences("myAppPackage", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", username);
        editor.commit();
    }

    }
