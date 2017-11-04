package com.app.kien.allquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.btQuiz);

       b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callQuiz(view);
            }
    });
    }


    public void callQuiz(View v) {
        Intent quiz = new Intent(this,quizzActivity.class);
        startActivity(quiz);
    }

}
