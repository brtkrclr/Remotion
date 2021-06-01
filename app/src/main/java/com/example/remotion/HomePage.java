package com.example.remotion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        View view=getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

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
            Intent intent=new Intent(this,SettingsActivity.class);
            startActivity(intent);
        });
    }
}