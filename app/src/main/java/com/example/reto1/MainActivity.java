package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void gotoRegistro(View view){
        Intent intent=new Intent(MainActivity.this,Registro.class);
        startActivity(intent);
    }
    public void gotoPrincipal(View view){
        Intent intent=new Intent(MainActivity.this,Principal.class);
        startActivity(intent);
    }
}