package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    private ImageButton button1;
    private ImageButton button2;
    private Button button3,button4;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = (ImageButton) findViewById(R.id.imgbtn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, Newaddress.class);
                startActivity(intent1);
            }
        });

        button2 = (ImageButton) findViewById(R.id.imgbtn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, CreditCardPayment.class);
                startActivity(intent2);
            }
        });

        button3 = (Button) findViewById(R.id.btnpaymenthandling);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity.this, AdminPaymentHandling.class);
                startActivity(intent3);
            }
        });

        button4 = (Button) findViewById(R.id.btnConvrt);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(MainActivity.this,CurrencyConvertr.class);
                startActivity(intent4);
            }
        });


    }
}