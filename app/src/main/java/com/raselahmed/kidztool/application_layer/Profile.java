package com.raselahmed.kidztool.application_layer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.raselahmed.kidztool.R;
import com.raselahmed.kidztool.adapters.FavAdapter;
import com.raselahmed.kidztool.data_access.DbHelper;
import com.raselahmed.kidztool.models.BioDict;
import com.raselahmed.kidztool.models.User;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    TextView textVieweEmail, textViewName, textViewInst;
    Button button;
    FirebaseAuth firebaseAuth;
    ArrayList<BioDict> Data;
    User mUser = null;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    DbHelper dbHelper;

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

        textViewName = findViewById(R.id.tvProfile);
        textVieweEmail = findViewById(R.id.tvProfileEmail);
        textViewInst = findViewById(R.id.tvProfileInst);
        button = findViewById(R.id.btnLogout);
        dbHelper = DbHelper.getInstance(this);
        Data = dbHelper.getFavouriteOrderByGN();
        recyclerView = findViewById(R.id.favRecylerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        FavAdapter adapter = new FavAdapter(Data);
        recyclerView.setAdapter(adapter);

        mUser = dbHelper.getUser(user.getEmail());

        //if (mUser != null) {
        textViewName.setText("Hello " + user.getEmail());
        //textVieweEmail.setText("Email: " + mUser.getEmail());
        //textViewInst.setText("Institution: " + mUser.getInstitution());
//        }else {
//            textViewName.setText("Your data wasn't saved correcty.");
//            textViewName.setGravity(Gravity.CENTER);
//        }
        
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