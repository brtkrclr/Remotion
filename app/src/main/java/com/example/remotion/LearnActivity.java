package com.example.remotion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.Console;
import java.util.ArrayList;

public class LearnActivity extends AppCompatActivity {
    ImageView imageEmotion;
    TextView textView;
    RecyclerView recyclerView;



    private DatabaseReference myRef;
    private ArrayList<Model> modelList;
    private RecyclerAdapter recyclerAdapter;
    private Context mContex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        recyclerView = findViewById(R.id.recView);
        imageEmotion = findViewById(R.id.imageEmotion);
        textView = findViewById(R.id.titleEmotion);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        myRef=FirebaseDatabase.getInstance().getReference();

        modelList = new ArrayList<>();


        GetDataFromFirebase();


    }

    private void GetDataFromFirebase() {
        Query query = myRef.child("Data");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                ClearAll();
                for (DataSnapshot dsnapshot : snapshot.getChildren()) {
                    Model model = new Model();
                    model.setImage(dsnapshot.child("image").getValue().toString());
                    model.setTitle(dsnapshot.child("title").getValue().toString());
                    modelList.add(model);
                }
                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), modelList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    private void ClearAll() {
        if (modelList != null) {
            modelList.clear();

            if(recyclerAdapter!=null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        modelList = new ArrayList<>();
    }


}
