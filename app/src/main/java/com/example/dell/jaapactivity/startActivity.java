package com.example.dell.jaapactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class startActivity extends AppCompatActivity {
    Button singup, login;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        singup =findViewById(R.id.signUp);
        login = findViewById(R.id.login);

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i = new Intent(startActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(startActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
