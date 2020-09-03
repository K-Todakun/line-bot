package com.example.linebot;

public class PreExam {
    private int questionNumber;
    private String mondai;
    private String sentaku1;
    private String sentaku2;
    private String sentaku3;
    private String sentaku4;
    private int answer;
    private String kaisetu;

    public PreExam(int questionNumber, String mondai, String sentaku1, String sentaku2, String sentaku3, String sentaku4, int answer, String kaisetu) {
        this.questionNumber = questionNumber;
        this.mondai = mondai;
        this.sentaku1 = sentaku1;
        this.sentaku2 = sentaku2;
        this.sentaku3 = sentaku3;
        this.sentaku4 = sentaku4;
        this.answer = answer;
        this.kaisetu = kaisetu;
    }




    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getMondai() {
        return mondai;
    }

    public void setMondai(String mondai) {
        this.mondai = mondai;
    }

    public String getSentaku1() {
        return sentaku1;
    }

    public void setSentaku1(String sentaku1) {
        this.sentaku1 = sentaku1;
    }

    public String getSentaku2() {
        return sentaku2;
    }

    public void setSentaku2(String sentaku2) {
        this.sentaku2 = sentaku2;
    }

    public String getSentaku3() {
        return sentaku3;
    }

    public void setSentaku3(String sentaku3) {
        this.sentaku3 = sentaku3;
    }

    public String getSentaku4() {
        return sentaku4;
    }

    public void setSentaku4(String sentaku4) {
        this.sentaku4 = sentaku4;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getKaisetu() {
        return kaisetu;
    }

    public void setKaisetu(String kaisetu) {
        this.kaisetu = kaisetu;
    }
}
