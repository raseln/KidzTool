package com.raselahmed.kidztool.application_layer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.raselahmed.kidztool.R;
import com.raselahmed.kidztool.data_access.DbHelper;
import com.raselahmed.kidztool.models.Question;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {

    private TextView tvQuestion, tvScore;
    private RadioGroup radioGroup;
    private RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    Button btnFinishExam, btnStarExam;
    Spinner spinner;
    private ArrayList<Question> questionList;
    DbHelper dbHelper;
    private int position = 0, score = 0;
    private String answer;
    TextView tvTimeLeft, tvNumberOfQues, tvFinishMessage;
    String[] numberOfQuestions = {"5", "10", "15", "20"};
    public int min, sec, mSec = 0, mSelfT = 0, counter = 0, numberOfQues = 0, answerCounter = 0, answerCounterCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        dbHelper = DbHelper.getInstance(this);

        tvTimeLeft = findViewById(R.id.timeLeft);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        tvFinishMessage = findViewById(R.id.quizFinishMessage);
        tvNumberOfQues = findViewById(R.id.numberOfQuestions);
        radioGroup = findViewById(R.id.radioGroup);
        rbOption1 = findViewById(R.id.rbFirstOption);
        rbOption2 = findViewById(R.id.rbSecondOption);
        rbOption3 = findViewById(R.id.rbThirdOption);
        rbOption4 = findViewById(R.id.rbFourthOption);
        final Button btnPrev = findViewById(R.id.btnPrev);
        final Button btnNext = findViewById(R.id.btnNext);
        btnFinishExam = findViewById(R.id.btnFinishExam);
        btnStarExam = findViewById(R.id.btnStartQuiz);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numberOfQuestions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnStarExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOfQues = Integer.parseInt(spinner.getSelectedItem().toString());
                answerCounterCheck = numberOfQues - 1;
                mSec = numberOfQues * 15 * 1000;
                mSelfT = mSec;

                btnFinishExam.setVisibility(View.VISIBLE);
                tvNumberOfQues.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                btnStarExam.setVisibility(View.GONE);
                radioGroup.setVisibility(View.VISIBLE);
                tvQuestion.setVisibility(View.VISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                tvFinishMessage.setVisibility(View.VISIBLE);

                new CountDownTimer(mSec, 1000) {

                    @Override
                    public void onTick(long l) {
                        counter++;
                        sec = mSelfT / 1000;
                        min = sec / 60;
                        sec = sec % 60;
                        String str = "Time left " + min + " : " + sec;
                        tvTimeLeft.setText(str);
                        mSelfT = mSelfT - 1000;
                    }

                    @Override
                    public void onFinish() {
                        finishQuiz();
                    }
                }.start();
            }
        });

        questionList = new ArrayList<>();
        questionList = dbHelper.getQuestion();
        setQuestion(position);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if (answerCounter<=numberOfQues) {
                if (rbOption1.isChecked() || rbOption2.isChecked() || rbOption3.isChecked() || rbOption4.isChecked()) {
                    String ans = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

                    if (ans.equalsIgnoreCase(answer)) {
                        Toast.makeText(Quiz.this, "Correct Answer!", Toast.LENGTH_SHORT).show();
                        score++;
                    } else {
                        Toast.makeText(Quiz.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                    }
                    questionList.get(position).setAnswered(true);
                    answerCounter++;
                    if (answerCounter == numberOfQues) {
                        btnNext.setVisibility(View.GONE);
                        btnPrev.setVisibility(View.GONE);
                        tvFinishMessage.setText(R.string.notify_user);
                    }
                    position++;
                    position = position % numberOfQues;
                    int k = 0;
                    if (answerCounter <= answerCounterCheck) {
                        while (k == 0) {
                            position = position % numberOfQues;
                            if (!questionList.get(position).isAnswered()) {
                                setQuestion(position);
                                k = 1;
                                //position++;
                            } else position++;
                        }
                    }
                } else {
                    int k = 0;
                    position++;
                    while (k == 0) {
                        position = position % numberOfQues;
                        if (!questionList.get(position).isAnswered()) {
                            setQuestion(position);
                            k = 1;
                            //position++;
                        } else position++;
                    }
                }
                //}
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if (position < 0)
                    position = numberOfQues - 1;
                int k = 0;
                while (k == 0) {
                    if (position < 0)
                        position = numberOfQues - 1;
                    if (!questionList.get(position).isAnswered()) {
                        setQuestion(position);
                        k = 1;
                    } else position--;
                }
            }
        });

        btnFinishExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishQuiz();
            }
        });
    }

    private void setQuestion(int pos) {
        rbOption1.setChecked(false);
        rbOption2.setChecked(false);
        rbOption3.setChecked(false);
        rbOption4.setChecked(false);
        answer = questionList.get(pos).getAnswer();
        String currentScore = "Your current score: " + score;
        tvScore.setText(currentScore);
        tvQuestion.setText(Html.fromHtml(questionList.get(pos).getQuestion()));
        rbOption1.setText(Html.fromHtml(questionList.get(pos).getOption1()));
        rbOption2.setText(Html.fromHtml(questionList.get(pos).getOption2()));
        rbOption3.setText(Html.fromHtml(questionList.get(pos).getOption3()));
        rbOption4.setText(Html.fromHtml(questionList.get(pos).getOption4()));
    }

    private void finishQuiz() {
        finish();
        Intent intent = new Intent(Quiz.this, QuizResult.class);
        intent.putExtra("score", score);
        intent.putExtra("timeConsumed", counter);
        startActivity(intent);
    }
}
