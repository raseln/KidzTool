package com.raselahmed.kidztool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //private EditText etEmail, etPassword;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;
    Button btnRegister,btnLogIn, btnProfile, btnQuiz, btnDictionary;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = findViewById(R.id.RegisterActivity);
        btnLogIn = findViewById(R.id.LoginActivity);
        btnProfile = findViewById(R.id.ProfileActivity);
        btnQuiz = findViewById(R.id.QuizActivity);
        btnDictionary = findViewById(R.id.Dictionary);
        textView = findViewById(R.id.loggedInUserEmailtext);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (firebaseAuth.getCurrentUser() != null){
            btnRegister.setVisibility(View.GONE);
            btnLogIn.setVisibility(View.GONE);
            btnProfile.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView.setText("Hello, "+user.getEmail());
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firebaseAuth.getCurrentUser()!=null){
                    Intent intent = new Intent(MainActivity.this, MathQuiz.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BiologyDictionary.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.ratingId) {
            Intent intent = new Intent(getApplicationContext(), RatingActivity.class);
            startActivity(intent);
        }

        else if (item.getItemId() == R.id.feedbackId) {
            Intent intent = new Intent(getApplicationContext(), FeedbackActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
