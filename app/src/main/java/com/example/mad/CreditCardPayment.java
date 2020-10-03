package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreditCardPayment extends AppCompatActivity {
        private EditText cardno,expdate,ccv;
        private Button btnpay;

        DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_payment);

        databaseReference= FirebaseDatabase.getInstance().getReference("Creditcrd");

        cardno=(EditText)findViewById(R.id.ET_CardNum);
        expdate=(EditText)findViewById(R.id.ET_Expdate);
        ccv=(EditText)findViewById(R.id.ET_Ccv);

        btnpay=(Button)findViewById(R.id.btnPaynow);
        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcreditcardDetails();
            }
        });
    }

    public void addcreditcardDetails(){
        String Cardno=cardno.getText().toString();
        String Expdate=expdate.getText().toString();
        String Ccv=ccv.getText().toString();


        if(TextUtils.isEmpty(Cardno)){
            Toast.makeText(CreditCardPayment.this,"Plese provide your card Number!! ",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Expdate)){
            Toast.makeText(CreditCardPayment.this,"Plese provide expired date!! ",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Ccv)){
            Toast.makeText(CreditCardPayment.this,"Plese provide ccv number!! ",Toast.LENGTH_SHORT).show();
        }
        else {
            String id = databaseReference.push().getKey();
            Creditcarddetails creditcarddetails = new Creditcarddetails(id, Cardno, Expdate, Ccv);
            databaseReference.child(id).setValue(creditcarddetails);

            cardno.setText("");
            Toast.makeText(CreditCardPayment.this, "Sucesfully enter your details ", Toast.LENGTH_SHORT).show();

            //Intent intent=new Intent(getApplicationContext(),Verify_OTP.class);
            //intent.putExtra("Contact",Contact);
            //startActivity(intent);

            startActivity(new Intent(CreditCardPayment.this, Newaddress.class));

        }

    }
}