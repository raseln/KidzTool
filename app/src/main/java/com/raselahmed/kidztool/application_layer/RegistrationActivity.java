package com.raselahmed.kidztool.application_layer;

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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.raselahmed.kidztool.R;
import com.raselahmed.kidztool.data_access.DbHelper;
import com.raselahmed.kidztool.data_access.SpHandler;
import com.raselahmed.kidztool.models.User;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etEmail,etPassword, etName, etInstitution;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressBar =new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etName = findViewById(R.id.etName);
        etInstitution = findViewById(R.id.etInstitution);
        Button btnReg = findViewById(R.id.btnRegister);
        TextView tvLink = findViewById(R.id.tvLoginLink);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private void registerUser() {
        email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_SHORT).show();
            etEmail.setError("Enter valid e-mail");
            etEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
            etPassword.setError("Enter password");
            etPassword.requestFocus();
            return;
        }

        progressBar.setTitle("Registering user...");
        progressBar.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            SpHandler spHandler = new SpHandler(RegistrationActivity.this);
                            spHandler.saveStatus();
                            //Toast.makeText(getApplicationContext(), "Registration successful.", Toast.LENGTH_SHORT).show();
                            //FirebaseUser fUser = firebaseAuth.getCurrentUser();
                            //User user = new User(fUser.getUid(), "name", email, "institution");
                            //saveData(user);
                            saveUserData();
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration failed.",
                                    Toast.LENGTH_SHORT).show();
                            //Log.e("Error", task.toString());
                        }

                        progressBar.dismiss();
                    }
                });
    }

    private void saveUserData(){
        DatabaseReference mReference = FirebaseDatabase.getInstance().getReference("users");
        String userId = firebaseAuth.getCurrentUser().getUid();
        final User user = new User(userId, etName.getText().toString().trim(), email,
                etInstitution.getText().toString().trim());
        mReference.child(userId).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null){
                    saveData(user);
                    Toast.makeText(RegistrationActivity.this, "User data saved" +
                            " successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        progressBar.dismiss();
    }

    private void saveData(User user){
        if (DbHelper.getInstance(getApplicationContext()).insertData(user)){
            Toast.makeText(getApplicationContext(), "Data saved successfully.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Failed to save data to local", Toast.LENGTH_SHORT).show();
        }
        finish();
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
