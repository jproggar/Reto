package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    Button reciclaje,statistics,recommendations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        reciclaje=findViewById(R.id.btReciclaje);
        statistics=findViewById(R.id.btEstadisticas);
        recommendations=findViewById(R.id.btConsejos);

        Intent receive= getIntent();
        String idUser= receive.getStringExtra("idUser");

        Intent categorias_view= new Intent(getApplicationContext(),
                Categorias.class);
        categorias_view.putExtra("idUser",idUser);
        categorias_view.putExtra("mensaje", "Vidrio");

        Intent statistics_view= new Intent(getApplicationContext(),
                Estadisticas.class);
        statistics_view.putExtra("idUser",idUser);
        statistics_view.putExtra("mensaje", "Vidrio");

        Intent recommendations_view= new Intent(getApplicationContext(),
                Consejos.class);
        recommendations_view.putExtra("idUser",idUser);

        reciclaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(categorias_view);
            }
        });

        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(statistics_view);
            }
        });

        recommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(recommendations_view);
            }
        });

    }
    public void gotologin(View view){
        Intent intent=new Intent(Principal.this,MainActivity.class);
        startActivity(intent);
    }
    public void gotoCategorias(View view){
        Intent intent=new Intent(Principal.this,Categorias.class);
        startActivity(intent);
    }
}