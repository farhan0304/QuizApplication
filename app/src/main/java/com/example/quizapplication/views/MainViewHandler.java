package com.example.quizapplication.views;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.quizapplication.R;
import com.example.quizapplication.SecondActivity;
import com.example.quizapplication.databinding.ActivityMainBinding;
import com.example.quizapplication.model.Question;
import com.example.quizapplication.model.QuestionList;

public class MainViewHandler {
    private Context context;
    private ActivityMainBinding activityMainBinding;

    private int total;
    private int score = 0;

    private int currQuestion = 0;

    private QuestionList questions;


    public MainViewHandler(){

    }


    public MainViewHandler(Context context, ActivityMainBinding activityMainBinding,QuestionList questions) {
        this.context = context;
        this.activityMainBinding = activityMainBinding;
        this.questions = questions;
        total = questions.size();
        currQuestion = 0;
        score = 0;
    }

    public int getTotal() {
        return total;
    }

    public int getScore() {
        return score;
    }

    public void nextQuestionClick(View view){
        String btnText = activityMainBinding.btn.getText().toString();
        if(!btnText.equals("Submit")){
            displayNextQuestion();
        }else{
            displayFinalScore();
        }
    }

    public void displayFirstQuestion(){
        if(total==1){
            activityMainBinding.btn.setText("Submit");
        }
        Question q1 = questions.get(0);
        activityMainBinding.setQues(q1);

    }
    public boolean checkAnswer(){
        int id = activityMainBinding.radioGroup.getCheckedRadioButtonId();
        if(id == -1){
            Toast.makeText(context, "One Option must be selected", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {

            RadioButton radioButton = activityMainBinding.radioGroup.findViewById(id);
            String userAnswer = radioButton.getText().toString();
            String correctAnswer = questions.get(currQuestion).getCorrectOption();
            if (userAnswer.equals(correctAnswer)) {
                radioButton.setBackgroundColor(ContextCompat.getColor(context, R.color.correctColor));
                score++;

            } else {

                radioButton.setBackgroundColor(ContextCompat.getColor(context, R.color.incorrectColor));
            }

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "Calculating Score", Toast.LENGTH_SHORT).show();
                }
            },400);
            return true;


        }
    }
    public void displayNextQuestion(){
        int id = activityMainBinding.radioGroup.getCheckedRadioButtonId();
        if(id == -1){
            Toast.makeText(context, "One Option must be selected", Toast.LENGTH_SHORT).show();
        }
        else{
            System.out.println("Id: "+id);
            RadioButton radioButton = activityMainBinding.radioGroup.findViewById(id);
            String userAnswer = radioButton.getText().toString();
            String correctAnswer = questions.get(currQuestion).getCorrectOption();
            if(userAnswer.equals(correctAnswer)){
                radioButton.setBackgroundColor(ContextCompat.getColor(context,R.color.correctColor));
                score++;

            }else{

                radioButton.setBackgroundColor(ContextCompat.getColor(context,R.color.incorrectColor));
            }
            currQuestion++;

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    radioButton.setBackgroundColor(ContextCompat.getColor(context,R.color.transparent));
                    activityMainBinding.radioGroup.clearCheck();
                    Question q1 = questions.get(currQuestion);
                    activityMainBinding.setQues(q1);
                    if(currQuestion+1==total){
                        activityMainBinding.btn.setText("Submit");
                    }

                }
            },400);
        }


    }
    public void displayFinalScore(){
        boolean isSelected = checkAnswer();
        if (isSelected){
            Intent intent = new Intent(context, SecondActivity.class);
            intent.putExtra("score",score);
            intent.putExtra("total",total);
            context.startActivity(intent);
        }


    }
}
