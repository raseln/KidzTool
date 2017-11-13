package com.raselahmed.kidztool.application_layer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.raselahmed.kidztool.R;

public class MathActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        findViewById(R.id.btnQuizMath).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Quiz.class));
            }
        });
        pdfView = findViewById(R.id.mathPdf);
        pdfView.fromAsset("math.pdf").load();
    }
}
