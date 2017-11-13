package com.raselahmed.kidztool.application_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.raselahmed.kidztool.R;

public class Profile extends AppCompatActivity {

    TextView textView;
    Button button;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Profile.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textView = findViewById(R.id.tvProfile);
        button = findViewById(R.id.btnLogout);

        textView.setText("Hello "+user.getEmail());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Profile.this, MainActivity.class));
            }
        });
    }
}