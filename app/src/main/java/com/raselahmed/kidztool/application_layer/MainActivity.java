package com.raselahmed.kidztool.application_layer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.raselahmed.kidztool.R;

public class MainActivity extends AppCompatActivity {

    Button btnRegister,btnLogIn, btnProfile;
    LinearLayout layoutPhysics, layoutChemistry, layoutMath, layoutBiology;
    //User user = null;
    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = findViewById(R.id.RegisterActivity);
        layoutPhysics = findViewById(R.id.layoutPhysics);
        layoutChemistry = findViewById(R.id.layoutChemistry);
        layoutMath = findViewById(R.id.layoutMath);
        layoutBiology = findViewById(R.id.layoutBiology);
        btnLogIn = findViewById(R.id.LoginActivity);
        btnProfile = findViewById(R.id.ProfileActivity);

        //etEmail = (EditText) findViewById(R.id.etEmail);
        //etPassword = (EditText) findViewById(R.id.etPassword);
        //Button btnReg = (Button) findViewById(R.id.btnRegister);
        //TextView tvLink = findViewById(R.id.tvLoginLink);
        TextView textView = findViewById(R.id.loggedInUserEmailText);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        fUser = firebaseAuth.getCurrentUser();
        //user = DbHelper.getInstance(this).getUser();

        if (firebaseAuth.getCurrentUser() != null){
            btnRegister.setVisibility(View.GONE);
            btnLogIn.setVisibility(View.GONE);
            btnProfile.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView.setText("Hello, "+fUser.getEmail());
        }

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
                if (fUser != null) {
                    Intent intent = new Intent(MainActivity.this, Profile.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Please, Log In first.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        layoutPhysics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PhysicsActivity.class));
            }
        });

        layoutChemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChemistryActivity.class));
            }
        });

        layoutMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MathActivity.class));
            }
        });

        layoutBiology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BiologyDictionary.class));
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