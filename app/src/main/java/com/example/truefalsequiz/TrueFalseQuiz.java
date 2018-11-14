package com.example.truefalsequiz;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TrueFalseQuiz extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MESSAGE = "finish";

    private TextView textViewQuestionNumber;
    private TextView textViewQuestion;
    private TextView textViewScore;
    private Button buttonTrue;
    private Button buttonFalse;
    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        wireWidgets();
        setListeners();
        InputStream JSONFileInputStream = getResources().openRawResource(R.raw.question);
        String sJSON = readTextFile(JSONFileInputStream);

        Gson gson = new Gson();
        // read your json file into an array of questions
        Question[] questions = gson.fromJson(sJSON, Question[].class);
        // convert your array to a list using the Arrays utility class
        List<Question> questionList = Arrays.asList(questions);
        Collections.shuffle(questionList);

        quiz = new Quiz(0, 0, 0, questionList);

        textViewQuestionNumber.setText(getString(R.string.quiz_QuizNumberQuestion) + quiz.getCurrentQuestionDisplay() + getString(R.string.quiz_outOfTen));
        textViewQuestion.setText(quiz.getQuestions().get(0).getQuestion());
        textViewScore.setText(quiz.getScore() + " points out of " + quiz.getQuestNum());

    }

    private void setListeners() {
        buttonFalse.setOnClickListener(this);
        buttonTrue.setOnClickListener(this);
    }

    private void wireWidgets() {
        textViewQuestionNumber = findViewById(R.id.textView_quiz_questionNumber);
        textViewQuestion = findViewById(R.id.textView_quiz_question);
        textViewScore = findViewById(R.id.textView_quiz_score);
        buttonTrue = findViewById(R.id.button_quiz_true);
        buttonFalse = findViewById(R.id.button_quiz_false);
    }



    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_quiz_false:
                if(quiz.getQuestions().get(quiz.getCurrentQuestion()).isAnswer() == false){
                    Toast.makeText(this, R.string.quiz_correct, Toast.LENGTH_SHORT).show();
                    quiz.setScore(quiz.getScore() + 1);
                }
                else
                {
                    Toast.makeText(this, R.string.quiz_wrong, Toast.LENGTH_SHORT).show();
                }
                if(quiz.isThereAnotherQuestion()) {
                    quiz.nextQuestion();
                    textViewQuestionNumber.setText(getString(R.string.quiz_QuizNumberQuestion) + quiz.getCurrentQuestionDisplay() + getString(R.string.quiz_outOfTen));
                    textViewQuestion.setText(quiz.getQuestions().get(quiz.getCurrentQuestion()).getQuestion());
                    textViewScore.setText(quiz.getScore() + " points out of " + quiz.getQuestNum());
                }
                else
                {
                    sendToEnd();
                    recreate();
                }
                break;
            case R.id.button_quiz_true:
                if(quiz.getQuestions().get(quiz.getCurrentQuestion()).isAnswer() == true){
                    Toast.makeText(this, getString(R.string.quiz_correct), Toast.LENGTH_SHORT).show();
                    quiz.setScore(quiz.getScore() + 1);
                }
                else
                {
                    Toast.makeText(this, getString(R.string.quiz_wrong), Toast.LENGTH_SHORT).show();
                }
                if(quiz.isThereAnotherQuestion()) {
                    quiz.nextQuestion();
                    textViewQuestionNumber.setText(getString(R.string.quiz_QuizNumberQuestion) + quiz.getCurrentQuestionDisplay() + getString(R.string.quiz_outOfTen));
                    textViewQuestion.setText(quiz.getQuestions().get(quiz.getCurrentQuestion()).getQuestion());
                    textViewScore.setText(quiz.getScore() + " points out of " + quiz.getQuestNum());
                }
                else
                {
                    sendToEnd();
                }
                break;
        }
    }

    private void sendToEnd() {
        Intent intentFinish = new Intent(TrueFalseQuiz.this, EndScreen.class);
        String score = quiz.getScore() + "";
        intentFinish.putExtra(EXTRA_MESSAGE, score);
        startActivity(intentFinish);
    }

}
