package com.example.remotion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        

        Button learnButton=findViewById(R.id.learnButton);
        Button playButton=findViewById(R.id.playButton);
        Button settingsButton=findViewById(R.id.settingsButton);

        learnButton.setOnClickListener(v -> {
            Intent intent=new Intent(this,LearnActivity.class);
            startActivity(intent);
        });
        playButton.setOnClickListener(v -> {
            Intent intent=new Intent(this,PlayActivity.class);
            startActivity(intent);
        });
        settingsButton.setOnClickListener(v -> {
            Intent intent=new Intent(this,startPage.class);
            startActivity(intent);
        });
    }
}