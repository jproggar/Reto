package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Consejos extends AppCompatActivity {

    ImageView regresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        Intent receive= getIntent();
        String idUser= receive.getStringExtra("idUser");

        regresa = findViewById(R.id.ivRegresaCon);

        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), Principal.class);
                intent.putExtra("idUser",idUser);
                startActivity(intent);
            }
        });
    }
}