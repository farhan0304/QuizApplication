package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizapplication.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding activitySecondBinding;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        activitySecondBinding = DataBindingUtil.setContentView(this,R.layout.activity_second);
        btn2 = activitySecondBinding.newbtn;

        Intent intent = getIntent();
        int score = intent.getIntExtra("score",0);
        int total = intent.getIntExtra("total",0);

        activitySecondBinding.score.setText(score+"/"+total);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewGame();
            }
        });


    }
    public void startNewGame(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}