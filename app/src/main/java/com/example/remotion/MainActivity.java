package com.example.remotion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FULL SCREEN MODE

        View view=getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        Button startButton= findViewById(R.id.startButton);
        EditText editText=findViewById(R.id.editText);
        startButton.setOnClickListener(v -> {

            if(editText.getText().toString().isEmpty()){
                Toast.makeText(this,"Please enter your name!",Toast.LENGTH_SHORT).show();
            }else{
                Intent intent=new Intent(this,HomePage.class);
                startActivity(intent);

            }

        });

    }
}