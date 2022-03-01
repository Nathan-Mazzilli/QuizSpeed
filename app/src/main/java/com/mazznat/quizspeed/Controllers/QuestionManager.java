package com.mazznat.quizspeed.Controllers;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mazznat.quizspeed.Models.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class QuestionManager {
    private ArrayList<Question> questionList = new ArrayList<>();

    public QuestionManager() {
        initQuestionList(questionList);
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

    private void initQuestionList(ArrayList<Question> questionList){
        questionList.add(new Question("La capitale de l'Australie est Sydney",0));
        questionList.add(new Question("Un octet vaut 7 bits",0));
        questionList.add(new Question("Le phénomène à l'origine de l'univers est le Big Bong",0));
        questionList.add(new Question("Il n'y a pas d'oxygène dans l'espace",1));
        questionList.add(new Question("Pythagore est grecque",1));
        questionList.add(new Question("Jules César est mort aux ides de Mars",1));
    }


}
