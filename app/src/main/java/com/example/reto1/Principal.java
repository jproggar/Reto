package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }


    public void gotologin(View view){
        Intent intent=new Intent(Principal.this,MainActivity.class);
        startActivity(intent);
    }

    public void gotoCategorias(View view){
        Intent intent=new Intent(Principal.this,Categorias.class);
        startActivity(intent);
    }

    public void gotoEstadistica(View view){
        Intent intent=new Intent(Principal.this,Estadistica.class);
        intent.putExtra("mensaje", "Vidrio");
        startActivity(intent);
    }

    public void gotoConsejos(View view){
        Intent intent=new Intent(Principal.this,Consejos.class);
        startActivity(intent);
    }
}