package com.app.kien.allquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class quizzActivity extends AppCompatActivity {

    //Numero Total de Questoes -1
    private static final int CONT_QUIZ = 5;

    private TextView contador;
    private TextView questao;
    private ImageView logo;

    private Button respBt1;
    private Button respBt2;
    private Button respBt3;
    private Button respBt4;

    private String respostCerta;
    private int contQuiz;
    private int contadorRespCerta;

    ArrayList<ArrayList<String>> quiz = new ArrayList<>();

    //  {"Pergunta" , "Resposta Certa", "Escolha 1", "Escolha 2" , "Escolha 3"}
    String quizDados[][] = {
            //Gerais
            {"Que matemático e geógrafo grego calculou o perímetro da Terra em 240 a.C.?", "Eratóstenes", "Descartes", "Sócrates", "Sófocles"},
            {"Segundo o tratado assinado em 1971, que região do planeta ficou interdita a armas nucleares?", "Antártida", "África", "Sara", "Bahamas"},
            {"Por qual  nome se tornou mais conhecido o escritor norte-americano Samuel Langhorne Clemens?", "Mark Twain", "Paul Auster", "Paulo Coelho", "Nicolau Gogol"},
            {"Que expressão alemã significa “guerra-relâmpago”?", "Blitzkrieg", "Relâmpago Mcqueen", "Mittagessen", "Kleiner Vogel"},
            {"Como se chamou o Sporting Club de Portugal entre 1904 e 1906?", "Campo Grande Football Club", "Sporting de Portugal", "Não teve nome", "Leões da Estrela"},
            {"Em Portugal, a que cidadãos foi retirado o direito de voto em 1913?", "Analfabetos", "Mulheres", "Emigrantes", "Idosos"},
            {"Qual é o mais famoso detetive criado por Raymond Chandler?", "Philip Marlowe", "Hercule Poirot", "Sherlock Holmes", "Agatha Christie"},
            {"Em que atividade se destacou Le Corbusier?", "Arquitetura", "Cinema", "Teatro", "Literatura"},
            {"Que parque natural na região de Bragança foi criado em 1979?", "Montesinho", "Peneda-Gerês", "Alto Douro", "Arrábida"},
            {"Como se chama a personagem principal do filme Touro Enraivecido?", "Jake La Motta", "Sugar Ray Robinson", "Josef Stalin", "Muhamad Ali"}
    };

    String quizDados2[][] = {
            //Matemática
            {"Quanto é !0", "1", "0", "1,5", "-1"},
            {"Quanto é (3*3)*0+1?", "1", "9", "6", "0"},
            {"Quanto é 60 ao quadrado?", "3600", "300", "336", "3015"},
            {"Mariana tem 18 anos. Sua irmã mais velha Melissa tem o triplo de sua idade. Quantos anos tem melissa?", "54", "56", "42", "44"},
            {"O Termômetro subiu 6 graus, o que representa a metade da temperatura de antes. A quantos graus está agora?", "18 graus", "12 graus", "16 graus", "9 graus"},
            {"O Avicultor diz: (Se eu tivesse dois Patos a mais, o dobro desse número seria 100.) Quantos Patos tem ele?", "48 Patos", "54 Patos", "72 Patos", "60 Patos"},
            {"Zezinho tem 24 bolas. Dá 4 para Luizinho e ambos ficarão com quantidade igual. Quantas bolas tinhas Luizinho inicialmente?", "20", "14", "16", "18"},
            {"Pedrinho tem 6 bolas a mais do que Chico. Os dois juntos têm 54. Quanto tem cada um?", "Pedrinho tem 30, Chico 24", "Pedrinho tem 28, Chico 22", "Pedrinho tem 36, Chico 30", "Pedrinho tem 40, Chico 34"},
            {"Seis pessoas comem 6 biscoitos em seis minutos. Quantas pessoas comerão 80 biscoitos em 48 minutos?", "10 pessoas", "14 pessoas", "15 pessoas", "8 pessoas"},
            {"Perguntado pela idade, Pedro responde: (Daqui a 30 anos, terei três vezes a idade de agora.) Qual a idade de Pedro?", "15 anos", "13 anos", "18 anos", "14 anos"},
            {"A Mãe é três vezes mais velha que a filha. Juntas têm 48 anos. Qual é a idade de cada uma?", "36 e 12", "33 e 15", "30 e 18", "34 e 14"},
            {"Se estivessem na sala de aula 5 alunos mais, a metade deles seria 20 alunos. Quantos estão lá realmente?", "35", "32", "34", "37"}
    };
    String quizDados3[][] = {
            //  {"Pergunta" , "Resposta Certa", "Escolha 1", "Escolha 2" , "Escolha 3"}
            {"A sequência de palavras abaixo segue uma determinada regra:  " +
                    "Camiseta, acetona, macaco, abacaxi, mágico. " +
                    "Qual é a próxima palavra da seqüência? ","publicação","cavalo","azeite","maionese"},
            {"Coloque as Palavras na Ordem Correta:  Neném(1) - Velho(2) - Adolescente(3) - Adulto(4)","1 - 3 - 4 - 2","2 - 4 - 3 - 1","1 - 4 - 3 - 2","1 - 2 - 3 - 4"},
            {"Dentre os itens abaixo, qual aquele que pode ser considerado um intruso?","Vaca","Gato","Hiena","Lobo Guará"},
            {"Um dos elementos relacionados abaixo não está de acordo com os demais do grupo.","Maracujá","Manga","Ameixa","Abacate"},
            {"Amigo está para Inimigo assim como Alegria está para:","Tristeza","Felicidade","Risos","Sonho"},
            {"Cabeça está para Pé assim como Teto está para:","Chão","Porta","Parede","Telhado"},
            {"O professor Epaminondas, no primeiro dia de aula, apostou que, entre os alunos daquela classe," +
                    " pelo menos dois fariam aniversário no mesmo dia do mês." +
                    " O professor tinha certeza de que ganharia a aposta, pois naquela" +
                    " classe o número de alunos era maior ou igual a:","32","28","30","15"},
            {"O pai de Maria tem cinco filhas: Lalá, Lelé, Lili, Loló e quem é a quinta filha? ","Maria","Lalá","Lelé","Loló"},
            {"Quando se escreve a sucessão dos números naturais, de 1 a 1000, quantas vezes aparece o algarismo 2 como algarismos das unidades ?","100","1","58","22"},
            {"Quantas vezes podemos subtrair cinco de 25? ","1 Vez","5 Vezes","100 Vezes","2 Vezes"},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        contador = findViewById(R.id.contQuestao);
        questao = findViewById(R.id.questao);
        respBt1 = findViewById(R.id.bt1);
        respBt2 = findViewById(R.id.bt2);
        respBt3 = findViewById(R.id.bt3);
        respBt4 = findViewById(R.id.bt4);
        logo = findViewById(R.id.logo);

        logo.setImageResource(R.drawable.ic_allquiz);

        //Receber Tipo de questionario
        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);

        Log.v("Tipo_Categoria", quizCategory + "");

        switch (quizCategory) {
            case 0:
                preencher(quizDados);
                break;
            case 1:
                preencher(quizDados2);
                break;
            case 2:
                preencher(quizDados3);
                break;
        }

    }

    private void preencher(String[][] quizDados2) {
        for (int i = 0; i < quizDados2.length; i++) {
            // Preparando (alocando) o Array
            ArrayList<String> datArray2 = new ArrayList<>();
            datArray2.add(quizDados2[i][0]);    // Local
            datArray2.add(quizDados2[i][1]);    // Resposta Certa
            datArray2.add(quizDados2[i][2]);    // Escolha 1
            datArray2.add(quizDados2[i][3]);    // Escolha 2
            datArray2.add(quizDados2[i][4]);    // Escolha 3
            quiz.add(datArray2);
        }
        proximoQuiz();
    }

    public void proximoQuiz() {
        String showQuiz = "";
        if (contQuiz == 0) {
            contQuiz++;
        }
        showQuiz = contQuiz + "º";
        contador.setText(showQuiz);

        Random random = new Random();
        // Gerando um num do array pegando a pergunt
        int numRandom = random.nextInt(quiz.size());

        // Pegando a pergunta
        ArrayList<String> quizScreen = quiz.get(numRandom);

        //Resposta certa *Set*
        questao.setText(quizScreen.get(0));

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
        Button btResposta = findViewById(v.getId());
        String btText = btResposta.getText().toString();

        String alerta;
        if (btResposta.getText().equals(respostCerta)) {
            MediaPlayer mp = MediaPlayer.create(quizzActivity.this, R.raw.right);
            mediaPlayer(mp);
            alerta = "Correto!";
            contadorRespCerta++;
        } else {
            MediaPlayer mp = MediaPlayer.create(quizzActivity.this, R.raw.nope);
            mediaPlayer(mp);
            alerta = "Errado...";
        }

        // Criando um dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alerta);
        builder.setMessage("Resposta: " + respostCerta);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (contQuiz == CONT_QUIZ) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("CONTADOR_RESPOSTA_CORRETA", contadorRespCerta);
                    finish();
                    startActivity(intent);
                } else {
                    contQuiz++;
                    proximoQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void mediaPlayer(MediaPlayer mp) {
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        mp.start();
    }
}
