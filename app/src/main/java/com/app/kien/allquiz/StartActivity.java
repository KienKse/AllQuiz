package com.app.kien.allquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    public void startQuiz(View v) {
        // se tiver 0 é a primeira opção
        int quizCategory = 0;

        switch (v.getId()) {
            case R.id.botao2:
                quizCategory = 1;
                break;
            case R.id.botao3:
                quizCategory = 2;
                break;
            case R.id.botao4:
                quizCategory=3;
                break;
        }

        Intent intent = new Intent(getApplicationContext(),quizzActivity.class);
        intent.putExtra("QUIZ_CATEGORY",quizCategory);
        startActivity(intent);

    }
}
