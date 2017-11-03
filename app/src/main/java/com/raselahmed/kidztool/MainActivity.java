package com.raselahmed.kidztool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
    private EditText etEmail,etPassword;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;
=======
    Button btnRegiser;
>>>>>>> 960e8d62aff99e29ce3ab2da9cb7d5ecc41a26cb
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegiser = (Button) findViewById(R.id.RegisterActivity);

<<<<<<< HEAD
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Button btnReg = (Button) findViewById(R.id.btnRegister);
        TextView tvLink = (TextView) findViewById(R.id.tvLoginLink);

        btnReg.setOnClickListener(new View.OnClickListener() {
=======
        btnRegiser.setOnClickListener(new View.OnClickListener() {
>>>>>>> 960e8d62aff99e29ce3ab2da9cb7d5ecc41a26cb
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }


}
