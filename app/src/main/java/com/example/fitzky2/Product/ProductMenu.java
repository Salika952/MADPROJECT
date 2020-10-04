package com.example.fitzky2.Product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitzky2.R;


public class ProductMenu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_menu);

        Button btnadd = (Button) findViewById(R.id.btnadd);
        Button btnview = (Button) findViewById(R.id.btnview);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductMenu.this, AddProduct.class);
                startActivity(intent);
            }
        });



        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductMenu.this,ViewProduct.class);
                startActivity(intent);
            }
        });

    }
}