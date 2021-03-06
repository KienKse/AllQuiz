package com.app.kien.allquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        TextView resultLabel= findViewById(R.id.resultLabel);
        TextView totalScoreLabel= findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("CONTADOR_RESPOSTA_CORRETA",0);

        SharedPreferences settings= getSharedPreferences("allquiz", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore",0);
        totalScore+= score;
        resultLabel.setText(score + " / 5");
        totalScoreLabel.setText("Pontuação Total: " + totalScore);

        if(score == 5) {
            Toast.makeText(this,"Parabéns você acertou tudo",Toast.LENGTH_SHORT).show();
        }

        //atualiza o total do score
        SharedPreferences.Editor editor= settings.edit();
        editor.putInt("totalScore",totalScore);
        editor.commit();


    }


    public void returnTop(View v){
        finish();
    }

}
