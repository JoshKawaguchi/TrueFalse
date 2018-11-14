package com.example.truefalsequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {

    private TextView textViewGameOver;
    private TextView textViewScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        wireWidgets();

        textViewGameOver.setText(R.string.score_gameOver);

        Intent receiveIntent = getIntent();
        String score = receiveIntent.getStringExtra(TrueFalseQuiz.EXTRA_MESSAGE);
        textViewScore.setText(getString(R.string.score_finalScore) + score + getString(R.string.score_finalScore2));
    }

    private void wireWidgets() {
        textViewGameOver = findViewById(R.id.textView_score_gameOver);
        textViewScore = findViewById(R.id.textView_score_score);

    }
}
