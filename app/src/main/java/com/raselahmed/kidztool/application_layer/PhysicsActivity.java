package com.raselahmed.kidztool.application_layer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.raselahmed.kidztool.R;

public class PhysicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics);

        findViewById(R.id.btnQuizPhy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Quiz.class));
            }
        });

        PDFView pdfView = findViewById(R.id.physicsPdf);
        pdfView.fromAsset("physics.pdf").load();
    }
}
