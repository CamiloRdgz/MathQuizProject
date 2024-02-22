package com.camilordgz.mathquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

public class MainMenu extends AppCompatActivity {

    int quizDifficulty;
    Slider difficulty;
    TextView difficulty_text;
    Button addition_quiz, subtraction_quiz, multiplication_quiz, division_quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        difficulty = (Slider) findViewById(R.id.difficulty);
        difficulty_text = (TextView) findViewById(R.id.difficulty_text);
        addition_quiz = (Button) findViewById(R.id.menuAddition);
        subtraction_quiz = (Button) findViewById(R.id.menuSubtraction);
        multiplication_quiz = (Button) findViewById(R.id.menuMultiplication);
        division_quiz = (Button) findViewById(R.id.menuDivision);

        difficulty.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                quizDifficulty = Math.round(value);
                difficulty_text.setText("Quiz Difficulty: Level " + String.format("%.0f", value));
            }
        });

        addition_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(1);
            }
        });

        subtraction_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(2);
            }
        });

        multiplication_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(3);
            }
        });

        division_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(4);
            }
        });
    }

    public void startQuiz(int quizType) {

        Intent switchToQuiz = new Intent(MainMenu.this, QuizScreen.class);
        switchToQuiz.removeExtra("difficulty");
        switchToQuiz.removeExtra("quizType");
        switchToQuiz.putExtra("difficulty", quizDifficulty);

        switch (quizType) {
            case 1:
                Log.i("Quiz-Info", "Selected Addition Quiz at difficulty: " + quizDifficulty);
                switchToQuiz.putExtra("difficulty", quizDifficulty);
                switchToQuiz.putExtra("quizType", 1);
                startActivity(switchToQuiz);
                break;
            case 2:
                Log.i("Quiz-Info", "Selected Subtraction Quiz at difficulty: " + quizDifficulty);
                switchToQuiz.putExtra("difficulty", quizDifficulty);
                switchToQuiz.putExtra("quizType", 2);
                startActivity(switchToQuiz);
                break;
            case 3:
                Log.i("Quiz-Info", "Selected Multiplication Quiz at difficulty: " + quizDifficulty);
                switchToQuiz.putExtra("difficulty", quizDifficulty);
                switchToQuiz.putExtra("quizType", 3);
                startActivity(switchToQuiz);
                break;
            case 4:
                Log.i("Quiz-Info", "Selected Division Quiz at difficulty: " + quizDifficulty);
                switchToQuiz.putExtra("difficulty", quizDifficulty);
                switchToQuiz.putExtra("quizType", 4);
                startActivity(switchToQuiz);
                break;
        }


    }
}