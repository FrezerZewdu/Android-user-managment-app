package com.example.finaltry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button register,login;
    EditText username,password;
    DBHelper db;
    private Shared sharedpreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DBHelper(this);

        sharedpreference=new Shared(this);

        username=findViewById(R.id.usernamelog);
        password=findViewById(R.id.passwordfield);



        login=findViewById(R.id.loginbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
      register = findViewById(R.id.signupbtn);
      register.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(getApplicationContext(),RegisterForm.class);
              startActivity(intent);
          }
      });
        if(sharedpreference.rLogInStatus()){
            startActivity(new Intent(getApplicationContext(),RecyclerView.class));
        }
    }
    private void login(){
        username=findViewById(R.id.usernamelog);
        password=findViewById(R.id.passwordfield);

        String userName=username.getText().toString();
        String passs=password.getText().toString();
        db=new DBHelper(this);
        Cursor cr=db.getAllData();
        while (cr.moveToNext()) {
            String usr = cr.getString(4);
            String pass = cr.getString(6);

            if (userName.equals(usr) && passs.equals(pass)) {
                sharedpreference.wLogInStatus(true);
                startActivity(new Intent(this,RecyclerView.class));
            }
           // else{
             //   Toast.makeText(getApplicationContext(),"Invalid email password",Toast.LENGTH_SHORT).show();
            //}
        }


    }
    }

