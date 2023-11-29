package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.reto1.models.PapelM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Papel extends AppCompatActivity {

    Button register;
    ImageView regresa;
    EditText quantity,price;
    Spinner month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papel);

        quantity=findViewById(R.id.etPapelCantidad);
        price=findViewById(R.id.etPapelValor);
        month=findViewById(R.id.spinnerMesPapel);
        register=findViewById(R.id.btEntrarPapel);
        regresa = findViewById(R.id.ivPapRegresa);

        Intent receive= getIntent();
        String idUser= receive.getStringExtra("idUser");

        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), Categorias.class);
                intent.putExtra("idUser",idUser);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity.getText().toString().isEmpty() ||
                        price.getText().toString().isEmpty() ||
                        month.getSelectedItem().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Debe llenar todos los campos",Toast.LENGTH_LONG).show();
                }else{
                    int quantityPapel= Integer.parseInt(quantity.getText().toString());
                    int pricePapel= Integer.parseInt(price.getText().toString());
                    String monthPapel= month.getSelectedItem().toString();
                    String serial= idUser+"Papel"+monthPapel;
                    PapelM consumePapel= new PapelM(serial,quantityPapel,pricePapel,monthPapel,idUser);
                    registerPapelM(consumePapel);
                    Toast.makeText(getApplicationContext(),"Registro exitoso",
                            Toast.LENGTH_LONG).show();
                    cleanView();
                }
            }
        });
    }


    public void registerPapelM(PapelM consume){

        File papelFile= new File(getFilesDir(),"papel.txt");

        try {
            FileWriter writer= new FileWriter(papelFile,true);
            BufferedWriter bufferedWriter= new BufferedWriter(writer);
            bufferedWriter.write(
                    consume.getSERIAL()+","+
                            consume.getQuantity()+","+
                            consume.getPrice()+","+
                            consume.getMonth()+","+
                            consume.getIdUser()
            );
            bufferedWriter.newLine();
            bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cleanView(){
        quantity.setText("");
        price.setText("");
        month.setSelection(0);
    }
}