package com.mazznat.quizspeed.Controllers;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mazznat.quizspeed.Models.Question;
import com.mazznat.quizspeed.Models.SpeedQuizSQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    private ArrayList<Question> questionList = new ArrayList<>();

    public QuestionManager(Context context)
    {
        questionList = initQuestionList(context);
    }

    public ArrayList<Question> getQuestionList(){

        return questionList;
    }

    public Question getRandomQuestion(ArrayList<Question> questionList){
        Random rand = new Random();
        int randomIndex = rand.nextInt(questionList.size());
        Question question = questionList.get(randomIndex);

        questionList.remove(randomIndex);

        return question;

    }
    public boolean isEmpty (ArrayList<Question>listeQuestion){
        return listeQuestion.isEmpty();
    }

    /**
     * Charge une liste de question depuis la DB.
     * @param context Le contexte de l'application pour passer la query
     * @return Une arraylist charger de Question
     */
    private ArrayList<Question> initQuestionList(Context context) {
        ArrayList<Question> listQuestion = new ArrayList<>();
        SpeedQuizSQLiteOpenHelper helper = new SpeedQuizSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true,"quiz",new String[]{"idQuiz","question","reponse"},null,null,null,null,"idquiz",null);

        while(cursor.moveToNext()){
            listQuestion.add(new Question(cursor));
        }
        cursor.close();
        db.close();

        return listQuestion;
    }


}
