package com.raselahmed.kidztool.application_layer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.raselahmed.kidztool.R;

public class QuizResult extends AppCompatActivity {

    TextView tvFinalScore, tvTimeConsumed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        tvFinalScore = findViewById(R.id.finalScore);
        tvTimeConsumed = findViewById(R.id.totalTime);

        int finalScore = getIntent().getIntExtra("score", 0);
        int timeConsumed = getIntent().getIntExtra("timeConsumed", 0);

        int min = timeConsumed / 60;
        int sec = timeConsumed % 60;
        sec++;

        String fScore = "Final score is: " + finalScore;
        String time = "Total time consumed: " + min + " min(s) " + sec + " sec(s)";
        tvFinalScore.setText(fScore);
        tvTimeConsumed.setText(time);
    }
}
