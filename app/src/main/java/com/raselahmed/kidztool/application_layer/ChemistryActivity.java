package com.raselahmed.kidztool.application_layer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.raselahmed.kidztool.R;

public class ChemistryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemistry);

        findViewById(R.id.btnQuizChem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Quiz.class));
            }
        });

        PDFView pdfView = findViewById(R.id.chemPdf);
        pdfView.fromAsset("chemistry_bang.pdf").load();
    }
}
