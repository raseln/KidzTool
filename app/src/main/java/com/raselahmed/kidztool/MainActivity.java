package com.raselahmed.kidztool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //private EditText etEmail, etPassword;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = (Button) findViewById(R.id.RegisterActivity);

        //etEmail = (EditText) findViewById(R.id.etEmail);
        //etPassword = (EditText) findViewById(R.id.etPassword);
        //Button btnReg = (Button) findViewById(R.id.btnRegister);
        //TextView tvLink = (TextView) findViewById(R.id.tvLoginLink);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MathQuiz.class);
                startActivity(intent);
            }
        });
    }
}
