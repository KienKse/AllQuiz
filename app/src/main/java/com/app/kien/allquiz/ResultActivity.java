package com.app.kien.allquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        TextView resultLabel= findViewById(R.id.resultLabel);
        TextView totalScoreLabel= findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("CONTADOR_RESPOTA_CORRETA",0);

        SharedPreferences settings= getSharedPreferences("allquiz", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore",0);
        totalScore+= score;

        resultLabel.setText(score + " / 5");

        totalScoreLabel.setText("Pontuação Total: " + totalScore);

        //atualiza o total do score

        SharedPreferences.Editor editor= settings.edit();
        editor.putInt("totalScore",totalScore);
        editor.commit();

    }


    public void returnTop(View v){
        Intent i = new Intent(getApplicationContext(), StartActivity.class);
        startActivity(i);
    }

}
