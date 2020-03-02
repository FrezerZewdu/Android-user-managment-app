package com.example.finaltry;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailUsers extends AppCompatActivity {

    public TextView fname,lname,usern,email,mobile,gender,date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last);

        fname = findViewById(R.id.fname3);
        lname = findViewById(R.id.lname3);
        usern = findViewById(R.id.username3);
        email = findViewById(R.id.email3);
        mobile = findViewById(R.id.phonenumber3);
        gender = findViewById(R.id.gender3);
        date = findViewById(R.id.date);

        Bundle b=getIntent().getExtras();
        usern.setText(b.get("Fname").toString());
        lname.setText(b.get("lname").toString());
        usern.setText(b.get("usname").toString());
        email.setText(b.get("email").toString());
        mobile.setText(b.get("phone").toString());
        gender.setText(b.get("sex").toString());
        date.setText(b.get("date").toString());


    }
        }
