package com.app.kien.allquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class quizzActivity extends AppCompatActivity {

    private TextView contador;
    private TextView questao;

    private Button respBt1;
    private Button respBt2;
    private Button respBt3;
    private Button respBt4;

    private String respostCerta;
    private int contQuiz;
    private int contadorRespCerta;

    ArrayList<ArrayList<String>> quiz = new ArrayList<>();

    String quizDados[][] = {
//            {"Local" , "Resposta Certa", "Escolha 1", "Escolha 2" , "Escolha 3"}
            {"Espanha","Madri","Cidade do Mexico","Jakarta","Havana"},
            {"Italia","Roma","Londres","Paris","Atenas"},
            {"Brasil","Brasilia","Bahia","Goias","Recife"},
//            {"","","","",""}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        contador = (TextView) findViewById(R.id.contQuestao);
        questao = (TextView) findViewById(R.id.questao);
        respBt1 = (Button) findViewById(R.id.bt1);
        respBt2 = (Button) findViewById(R.id.bt2);
        respBt3 = (Button) findViewById(R.id.bt3);
        respBt4 = (Button) findViewById(R.id.bt4);

        for (int i = 0; i < quizDados.length; i++) {


            // Preparando (alocando) o Array
            ArrayList<String> datArray = new ArrayList<>();
            datArray.add(quizDados[i][0]); // Local
            datArray.add(quizDados[i][1]); // Resposta Certa
            datArray.add(quizDados[i][2]); // Escolha 1
            datArray.add(quizDados[i][3]); // Escolha 2
            datArray.add(quizDados[i][4]); // Escolha 3

            // Coloando as coisas no array
            quiz.add(datArray);

        }
        proximoQuiz();
    }

    public void proximoQuiz() {
        contador.setText(contQuiz + "º");

        Random random = new Random();
        // Gerando um num do array pegando a pergunt
        int numRandom = random.nextInt(quiz.size());

        // Pegando a pergunta
        ArrayList<String> quizScreen = quiz.get(numRandom);

        //Resposta certa *Set*
        questao.setText(quizScreen.get(0));
        respostCerta = quizScreen.get(0);

        // Removendo o indice principal e embaralhando
        quizScreen.remove(0);
        Collections.shuffle(quizScreen);

        // Definindo respostas
        respBt1.setText(quizScreen.get(0));
        respBt2.setText(quizScreen.get(1));
        respBt3.setText(quizScreen.get(2));
        respBt4.setText(quizScreen.get(3));

        // Removendo quiz atual da lista *Duplicate Problem*
        quiz.remove(numRandom);

    }
}
