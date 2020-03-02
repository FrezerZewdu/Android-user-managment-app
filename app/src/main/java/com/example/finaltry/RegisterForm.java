package com.example.finaltry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterForm extends AppCompatActivity {

    DBHelper myDBHelper;
    SQLiteDatabase db;
    EditText firstName, lastNsme, username, email, password, phonenumber;
    private RadioGroup radioGender;
    Button regBtn;
    private String gender;
    private SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        //remember problem started here1
        myDBHelper= new DBHelper(this);

        firstName=findViewById(R.id.firstnameview);
        lastNsme=findViewById(R.id.lastnameview);
        username=findViewById(R.id.usernameview);
        email=findViewById(R.id.emailview);
        password=findViewById(R.id.passwordview);
        phonenumber=findViewById(R.id.phonenumberview);
        radioGender=findViewById(R.id.radiogroup);
        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=findViewById(checkedId);
                gender=radioButton.getText().toString();
            }
        });


           regBtn=(Button) findViewById(R.id.registerbutton);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
                Intent intent = new Intent(getApplicationContext(), RecyclerView.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(){


            String fname=firstName.getText().toString();
            String lname=lastNsme.getText().toString();
            String userName=username.getText().toString();
            String eMail=email.getText().toString();
            String pass=password.getText().toString();
            String mobile=phonenumber.getText().toString();
            String sex=gender;

            if(TextUtils.isEmpty(userName)){
                Toast.makeText(getApplicationContext(),"username is Mandatory",Toast.LENGTH_LONG).show();
            }
            if(TextUtils.isEmpty(eMail)){
                Toast.makeText(getApplicationContext(),"Email addresses should be specified",Toast.LENGTH_LONG).show();
            }
            if(TextUtils.isEmpty(pass)){
                Toast.makeText(getApplicationContext(),"Enter password please",Toast.LENGTH_LONG).show();
            }
            if(pass.length()<8){
                password.setError("minimum charachters for password is 8");
            }
        boolean isInserted=myDBHelper.insertData(fname,lname,userName,eMail,pass,mobile,sex);
            if(isInserted==true){
                Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
                firstName.setText("");
                lastNsme.setText("");
                username.setText("");
                email.setText("");
                password.setText("");
                phonenumber.setText("");

            }
            else {
                Toast.makeText(getApplicationContext(), "Registration failed. Please try again", Toast.LENGTH_LONG).show();
            }



    }
}
