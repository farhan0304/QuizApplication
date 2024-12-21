package com.example.quizapplication.quizapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static QuizService quizService = null;
    private static String BASE_URL = "https://quiz-api-kappa.vercel.app/";

    public static QuizService getQuizService(){

        if(quizService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            quizService = retrofit.create(QuizService.class);
        }

        return quizService;
    }
}
