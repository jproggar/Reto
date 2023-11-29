package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class Categorias extends AppCompatActivity {

    Button vidrio, plastico, papel;
    ImageView regresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        Intent receive= getIntent();
        String idUser= receive.getStringExtra("idUser");

        vidrio = findViewById(R.id.btVidrio);
        papel = findViewById(R.id.btPapel);
        regresa = findViewById(R.id.ivRegresaCat);

        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), Principal.class);
                intent.putExtra("idUser",idUser);
                startActivity(intent);
            }
        });
        papel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), Papel.class);
                intent.putExtra("idUser",idUser);
                startActivity(intent);
            }
        });
        vidrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), Vidrio.class);
                intent.putExtra("idUser",idUser);
                startActivity(intent);
            }
        });
    }
}