package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class QuizActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup optionsGroup;
    private Button submitButton;

    private List<Question> questionList;
    private List<Integer> userAnswers = new ArrayList<>();
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String quizType = getIntent().getStringExtra("quizType");
        questionList = getQuestionsByType(quizType);

        questionText = findViewById(R.id.question_text);
        optionsGroup = findViewById(R.id.options_group);
        submitButton = findViewById(R.id.submit_button);

        questionList = getQuestions();
        displayQuestion(currentQuestionIndex);

        submitButton.setOnClickListener(v -> {
            int selectedOptionId = optionsGroup.getCheckedRadioButtonId();
            if (selectedOptionId != -1) {
                int selectedIndex = optionsGroup.indexOfChild(findViewById(selectedOptionId));
                userAnswers.add(selectedIndex);
                currentQuestionIndex++;
                if (currentQuestionIndex < questionList.size()) {
                    displayQuestion(currentQuestionIndex);
                } else {
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("score", calculateScore());
                    intent.putExtra("total", questionList.size());

                    ArrayList<String> corrections = new ArrayList<>();
                    for (int i = 0; i < questionList.size(); i++) {
                        if (userAnswers.get(i) != questionList.get(i).getCorrectAnswerIndex()) {
                            String correction = "Q" + (i + 1) + ": " + questionList.get(i).getQuestionText() +
                                    "\nTa réponse: " + questionList.get(i).getOptions()[userAnswers.get(i)] +
                                    "\nCorrection: " + questionList.get(i).getOptions()[questionList.get(i).getCorrectAnswerIndex()];
                            corrections.add(correction);
                        }
                    }
                    intent.putStringArrayListExtra("corrections", corrections);
                    startActivity(intent);
                    finish();
                }
            } else {
                Toast.makeText(this, "Veuillez sélectionner une réponse", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Question> getQuestionsByType(String type) {
        List<Question> list = new ArrayList<>();

        switch (type.toLowerCase()) {
            case "java":
                list.add(new Question("Java est ?", new String[]{"Un animal", "Un langage", "Un fruit", "Un pays"}, 1));
                list.add(new Question("Mot-clé pour hériter d'une classe ?", new String[]{"extends", "implements", "inherit", "include"}, 0));
                list.add(new Question("Type de retour de main() ?", new String[]{"int", "void", "String", "char"}, 1));
                list.add(new Question("Mot-clé pour créer un objet ?", new String[]{"class", "new", "make", "this"}, 1));
                list.add(new Question("Fichier de démarrage Java ?", new String[]{"Main.doc", "index.html", "Main.java", "Start.exe"}, 2));
                break;

            case "c":
                list.add(new Question("Langage C est ?", new String[]{"Bas niveau", "Haut niveau", "Script", "Web"}, 0));
                list.add(new Question("Quel symbole termine une instruction ?", new String[]{";", ":", ".", ","}, 0));
                list.add(new Question("Fonction principale ?", new String[]{"main()", "start()", "first()", "init()"}, 0));
                list.add(new Question("Type pour caractère ?", new String[]{"char", "string", "byte", "text"}, 0));
                list.add(new Question("Mot-clé pour déclaration variable ?", new String[]{"int", "let", "var", "define"}, 0));
                break;

            case "python":
                list.add(new Question("Extension Python ?", new String[]{".py", ".java", ".html", ".exe"}, 0));
                list.add(new Question("Mot-clé condition ?", new String[]{"if", "when", "condition", "cond"}, 0));
                list.add(new Question("Python est ?", new String[]{"Typé dynamiquement", "Statique", "Pas typé", "Binaire"}, 0));
                list.add(new Question("Type de boucle ?", new String[]{"for", "loop", "iterate", "circle"}, 0));
                list.add(new Question("Mot-clé fonction ?", new String[]{"def", "function", "fun", "void"}, 0));
                break;

            case "css":
                list.add(new Question("CSS signifie ?", new String[]{"Cascading Style Sheets", "Creative Style Sheet", "Computer Style Sheet", "Color Style Sheet"}, 0));
                list.add(new Question("Sélecteur d’ID ?", new String[]{"#", ".", "*", "@"}, 0));
                list.add(new Question("Unité de taille ?", new String[]{"px", "kg", "cm", "inch"}, 0));
                list.add(new Question("Position relative ?", new String[]{"relative", "static", "fixed", "absolute"}, 0));
                list.add(new Question("Couleur rouge en hex ?", new String[]{"#FF0000", "#00FF00", "#0000FF", "#FFFF00"}, 0));
                break;

            case "html":
                list.add(new Question("HTML signifie ?", new String[]{"HyperText Markup Language", "HighText Machine Language", "HyperTool Multi Language", "None"}, 0));
                list.add(new Question("Balise titre ?", new String[]{"<title>", "<head>", "<meta>", "<h1>"}, 0));
                list.add(new Question("Balise de lien ?", new String[]{"<a>", "<link>", "<href>", "<url>"}, 0));
                list.add(new Question("Balise d’image ?", new String[]{"<img>", "<image>", "<pic>", "<photo>"}, 0));
                list.add(new Question("Attribut de source ?", new String[]{"src", "href", "alt", "url"}, 0));
                break;
        }

        return list;
    }


    private int calculateScore() {
        int score = 0;
        for (int i = 0; i < questionList.size(); i++) {
            if (userAnswers.get(i) == questionList.get(i).getCorrectAnswerIndex()) {
                score++;
            }
        }
        return score;
    }

    private void displayQuestion(int index) {
        Question q = questionList.get(index);
        questionText.setText(q.getQuestionText());
        optionsGroup.removeAllViews();
        for (String option : q.getOptions()) {
            RadioButton rb = new RadioButton(this);
            rb.setText(option);
            optionsGroup.addView(rb);
        }
    }

    private List<Question> getQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question("Java est ?", new String[]{"Un fremwork", "Un langage", "Un fruit", "Un pays"}, 1));
        list.add(new Question("Le mot-clé pour hériter d'une classe ?", new String[]{"extends", "implements", "inherit", "include"}, 0));
        list.add(new Question("Quel est le type de retour de main() ?", new String[]{"int", "void", "String", "char"}, 1));
        list.add(new Question("Quel mot-clé pour créer un objet ?", new String[]{"class", "new", "make", "this"}, 1));
        list.add(new Question("Quel est le bon fichier pour démarrer une application Java ?", new String[]{"Main.doc", "index.html", "Main.java", "Start.exe"}, 2));
        return list;
    }
}
