package com.example.remotion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends AppCompatActivity {
    ImageView mQuizImage;
    TextView mScoreTitle;
    private String mAnswer;
    private int mScore = 0;
    private int mQuizNum = 1;
    private int QuestionNum = 0;
    TextView mQuestionView;
    TextView mQuizNumView;
    private Questions mQuestions = new Questions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mQuestionView=findViewById(R.id.question_textview);
        mQuizNumView=findViewById(R.id.quiznum_textview);
        TextView mScoreTitle=findViewById(R.id.mScore);

        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        updateQuestion();

        Button submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //QUIZ
                if(mQuestions.getType(QuestionNum)=="radiobutton"){
                    if(mQuestions.getCorrectAnswers(QuestionNum).equals(mAnswer)){
                        mScore++;
                        displayToastCorrectAnswer();
                    }else{
                        displayToastWrongAnswer();
                    }
                }
                SystemClock.sleep(1000);
                if(QuestionNum==mQuestions.getLength()-1){

                    Intent intent_result = new Intent(PlayActivity.this, ResultActivity.class);
                    intent_result.putExtra("totalQuestions",mQuestions.getLength());
                    intent_result.putExtra("finalScore",mScore);
                    startActivity(intent_result);

                    QuestionNum=0;
                    mQuizNum=0;
                    mScore=0;
                }else{
                    QuestionNum++;
                    mQuizNum++;
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateQuestion();
                    }
                }, 1000);{

                }


                updateQuestion();
            }
        });

    }
    private void displayToastCorrectAnswer(){
        Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
        TextView mScoreTitle=findViewById(R.id.mScore);

    }

    private void displayToastWrongAnswer(){
        Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        LinearLayout answer_layout = findViewById(R.id.answers_layout);
        answer_layout.removeAllViews();
        mAnswer = "";
        mQuizNumView.setText(mQuizNum + "/" + String.valueOf(mQuestions.getLength()));
        mQuestionView.setText(mQuestions.getQuestions(QuestionNum));

        if (mQuestions.getType(QuestionNum) == "radiobutton") {
            showRadioButtonAnswer(QuestionNum);
        }
        showMainImage();
        // ScrollView sv = findViewById(R.id.scrollView);
        //sv.smoothScrollTo(0,0);


    }

    private void showMainImage() {
        mQuizImage = findViewById(R.id.imageView);
        String img = mQuestions.getImages(QuestionNum);
        mQuizImage.setImageResource(getResources().getIdentifier(img, "drawable", getPackageName()));
    }

    private void showRadioButtonAnswer(int qnum) {
        final LinearLayout answerLayout = findViewById(R.id.answers_layout);
        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rg.setLayoutParams(lp);
        rg.setPadding(150, 0, 0, 0);

        final RadioButton[] rb1 = new RadioButton[4];
        for (int i = 0; i <= 3; i++) {
            rb1[i] = new RadioButton(this);
            rb1[i].setText(mQuestions.getChoice(qnum)[i]);
            rb1[i].setTextColor(Color.BLACK);
            rb1[i].setPadding(8, 16, 8, 16);
            rb1[i].setTextSize(20);
            rb1[i].setId(i);
            rb1[i].setWidth(600);

            rg.addView(rb1[i]);

        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int Id) {
                mAnswer = mQuestions.getChoice(QuestionNum)[Id];
            }
        });
        answerLayout.addView(rg);

    }


}