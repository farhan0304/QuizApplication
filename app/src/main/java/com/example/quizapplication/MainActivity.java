package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


import com.example.quizapplication.databinding.ActivityMainBinding;

import com.example.quizapplication.model.QuestionList;
import com.example.quizapplication.quizapi.MyViewModel;
import com.example.quizapplication.views.MainViewHandler;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    MyViewModel myViewModel;

    QuestionList questions = new QuestionList();

    MainViewHandler mainViewHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModel.getQuestionData().observe(this, new Observer<QuestionList>() {
            @Override
            public void onChanged(QuestionList questionsBank) {
                questions = questionsBank;
                fetchData();

            }
        });

    }

    public void fetchData(){
        mainViewHandler = new MainViewHandler(this,activityMainBinding,questions);
        activityMainBinding.setHandler(mainViewHandler);
        mainViewHandler.displayFirstQuestion();
    }




}