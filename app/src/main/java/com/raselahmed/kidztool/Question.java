package com.raselahmed.kidztool;

public class Question {

    private int qNo;
    private String question, option1, option2, option3, option4, answer;

    Question(int qNo, String question, String option1, String option2, String option3, String option4, String answer) {
        this.qNo = qNo;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    public int getqNo() {
        return qNo;
    }

    String getQuestion() {
        return question;
    }

    String getOption1() {
        return option1;
    }

    String getOption2() {
        return option2;
    }

    String getOption3() {
        return option3;
    }

    String getOption4() {
        return option4;
    }

    String getAnswer() {
        return answer;
    }
}
