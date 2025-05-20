package com.example.myapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.result_text);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);
        ArrayList<String> corrections = getIntent().getStringArrayListExtra("corrections");

        StringBuilder result = new StringBuilder("Votre note : " + score + " / " + total + "\n\n");

        if (!corrections.isEmpty()) {
            result.append("Réponses incorrectes et corrections :\n");
            for (String correction : corrections) {
                result.append("\n").append(correction).append("\n");
            }
        } else {
            result.append("Toutes les réponses sont correctes ! Bravo !");
        }

        resultText.setText(result.toString());
    }
}
