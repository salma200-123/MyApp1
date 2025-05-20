package com.example.myapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class JavaQuizActivity extends AppCompatActivity {
    private RadioGroup[] questionGroups;
    private int[] correctAnswers = {
            R.id.q1_option3,  // int
            R.id.q2_option2,  // public
            R.id.q3_option1,  // new
            R.id.q4_option3,  // false
            R.id.q5_option2   // JVM
    };
    private String[] corrections = {
            "Q1: La bonne réponse est 'int'",
            "Q2: La bonne réponse est 'public'",
            "Q3: La bonne réponse est 'new'",
            "Q4: La bonne réponse est 'false'",
            "Q5: La bonne réponse est 'JVM'"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_quiz);

        questionGroups = new RadioGroup[]{
                findViewById(R.id.question1),
                findViewById(R.id.question2),
                findViewById(R.id.question3),
                findViewById(R.id.question4),
                findViewById(R.id.question5)
        };

        Button submitButton = findViewById(R.id.submit_button);
        TextView resultText = findViewById(R.id.result_text);

        submitButton.setOnClickListener(v -> {
            int score = 0;
            StringBuilder feedback = new StringBuilder();
            for (int i = 0; i < questionGroups.length; i++) {
                if (questionGroups[i].getCheckedRadioButtonId() == correctAnswers[i]) {
                    score++;
                } else {
                    feedback.append(corrections[i]).append("\n");
                }
            }
            resultText.setText("Votre score : " + score + "/5\n" + feedback);
        });
    }
}
