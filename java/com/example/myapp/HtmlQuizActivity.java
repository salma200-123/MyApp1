package com.example.myapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class HtmlQuizActivity extends AppCompatActivity {
    private RadioGroup[] questionGroups;
    private int[] correctAnswers = {
            R.id.q1_option1, // <html>
            R.id.q2_option3, // <a>
            R.id.q3_option2, // <head>
            R.id.q4_option2, // <img>
            R.id.q5_option1  // <title>
    };
    private String[] corrections = {
            "Q1: La bonne balise de base est <html>",
            "Q2: La balise pour un lien est <a>",
            "Q3: La balise qui contient les métadonnées est <head>",
            "Q4: La balise pour afficher une image est <img>",
            "Q5: La balise pour le titre de la page est <title>"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_quiz);

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
