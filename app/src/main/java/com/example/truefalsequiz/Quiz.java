package com.example.truefalsequiz;

import java.util.List;

public class Quiz {
    private int score;
    private int currentQuestion;
    private int display;
    private List<Question> questions;

    public Quiz(int score, int currentQuestion, int display, List<Question> questions) {
        this.score = score;
        this.currentQuestion = currentQuestion;
        this.questions = questions;
        this.display = display;

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public int getCurrentQuestionDisplay(){
        display++;
        return display;
    }

    public int getQuestNum(){return display - 1;}
    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void nextQuestion(){
        if(isThereAnotherQuestion()){
            currentQuestion++;
        }
    }

    public int getNextQuestion(){
        return currentQuestion++;
    }

    public boolean isThereAnotherQuestion() {
        if(currentQuestion < questions.size() - 1){
            return true;
        }
        else{
            return false;
        }

    }
}
