package com.camilordgz.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizScreen extends AppCompatActivity {

    int quizType, num1, num2, correctAnswer, userAnswer, quizScore, wrongAnswers;

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
                subtractionQuiz(difficulty);
                break;
            case 3:
                Log.i("WARNING", "Initializing Multiplication Quiz with Difficulty Level" + difficulty);
                multiplicationQuiz(difficulty);
                break;
            case 4:
                Log.i("WARNING", "Initializing Division Quiz with Difficulty Level" + difficulty);
                divisionQuiz(difficulty);
                break;
        }
    }

    public void createQuestion(int difficulty, int quizType) {
        Random rand = new Random();

        switch (quizType) {
            //Addition
            case 1:
                switch (difficulty) {
                    case 1:
                        num1 = rand.nextInt(20);
                        num2 = rand.nextInt(20);
                        question.setText(num1 + " + " + num2);
                        break;
                    case 2:
                        num1 = rand.nextInt(50);
                        num2 = rand.nextInt(50);
                        question.setText(num1 + " + " + num2);
                        break;
                    case 3:
                        num1 = rand.nextInt(100);
                        num2 = rand.nextInt(100);
                        question.setText(num1 + " + " + num2);
                        break;
                }
                break;
            case 2:
                switch (difficulty) {
                    case 1:
                        num1 = rand.nextInt(20);
                        num2 = rand.nextInt(20);
                        question.setText(num1 + " - " + num2);
                        break;
                    case 2:
                        num1 = rand.nextInt(50);
                        num2 = rand.nextInt(50);
                        question.setText(num1 + " - " + num2);
                        break;
                    case 3:
                        num1 = rand.nextInt(100);
                        num2 = rand.nextInt(100);
                        question.setText(num1 + " - " + num2);
                        break;
                }
                break;

            //Multiplication
            case 3:
                switch (difficulty) {
                    case 1:
                        num1 = rand.nextInt(5);
                        num2 = rand.nextInt(5);
                        question.setText(num1 + " × " + num2);
                        break;
                    case 2:
                        num1 = rand.nextInt(5);
                        num2 = rand.nextInt(20);
                        question.setText(num1 + " × " + num2);
                        break;
                    case 3:
                        num1 = rand.nextInt(20);
                        num2 = rand.nextInt(20);
                        question.setText(num1 + " × " + num2);
                        break;
                }
                break;

            //Division
            case 4:
                switch (difficulty) {
                    case 1:
                        num1 = rand.nextInt(5);
                        num2 = rand.nextInt(5);
                        question.setText(num1 + " ÷ " + num2);
                        break;
                    case 2:
                        num1 = rand.nextInt(20);
                        num2 = rand.nextInt(5);
                        question.setText(num1 + " ÷ " + num2);
                        break;
                    case 3:
                        num1 = rand.nextInt(200);
                        num2 = rand.nextInt(10);
                        question.setText(num1 + " ÷ " + num2);
                        break;
                }
                break;
        }
    }

    public void checkAnswer(int num1, int num2, int quizType) {
        float correctAnswer = 0;
        switch (quizType) {
            case 1:
                correctAnswer = num1 + num2;
                break;
            case 2:
                correctAnswer = num1 - num2;
                break;
            case 3:
                correctAnswer = num1 * num2;
                break;
            case 4:
                correctAnswer = (float) num1 / num2;
                break;
        }

        if (!userInput.getText().toString().isEmpty()) {
            userAnswer = Integer.parseInt(userInput.getText().toString());
            if (userAnswer == correctAnswer) {
                quizScore++;
                questionNum.setText(quizScore + "/10");
                Toast.makeText(QuizScreen.this, "ANSWER IS CORRECT", Toast.LENGTH_SHORT).show();
                switch (quizType) {
                    case 1:
                        additionQuiz(difficulty);
                        break;
                    case 2:
                        subtractionQuiz(difficulty);
                        break;
                    case 3:
                        multiplicationQuiz(difficulty);
                        break;
                    case 4:
                        divisionQuiz(difficulty);
                        break;
                }
                userInput.setText("");
            } else {
                Toast.makeText(QuizScreen.this, "ANSWER IS INCORRECT", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(QuizScreen.this, "INPUT IS EMPTY", Toast.LENGTH_SHORT).show();
        }
    }

    public void vibratePhone() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 2000 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(2000);
        }
    }

    public void additionQuiz(int difficulty) {
        if (quizScore == 10) {
            Toast.makeText(QuizScreen.this, "YOU'VE FINISHED THE TEST", Toast.LENGTH_SHORT).show();
            vibratePhone();
        }

        createQuestion(difficulty, 1);

        sendUserInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(num1, num2, 1);
            }
        });
    }

    public void subtractionQuiz(int difficulty) {


        if (quizScore == 10) {
            Toast.makeText(QuizScreen.this, "YOU'VE FINISHED THE TEST", Toast.LENGTH_SHORT).show();
            vibratePhone();
        }

        createQuestion(difficulty, 2);

        sendUserInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(num1, num2, 2);
            }
        });
    }

    public void multiplicationQuiz(int difficulty) {


        if (quizScore == 10) {
            Toast.makeText(QuizScreen.this, "YOU'VE FINISHED THE TEST", Toast.LENGTH_SHORT).show();
            vibratePhone();
        }

        createQuestion(difficulty, 3);

        sendUserInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(num1, num2, 3);
            }
        });
    }

    public void divisionQuiz(int difficulty) {


        if (quizScore == 10) {
            Toast.makeText(QuizScreen.this, "YOU'VE FINISHED THE TEST", Toast.LENGTH_SHORT).show();
            vibratePhone();
        }

        createQuestion(difficulty, 4);

        sendUserInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(num1, num2, 4);
            }
        });
    }
}