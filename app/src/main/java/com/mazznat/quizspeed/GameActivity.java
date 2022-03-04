package com.mazznat.quizspeed;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TintableImageSourceView;

import com.mazznat.quizspeed.Controllers.QuestionManager;
import com.mazznat.quizspeed.Models.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    Handler handler;
    Runnable questionRunnable=null;

    Question questionEnCours;
    private TextView TV_nomJoueur1;
    private TextView TV_nomJoueur2;
    private Button BT_menu;
    private Button BT_rejouer;
    private TextView TV_Question1;
    private TextView TV_Question2;
    private Button BT_Joueur1;
    private Button BT_Joueur2;
    private TextView TV_Score1;
    private TextView TV_Score2;
    int v=5;

    /**
     * Prend les ID des objets utiilisé
     */
    private void getId(){
        TV_nomJoueur1 = findViewById(R.id.nomJoueur1);
        TV_nomJoueur2 = findViewById(R.id.nomJoueur2);
        BT_menu = findViewById(R.id.buttonMenu);
        BT_rejouer = findViewById(R.id.buttonRejouer);
        TV_Question1 = findViewById(R.id.gagnantJoueur1);
        TV_Question2 = findViewById(R.id.gagnantJoueur2);
        BT_Joueur1 = findViewById(R.id.buttonJoueur1);
        BT_Joueur2 = findViewById(R.id.buttonJoueur2);
        TV_Score1 = findViewById(R.id.ScoreJoueur1);
        TV_Score2 = findViewById(R.id.ScoreJoueur2);

        BT_menu.setVisibility(View.INVISIBLE);
        BT_rejouer.setVisibility(View.INVISIBLE);

        BT_Joueur1.setEnabled(false);
        BT_Joueur2.setEnabled(false);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getId();
        QuestionManager questionManager = new QuestionManager(this);
        ArrayList<Question> ListeQuestion = questionManager.getQuestionList();

        Intent GameActivity = getIntent();
        String Joueur1 = GameActivity.getStringExtra("Joueur1");
        String Joueur2 = GameActivity.getStringExtra("Joueur2");


        TV_nomJoueur1.setText(Joueur1);
        TV_nomJoueur2.setText(Joueur2);

        //Decompte
        handler = new Handler();
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                TV_Question1.setText(String.valueOf(v));
                TV_Question2.setText(String.valueOf(v));
                if(v < 1){
                    handler.removeCallbacks(this);

                    TV_Question1.setText(R.string.go);
                    TV_Question2.setText(R.string.go);

                    // question
                     handler = new Handler();
                     questionRunnable = new Runnable() {
                         @Override
                         public void run() {
                             if (questionManager.isEmpty(questionManager.getQuestionList())) {
                                BT_menu.setVisibility(View.VISIBLE);
                                BT_rejouer.setVisibility(View.VISIBLE);
                                BT_Joueur1.setEnabled(false);
                                BT_Joueur2.setEnabled(false);

                                if(Integer.parseInt(String.valueOf(TV_Score1.getText())) > Integer.parseInt(String.valueOf(TV_Score2.getText()))){
                                    TV_Question1.setText(R.string.gagne);
                                    TV_Question2.setText(R.string.perdu);
                                }
                                else if(Integer.parseInt(String.valueOf(TV_Score2.getText())) > Integer.parseInt(String.valueOf(TV_Score1.getText()))){
                                    TV_Question2.setText(R.string.gagne);
                                    TV_Question1.setText(R.string.perdu);
                                }else {
                                    TV_Question1.setText(R.string.egalite);
                                    TV_Question2.setText(R.string.egalite);
                                }

                                handler.removeCallbacks(this);

                             } else {
                                 Question question = questionManager.getRandomQuestion(ListeQuestion);
                                 String texteQuestion = question.getQuestion();
                                 TV_Question1.setText(texteQuestion);
                                 TV_Question2.setText(texteQuestion);
                                 BT_Joueur1.setEnabled(true);
                                 BT_Joueur2.setEnabled(true);

                                 questionEnCours = question;

                                 handler.postDelayed(this, 2000);
                             }
                         }
                     };
                     handler.postDelayed(questionRunnable, 2000);
                }else{
                   v--;
                    handler.postDelayed(this,1000);
                }
            }
        };
        handler.postDelayed(questionRunnable,1000);


        BT_Joueur1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ScoreInt = Integer.parseInt(TV_Score1.getText().toString());
                int reponse = questionEnCours.isReponse();
                if(reponse == 1) {
                    ScoreInt++;
                }else
                    if(ScoreInt != 0){
                        ScoreInt--;
                    }

                TV_Score1.setText(String.valueOf(ScoreInt));
                BT_Joueur1.setEnabled(false);
                BT_Joueur2.setEnabled(false);


            }
        });

        BT_Joueur2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int ScoreInt = Integer.parseInt(TV_Score2.getText().toString());
                int reponse = questionEnCours.isReponse();
                if(reponse == 1) {
                    ScoreInt++;
                }else
                if(ScoreInt != 0){
                    ScoreInt--;
                }

                TV_Score2.setText(String.valueOf(ScoreInt));
                BT_Joueur1.setEnabled(false);
                BT_Joueur2.setEnabled(false);

            }
        });
        BT_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainActivity = new Intent(GameActivity.this, MainActivity.class);
                startActivity(MainActivity);
            }
        });

        BT_rejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameActivity = new Intent(GameActivity.this, GameActivity.class);
                gameActivity.putExtra("Joueur1", TV_nomJoueur1.getText().toString());
                gameActivity.putExtra("Joueur2", TV_nomJoueur2.getText().toString());
                startActivity(gameActivity);
            }
        });
    }
}


