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

public class MainActivity extends AppCompatActivity {

    //private EditText etEmail, etPassword;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;
    Button btnRegister,btnRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = (Button) findViewById(R.id.RegisterActivity);
        btnRating = (Button) findViewById(R.id.rateBtn);
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
