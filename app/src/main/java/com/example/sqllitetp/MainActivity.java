package com.example.sqllitetp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password, password2;
    Button signUp, signIn;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.usernameedt);
        password = findViewById(R.id.passwordedt);
        password2 = findViewById(R.id.passwordedt2);
        signUp = findViewById(R.id.btnsignup);
        signIn = findViewById(R.id.btnsignin);
        DB = new DBHelper(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    String repass = password2.getText().toString();

                    if(user.equals("")||pass.equals("")||repass.equals(""))
                        Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else{
                        if(pass.equals(repass)){
                            Boolean checkuser = DB.checkusername(user);
                            if(!checkuser){
                                Boolean insert = DB.insertData(user, pass);
                                if(insert){
                                    Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                        }
                    } }
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.learnandroid.loginsqlite.LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}