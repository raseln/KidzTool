package com.raselahmed.kidztool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MathQuiz extends AppCompatActivity {

    private TextView tvQuestion, tvScore;
    private RadioGroup radioGroup;
    private RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    private ArrayList<Question> questionList;
    private int position = 0, score = 0;
    private String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_quiz);

        DbHelper dbHelper = new DbHelper(this);

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        tvScore = (TextView) findViewById(R.id.tvScore);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbOption1 = (RadioButton) findViewById(R.id.rbFirstOption);
        rbOption2 = (RadioButton) findViewById(R.id.rbSecondOption);
        rbOption3 = (RadioButton) findViewById(R.id.rbThirdOption);
        rbOption4 = (RadioButton) findViewById(R.id.rbFourthOption);
        Button btnPrev = (Button) findViewById(R.id.btnPrev);
        Button btnNext = (Button) findViewById(R.id.btnNext);
        questionList = new ArrayList<>();

        questionList = dbHelper.getQuestion();
        setQuestion(position);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                String ans = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId()))
                        .getText().toString();
                if (ans.equalsIgnoreCase(answer)){
                    Toast.makeText(MathQuiz.this, "Correct Answer!", Toast.LENGTH_SHORT).show();
                    score++;
                }else {
                    Toast.makeText(MathQuiz.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                }
                if (position == questionList.size())
                    position = 0;
                setQuestion(position);
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if (position < 0)
                    position = 0;
                setQuestion(position);
            }
        });
    }

    private void setQuestion(int pos){

        answer = questionList.get(pos).getAnswer();
        String currentScore = "Your current score: "+score;
        tvScore.setText(currentScore);
        tvQuestion.setText(questionList.get(pos).getQuestion());
        rbOption1.setText(questionList.get(pos).getOption1());
        rbOption2.setText(questionList.get(pos).getOption2());
        rbOption3.setText(questionList.get(pos).getOption3());
        rbOption4.setText(questionList.get(pos).getOption4());
    }
}
