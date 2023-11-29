package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.reto1.models.VidrioM;
import com.example.reto1.models.PapelM;

public class Estadisticas extends AppCompatActivity {

    TextView totalVidrio,totalPapel,total_pay,max_vidrio_month,
            max_papel_month,max_vidrio_quantity,max_papel_quantity;
    TableLayout vidrio,papel;
    ImageView regresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        totalVidrio=findViewById(R.id.textViewTotalVidrio);
        totalPapel=findViewById(R.id.textViewTotalPapel);
        total_pay=findViewById(R.id.textViewTotalPay);
        max_vidrio_month=findViewById(R.id.textViewMonthMaxVidrio);
        max_vidrio_quantity=findViewById(R.id.textViewMaxVidrioQuantity);
        max_papel_month=findViewById(R.id.textViewMonthMaxPapel);
        max_papel_quantity=findViewById(R.id.textViewMaxPapelQuantity);
        vidrio= findViewById(R.id.TableVidrio);
        papel= findViewById(R.id.TablePapel);
        regresa = findViewById(R.id.ivRegresaEst);

        Intent receive= getIntent();
        String idUser= receive.getStringExtra("idUser");

        regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), Principal.class);
                intent.putExtra("idUser",idUser);
                startActivity(intent);
            }
        });
        File vidrio= new File(getFilesDir(),"vidrio.txt");
        File papel= new File(getFilesDir(),"papel.txt");

        ArrayList<VidrioM> list_vidrio= listVidrio(vidrio,idUser);
        ArrayList<PapelM> list_papel= listPapel(papel,idUser);
        inflateVidrioTable(list_vidrio);
        inflatePapelTable(list_papel);
        total_consume_vidrio(list_vidrio);
        total_consume_papel(list_papel);
        int total= totalPayVidrio(list_vidrio)+totalPayPapel(list_papel);
        total_pay.setText("$ "+total);
    }
    public void inflateVidrioTable(ArrayList<VidrioM>list_vidrio){
        System.out.println("inflate");
        System.out.println(list_vidrio.size());

        int total=0;
        String averageValue="";
        System.out.println("Antes");
        for (VidrioM i: list_vidrio){
            TableRow row=new TableRow(this);
            TextView quantity= new TextView(this);
            quantity.setWidth(97);
            quantity.setTextSize(14);
            quantity.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView price= new TextView(this);
            price.setTextSize(14);
            price.setWidth(96);
            price.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView month= new TextView(this);
            month.setWidth(105);
            month.setTextSize(14);
            month.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView average= new TextView(this);
            average.setWidth(90);
            average.setTextSize(14);
            average.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            quantity.setText(i.getQuantity()+"");
            price.setText(i.getPrice()+"");
            month.setText(i.getMonth());
            average.setText((i.getPrice()*i.getQuantity())+"");
            row.addView(month);
            row.addView(quantity);
            row.addView(price);
            row.addView(average);
            vidrio.addView(row);
        }
        System.out.println("Fin");
    }

    public void inflatePapelTable(ArrayList<PapelM>list_papel){
        int total=0;
        String averageValue="";
        System.out.println("Antes");
        for (PapelM i: list_papel){
            TableRow row=new TableRow(this);
            TextView quantity= new TextView(this);
            quantity.setWidth(97);
            quantity.setTextSize(14);
            quantity.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView price= new TextView(this);
            price.setTextSize(14);
            price.setWidth(96);
            price.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView month= new TextView(this);
            month.setWidth(105);
            month.setTextSize(14);
            month.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            TextView average= new TextView(this);
            average.setWidth(90);
            average.setTextSize(14);
            average.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            quantity.setText(i.getQuantity()+"");
            price.setText(i.getPrice()+"");
            month.setText(i.getMonth());
            average.setText((i.getPrice()*i.getQuantity())+"");
            row.addView(month);
            row.addView(quantity);
            row.addView(price);
            row.addView(average);
            papel.addView(row);
        }
    }
    public String average(int total, int quantity){
        if (total==0){
            return "Error";
        }else{
            double total_d= total;
            double quantity_d=quantity;
            double avg= (quantity_d/total_d)*100; //2.3698
            DecimalFormat df= new DecimalFormat("#.##");// 2.36
            // df.setRoundingMode(RoundingMode.FLOOR); 2.37
            return df.format(avg);
        }
    }
    public int totalPayVidrio(ArrayList<VidrioM>list){
        int pay=0;
        for (VidrioM i: list){
            pay+=i.getPrice()*i.getQuantity();
        }
        return pay;
    }
    public int totalPayPapel(ArrayList<PapelM>list){
        int pay=0;
        for (PapelM i: list){
            pay+=i.getPrice()*i.getQuantity();
        }
        return pay;
    }
    public void total_consume_vidrio(ArrayList<VidrioM>list){
        int total=0;
        String month="";
        int max=0;
        for (VidrioM i:list){
            total+=i.getQuantity();
            if (max<i.getQuantity()){
                max=i.getQuantity();
                month=i.getMonth();
            }
        }
        totalVidrio.setText(total+" Kg");
        max_vidrio_quantity.setText(max+" Kg");
        max_vidrio_month.setText(month);
    }
    public void total_consume_papel(ArrayList<PapelM>list){
        int total=0;
        String month="";
        int max=0;
        for (PapelM i:list){
            total+=i.getQuantity();
            if (max<i.getQuantity()){
                max=i.getQuantity();
                month=i.getMonth();
            }
        }
        totalPapel.setText(total+" Kg");
        max_papel_quantity.setText(max+" Kg");
        max_papel_month.setText(month);
    }
    public ArrayList<VidrioM> listVidrio(File vidrio,String user){
        ArrayList<VidrioM> list= new ArrayList<>();
        try {
            FileReader reader= new FileReader(vidrio);
            BufferedReader bufferedReader=new BufferedReader(reader);
            String cadena;
            while ((cadena=bufferedReader.readLine())!=null){
                String [] data= cadena.split(",");
                if(data[4].equals(user)){
                    String serial= data[0];
                    int quantity= Integer.parseInt(data[1]);
                    int price= Integer.parseInt(data[2]);
                    String month= data[3];
                    String idUser= data[4];
                    VidrioM obj= new VidrioM(serial,quantity,price,month,idUser);
                    list.add(obj);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<PapelM> listPapel(File papel, String user){
        ArrayList<PapelM> list= new ArrayList<>();
        try {
            FileReader reader= new FileReader(papel);
            BufferedReader bufferedReader=new BufferedReader(reader);
            String cadena;
            while ((cadena=bufferedReader.readLine())!=null){
                String [] data= cadena.split(",");
                if(data[4].equals(user)){
                    String serial= data[0];
                    int quantity= Integer.parseInt(data[1]);
                    int price= Integer.parseInt(data[2]);
                    String month= data[3];
                    String idUser= data[4];
                    PapelM obj= new PapelM(serial,quantity,price,month,idUser);
                    list.add(obj);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}