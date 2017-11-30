package com.app.kien.allquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    private final int GERAL_DEFAUT = 0;
    private final int MATEMATICA = 1;
    private final int RACIOCINIO=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void startQuiz(View v) {
        // se tiver 0 é a primeira opção
        int quizCategory = GERAL_DEFAUT;

        switch (v.getId()) {
            case R.id.botao1:
                quizCategory = GERAL_DEFAUT;
                break;
            case R.id.botao2:
                quizCategory = MATEMATICA;
                break;
            case R.id.botao3:
                quizCategory=RACIOCINIO;
                break;
        }

        Intent intent = new Intent(getApplicationContext(),quizzActivity.class);
        intent.putExtra("QUIZ_CATEGORY",quizCategory);
        startActivity(intent);

    }
}
