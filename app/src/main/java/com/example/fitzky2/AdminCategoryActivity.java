package com.example.fitzky2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fitzky2.Discount.DiscountMenu;
import com.example.fitzky2.Feedback.FeedbackManage;
import com.example.fitzky2.Product.ProductMenu;
import com.example.fitzky2.paymnet.PaymentType;

public class AdminCategoryActivity extends AppCompatActivity {

    Button adddiscount;
    ImageView profilebtn,discountbtn,paymentbtn,productbtn,logoutbtn,feedbackbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        Toast.makeText(this,"Welcome Admin",Toast.LENGTH_LONG).show();


        //adddiscount=(Button) findViewById(R.id.discount_btn);

        //adddiscount.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //Intent intent = new Intent(AdminCategoryActivity.this,DiscountMenu.class);
                //startActivity(intent);
               // startActivity( new Intent(AdminCategoryActivity.this, DiscountMenu.class));
            //}
        //});


        profilebtn=(ImageView) findViewById(R.id.prof);
        productbtn=(ImageView) findViewById(R.id.prod);
        paymentbtn=(ImageView) findViewById(R.id.pay);
        discountbtn=(ImageView) findViewById(R.id.dis);
        feedbackbtn=(ImageView) findViewById(R.id.feed);
        logoutbtn=(ImageView)findViewById(R.id.logout);


        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminCategoryActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        productbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminCategoryActivity.this, ProductMenu.class);
                startActivity(intent);
            }
        });



        paymentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AdminCategoryActivity.this, PaymentType.class);
                startActivity(intent1);



            }
        });



        discountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminCategoryActivity.this, DiscountMenu.class);
                startActivity(intent);
            }
        });

        feedbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminCategoryActivity.this, FeedbackManage.class);
                startActivity(intent);
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminCategoryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
