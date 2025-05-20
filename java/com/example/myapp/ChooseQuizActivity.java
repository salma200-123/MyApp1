package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseQuizActivity extends AppCompatActivity {

    Button javaBtn, cBtn, pythonBtn, htmlBtn, cssBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quiz);

        javaBtn = findViewById(R.id.btn_java);
        cBtn = findViewById(R.id.btn_c);
        pythonBtn = findViewById(R.id.btn_python);
        htmlBtn = findViewById(R.id.btn_html);
        cssBtn = findViewById(R.id.btn_css);

        javaBtn.setOnClickListener(v -> startActivity(new Intent(this, JavaQuizActivity.class)));
        cBtn.setOnClickListener(v -> startActivity(new Intent(this, CQuizActivity.class)));
        pythonBtn.setOnClickListener(v -> startActivity(new Intent(this, PythonQuizActivity.class)));
        htmlBtn.setOnClickListener(v -> startActivity(new Intent(this, HtmlQuizActivity.class)));
        cssBtn.setOnClickListener(v -> startActivity(new Intent(this, CssQuizActivity.class)));
    }
}
