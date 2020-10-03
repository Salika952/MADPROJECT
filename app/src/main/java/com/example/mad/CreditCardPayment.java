package com.example.mad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.braintreepayments.cardform.view.CardForm;

public class CreditCardPayment extends AppCompatActivity {
        private EditText cardno,expdate,ccv;
        private Button btnpay;
         CardForm cardFrom;

         AlertDialog.Builder alertBuilder;

        DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_payment);

        databaseReference= FirebaseDatabase.getInstance().getReference("Creditcrd");

        cardFrom=findViewById(R.id.card_form);
        btnpay=findViewById(R.id.btnPaynow);
        cardFrom.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(CreditCardPayment.this);
        cardFrom.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardFrom.isValid()){
                    alertBuilder=new AlertDialog.Builder(CreditCardPayment.this);
                    alertBuilder.setTitle("Confirm before purchase");
                    alertBuilder.setMessage("Card Number: " + cardFrom.getCardNumber()+"\n" +
                            "Card expiry date: " + cardFrom.getExpirationDateEditText().getText().toString() + "\n" +
                            "Card Cvv: " +cardFrom.getCvv() + "\n" +
                            "Phone Number: "+ cardFrom.getMobileNumber());
                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Toast.makeText(CreditCardPayment.this,"Thank you purchase",Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertBuilder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }
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