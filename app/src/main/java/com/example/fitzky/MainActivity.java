package com.example.fitzky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitzky.managediscount.AddDiscount;
import com.example.fitzky.managediscount.ViewDiscount;


public class MainActivity extends AppCompatActivity {

    Button btnadd = (Button)findViewById(R.id.main_add);
    Button btnview = (Button)findViewById(R.id.main_view);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            startActivity( new Intent(MainActivity.this, AddDiscount.class));
        }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewDiscount.class));
            }
        });
    }


}