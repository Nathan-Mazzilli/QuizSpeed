package com.mazznat.quizspeed.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mazznat.quizspeed.Models.Question;
import com.mazznat.quizspeed.Models.SpeedQuizSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionManager {
    private List<Question> questionList;


    public List<Question> getQuestionList(){
        return questionList;
    }

    public Question getRandomQuestion(List<Question> questionList){
        int randomIndex = getQuestionIndex(questionList);
        Question question = questionList.get(randomIndex);

        questionList.remove(randomIndex);

        return question;

    }
    private int getQuestionIndex(List<Question> questionList){
        Random rand = new Random();
        return rand.nextInt(questionList.size());
    }

    private void initQuestionList(List<Question> questionList){
        questionList.add(new Question("La capitale de l'Australie est Sydney",0));
        questionList.add(new Question("Un octet vaut 7 bits",0));
        questionList.add(new Question("Le phénomène à l'origine de l'univers est le Big Bong",0));
        questionList.add(new Question("Il n'y a pas d'oxygène dans l'espace",1));
        questionList.add(new Question("Pythagore est grecque",1));
        questionList.add(new Question("Jules César est mort aux ides de Mars",1));
    }


}
