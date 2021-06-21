package com.example.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;

public class PeniAdd extends AppCompatActivity {

    EditText kod, nama, status;
    Button submit, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peni_add);

        kod=(EditText)findViewById(R.id.p2akod);
        nama=(EditText)findViewById(R.id.p2aname);
        status=(EditText)findViewById(R.id.p2astatus);

        back=(Button)findViewById(R.id.p2akemba);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        submit=(Button)findViewById(R.id.p2asubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processinsert();
            }
        });
    }
    private void processinsert() {

        Map<String,Object> map=new HashMap<>();
        map.put("kod", kod.getText().toString());
        map.put("nama", nama.getText().toString());
        map.put("status", status.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Jenis Penilaian").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        kod.setText("");
                        nama.setText("");
                        status.setText("");

                        Toast.makeText(getApplicationContext(), "Data Telah Berjaya Dimasukkan", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Data Tidak Dapat Dimasukkan", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}