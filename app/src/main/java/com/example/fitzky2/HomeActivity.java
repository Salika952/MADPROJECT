package com.example.fitzky2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import com.example.fitzky2.Discount.DiscountMenu;
import com.example.fitzky2.Feedback.Feedback;
import com.example.fitzky2.Feedback.FeedbackManage;
import com.example.fitzky2.Product.AddProduct;
import com.example.fitzky2.Product.Product;
import com.example.fitzky2.Product.ProductMenu;
import com.example.fitzky2.Product.productRetrieve;
import com.example.fitzky2.paymnet.Newaddress;
import com.example.fitzky2.paymnet.PaymentType;

public class HomeActivity extends AppCompatActivity {

    ImageView profilebtn,discountbtn,paymentbtn,productbtn,logoutbtn,feedbackbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profilebtn=(ImageView) findViewById(R.id.prof);
        productbtn=(ImageView) findViewById(R.id.prod);
        paymentbtn=(ImageView) findViewById(R.id.pay);
        discountbtn=(ImageView) findViewById(R.id.dis);
        feedbackbtn=(ImageView) findViewById(R.id.feed);
        logoutbtn=(ImageView)findViewById(R.id.logout);


        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        productbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, productRetrieve.class);
                startActivity(intent);
            }
        });



        paymentbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(HomeActivity.this, PaymentType.class);
                        startActivity(intent1);



            }
        });



        discountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, DiscountMenu.class);
                startActivity(intent);
            }
        });

        feedbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, FeedbackManage.class);
                startActivity(intent);
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

