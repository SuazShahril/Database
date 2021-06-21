package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    RecyclerView recview;
    PenilaianAdapter adapter;
    Button masButton, kemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recview=(RecyclerView)findViewById(R.id.recview2);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PeniModel> options =
                new FirebaseRecyclerOptions.Builder<PeniModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Jenis Penilaian"), PeniModel.class)
                        .build();

        adapter=new PenilaianAdapter(options);
        recview.setAdapter(adapter);

        masButton = findViewById(R.id.badd);
        masButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PeniAdd.class));
            }
        });

        kemButton = findViewById(R.id.bback);

    }
    @Override
    protected  void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected  void onStop(){
        super.onStop();
        adapter.stopListening();
    }

}