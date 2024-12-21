package com.example.quizapplication.quizapi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapplication.model.QuestionList;

public class MyViewModel extends ViewModel {

    private QuizRepository repository;

    public MyViewModel(){
        repository = new QuizRepository();
    }

    public LiveData<QuestionList> getQuestionData(){
        return repository.getQuizQuestion();
    }
}
