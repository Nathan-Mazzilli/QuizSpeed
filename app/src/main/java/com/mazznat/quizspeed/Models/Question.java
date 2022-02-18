package com.mazznat.quizspeed.Models;

import android.database.Cursor;

public class Question {

    private String question;
    private int reponse;

    public Question(){};

    public Question(String question,int reponse){
        this.question = question;
        this.reponse = reponse;
    }

    public Question(Cursor cursor){
        question = cursor.getString(cursor.getColumnIndex("question"));
        reponse = cursor.getInt(cursor.getColumnIndex("reponse"));
    }

    public String getQuestion(){
        return question;
    }

    public int isReponse(){
        return reponse;
    }
}
