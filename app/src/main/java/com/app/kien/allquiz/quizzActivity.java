package com.app.kien.allquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class quizzActivity extends AppCompatActivity {

    //Numero Total de Questoes -1
    private static final int CONT_QUIZ = 4;

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
            {"Qual a Capital da Espanha?","Madri","Cidade do Mexico","Jakarta","Havana"},
            {"Qual a Capital da Italia?","Roma","Londres","Paris","Atenas"},
            {"Qual a Capital do Brasil?","Brasilia","Bahia","Goias","Recife"},
            {"Qual a cor do cavalo branco de Napoleão?","Branco","Marron","Preto","Cinza"},
            {"Quanto é 7*7?","49","54","32","38"}
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


        //Receber Tipo de questionario
        int quizCategory= getIntent().getIntExtra("QUIZ_CATEGORY",0);

        Log.v("Tipo_Categoria",quizCategory + "");

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
        String showQuiz;
        if(contQuiz == 0) {
            showQuiz = contQuiz + 1 + "";
        } else {
            showQuiz = contQuiz + "º";
        }
        contador.setText(showQuiz);

        Random random = new Random();
        // Gerando um num do array pegando a pergunt
        int numRandom = random.nextInt(quiz.size());

        // Pegando a pergunta
        ArrayList<String> quizScreen = quiz.get(numRandom);

        //Resposta certa *Set*
        questao.setText(quizScreen.get(0));

        // indice errado na fonte - CORRETO = 1 - FONTE = 0
        respostCerta = quizScreen.get(1);

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

    public void checarRespostas(View v) {
        Button btResposta = (Button) findViewById(v.getId());
        String btText = btResposta.getText().toString();

        String alerta;
        if (btResposta.getText().equals(respostCerta)) {
            // Correto
            alerta = "Correto!";
            contadorRespCerta++;
        } else {
            // Errado
            alerta = "Errado...";
        }

        // Criando um dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alerta);
        builder.setMessage("Resposta: " + respostCerta);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(contQuiz == CONT_QUIZ) {
                    // Mostrar
                    //SEM FONTE INICIAL
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("CONTADOR_RESPOTA_CORRETA",contadorRespCerta);
                    startActivity(intent);
                } else {
                    contQuiz++;
                    // VAI DAR ERRO POIS NÃO HÁ PROX
                    proximoQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
