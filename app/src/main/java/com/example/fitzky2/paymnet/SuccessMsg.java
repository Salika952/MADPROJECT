package com.example.fitzky2.paymnet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitzky2.Product.productRetrieve;
import com.example.fitzky2.R;

public class SuccessMsg extends AppCompatActivity {

    TextView txt;
    Button btnBacktoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_msg);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String contact = extras.getString("Contact");
        String id = extras.getString("ID");
        //Toast.makeText(this, ""+contact, Toast.LENGTH_SHORT).show();

        txt = findViewById(R.id.textView30);
        String x = (contact+"/n"+id);
        txt.setText(x);

        btnBacktoCart = (Button) findViewById(R.id.btnbacktoCart);
        btnBacktoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(SuccessMsg.this, productRetrieve.class);
                startActivity(intent3);
            }
        });

    }
}
