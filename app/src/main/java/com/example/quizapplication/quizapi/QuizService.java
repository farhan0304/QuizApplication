package com.example.quizapplication.quizapi;

import com.example.quizapplication.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizService {

    @GET("quiz/getquestion")
    Call<QuestionList> getAllQuestion();

}
