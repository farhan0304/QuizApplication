package com.example.quizapplication.quizapi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quizapplication.model.Question;
import com.example.quizapplication.model.QuestionList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {


    private QuestionList questions = new QuestionList();
    private MutableLiveData<QuestionList> questionListLiveData = new MutableLiveData<>();


    public QuizRepository(){

    }

    public LiveData<QuestionList> getQuizQuestion(){
        QuizService quizService = RetrofitInstance.getQuizService();
        Call<QuestionList> responseData = quizService.getAllQuestion();
        responseData.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                questions = response.body();
                if(questions!=null || questions.size()!=0){
                    questionListLiveData.setValue(questions);
                }else{

                }

            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable throwable) {

            }
        });
        return questionListLiveData;
    }

}
