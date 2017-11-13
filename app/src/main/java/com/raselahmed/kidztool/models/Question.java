package com.raselahmed.kidztool.models;

public class Question {

    private int qNo;
    private String question, option1, option2, option3, option4, answer;
    private boolean answered;

    public Question(int qNo, String question, String option1, String option2, String option3,
                    String option4, String answer, boolean answered) {
        this.qNo = qNo;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.answered = answered;
    }

    public int getQNo() {
        return qNo;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
