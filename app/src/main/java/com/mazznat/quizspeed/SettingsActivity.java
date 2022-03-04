package com.mazznat.quizspeed;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;

public class SettingsActivity extends AppCompatActivity {
    private EditText ED_nombreQuestion;
    private Slider sliderQuestion;
    private Button BT_Valider;

    private void getID(){
        ED_nombreQuestion = findViewById(R.id.parametre_questions);
        sliderQuestion = findViewById(R.id.slider);
        BT_Valider = findViewById(R.id.settings_btnValider);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getID();
    }

}
