package com.camilordgz.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizScreen extends AppCompatActivity {

    int quizType, num1, num2, correctAnswer, userAnswer, quizScore;

    int difficulty;
    TextView quizTypeText, question, questionNum;
    EditText userInput;
    Button sendUserInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_screen);

        quizTypeText = findViewById(R.id.quizTypeText);
        question = findViewById(R.id.question);
        userInput = findViewById(R.id.userInput);
        sendUserInput = findViewById(R.id.sendUserInput);
        questionNum = findViewById(R.id.questionNum);

        difficulty = getIntent().getIntExtra("difficulty", 0);
        quizType = getIntent().getIntExtra("quizType", 0);


        switch (quizType) {
            case 1:
                Log.i("WARNING", "Initializing Addition Quiz with Difficulty Level" + difficulty);
                additionQuiz(difficulty);
                break;
            case 2:
                Log.i("WARNING", "Initializing Subtraction Quiz with Difficulty Level" + difficulty);

                break;
            case 3:
                Log.i("WARNING", "Initializing Multiplication Quiz with Difficulty Level" + difficulty);

                break;
            case 4:
                Log.i("WARNING", "Initializing Division Quiz with Difficulty Level" + difficulty);

                break;
        }
    }

    public void additionQuiz(int difficulty) {
        Random rand = new Random();

        switch (difficulty) {
            case 1:
                num1 = rand.nextInt(20);
                num2 = rand.nextInt(20);
                correctAnswer = num1 + num2;
                question.setText(num1 + " + " + num2);

                if (!userInput.getText().toString().isEmpty()) {
                    userAnswer = Integer.parseInt(userInput.getText().toString());
                    sendUserInput.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (userAnswer == correctAnswer) {
                                quizScore++;
                                questionNum.setText(quizScore + "/10");
                            } else {
                                Toast.makeText(QuizScreen.this, "INCORRECT ANSWER", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            case 2:
                num1 = rand.nextInt(49);
                num2 = rand.nextInt(49);
                question.setText(num1 + " + " + num2);

                sendUserInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer();
                    }
                });


                break;
            case 3:
                num1 = rand.nextInt(99);
                num2 = rand.nextInt(99);
                question.setText(num1 + " + " + num2);

                if (!userInput.getText().toString().isEmpty()) {
                    userAnswer = Integer.parseInt(userInput.getText().toString());
                    sendUserInput.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (userAnswer == correctAnswer) {
                                quizScore++;
                                questionNum.setText(quizScore + "/10");
                            } else {
                                Toast.makeText(QuizScreen.this, "INCORRECT ANSWER", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;

            default:
                num1 = rand.nextInt(20);
                num2 = rand.nextInt(20);
                correctAnswer = num1 + num2;
                question.setText(num1 + " + " + num2);

                sendUserInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!userInput.getText().toString().isEmpty()) {
                            userAnswer = Integer.parseInt(userInput.getText().toString());
                            if (userAnswer == correctAnswer) {
                                quizScore++;
                                questionNum.setText(quizScore + "/10");
                                Toast.makeText(QuizScreen.this, "ANSWER IS CORRECT", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(QuizScreen.this, "INPUT IS EMPTY", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }

    public void checkAnswer() {
        if (!userInput.getText().toString().isEmpty()) {
            userAnswer = Integer.parseInt(userInput.getText().toString());
            if (userAnswer == correctAnswer) {
                quizScore++;
                questionNum.setText(quizScore + "/10");
                Toast.makeText(QuizScreen.this, "ANSWER IS CORRECT", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(QuizScreen.this, "INPUT IS EMPTY", Toast.LENGTH_SHORT).show();
        }
    }
}