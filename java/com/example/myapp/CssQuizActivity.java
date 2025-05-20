package com.example.myapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CssQuizActivity extends AppCompatActivity {
    private RadioGroup[] questionGroups;
    private int[] correctAnswers = {
            R.id.q1_option3, // color
            R.id.q2_option1, // font-size
            R.id.q3_option2, // margin
            R.id.q4_option3, // background-color
            R.id.q5_option2  // border
    };
    private String[] corrections = {
            "Q1: La bonne propriété est 'color' (couleur du texte)",
            "Q2: La bonne propriété est 'font-size' (taille de la police)",
            "Q3: La bonne propriété est 'margin' (marge autour d'un élément)",
            "Q4: La bonne propriété est 'background-color' (couleur de fond)",
            "Q5: La bonne propriété est 'border' (bordure)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_css_quiz);

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
