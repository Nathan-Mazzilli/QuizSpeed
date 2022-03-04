package com.mazznat.quizspeed;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    private EditText ED_question;
    private Switch VraiFaux;
    private Button BT_Valider;

    private void getID(){
        ED_question = findViewById(R.id.question_question);
        VraiFaux = findViewById(R.id.switchQuestion);
        BT_Valider = findViewById(R.id.question_btnValider);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        getID();
    }

}
