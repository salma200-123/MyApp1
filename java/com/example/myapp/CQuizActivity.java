package com.example.myapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CQuizActivity extends AppCompatActivity {
    private RadioGroup[] questionGroups;
    private int[] correctAnswers = {
            R.id.q1_option2, // int
            R.id.q2_option3, // printf
            R.id.q3_option1, // *
            R.id.q4_option2, // //
            R.id.q5_option1  // gcc
    };
    private String[] corrections = {
            "Q1: La bonne réponse est 'int'",
            "Q2: La bonne réponse est 'printf'",
            "Q3: La bonne réponse est '*' (opérateur de multiplication)",
            "Q4: La bonne réponse est '//' (commentaire sur une ligne)",
            "Q5: La bonne réponse est 'gcc' (compilateur C)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_quiz);

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
            StringBuilder wrongAnswers = new StringBuilder();

            for (int i = 0; i < 5; i++) {
                int selectedId = questionGroups[i].getCheckedRadioButtonId();
                if (selectedId == correctAnswers[i]) {
                    score++;
                } else {
                    wrongAnswers.append(corrections[i]).append("\n");
                }
            }

            resultText.setText("Votre note : " + score + "/5\n\n" + wrongAnswers);
        });
    }
}
