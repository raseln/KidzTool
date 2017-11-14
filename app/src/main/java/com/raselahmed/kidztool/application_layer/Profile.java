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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.raselahmed.kidztool.R;
import com.raselahmed.kidztool.adapters.FavAdapter;
import com.raselahmed.kidztool.data_access.DbHelper;
import com.raselahmed.kidztool.models.BioDict;
import com.raselahmed.kidztool.models.User;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    private TextView textViewEmail, textViewName, textViewInst;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mReference;
    private User mUser = null;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String email = getIntent().getStringExtra("email");
//        firebaseAuth = FirebaseAuth.getInstance();
//        if (firebaseAuth.getCurrentUser() == null){
//            finish();
//            startActivity(new Intent(this, LoginActivity.class));
//        }

        //FirebaseUser user = firebaseAuth.getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference();

        textViewName = findViewById(R.id.tvProfile);
        textViewEmail = findViewById(R.id.tvProfileEmail);
        textViewInst = findViewById(R.id.tvProfileInst);
        Button button = findViewById(R.id.btnLogout);
        dbHelper = DbHelper.getInstance(this);
        ArrayList<BioDict> data = dbHelper.getFavouriteOrderByGN();
        RecyclerView recyclerView = findViewById(R.id.favRecylerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        FavAdapter adapter = new FavAdapter(data);
        recyclerView.setAdapter(adapter);

        mUser = dbHelper.getUser(email);
        if (mUser == null){
            getUserData();
        }else {
            setProfile();
        }

        //if (mUser != null) {
        //textViewName.setText("Hello " + user.getEmail());
        //textViewEmail.setText("Email: " + mUser.getEmail());
        //textViewInst.setText("Institution: " + mUser.getInstitution());
//        }else {
//            textViewName.setText("Your sciNameList wasn't saved correcty.");
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

    public void getUserData(){
        mReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUser = dataSnapshot.getValue(User.class);
                if (mUser != null) {
                    dbHelper.insertData(mUser);
                    setProfile();
                }else
                    Toast.makeText(Profile.this, "Couldn't retrieve user data!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void setProfile(){
        textViewName.setText(mUser.getName());
        textViewEmail.setText(mUser.getEmail());
        textViewInst.setText(mUser.getInstitution());
    }
}