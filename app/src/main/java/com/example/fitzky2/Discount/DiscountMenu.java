package com.example.fitzky2.Discount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitzky2.R;

public class DiscountMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_menu);

        Button btnadd = (Button)findViewById(R.id.main_add1);
        Button btnview = (Button)findViewById(R.id.main_view);


        btnadd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity( new Intent(DiscountMenu.this, AddDiscount.class));
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiscountMenu.this, ViewDiscount.class));
            }
        });
    }
}
