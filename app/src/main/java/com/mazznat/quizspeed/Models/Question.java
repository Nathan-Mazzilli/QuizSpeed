package com.mazznat.quizspeed.Models;


import android.database.Cursor;

public class Question {

    private String question;
    private int reponse;

    public Question(){};


    public Question(Cursor cursor){
        question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        reponse = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }


    public String getQuestion(){
        return question;
    }

    public int isReponse(){
        return reponse;
    }
}
