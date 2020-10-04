package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Newaddress extends AppCompatActivity {
    private EditText name,contact,postalcode,address;
    private Button save;

    DatabaseReference databaseReference;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaddress);

        databaseReference= FirebaseDatabase.getInstance().getReference("delivery");

        name=(EditText)findViewById(R.id.ET_Name);
        contact=(EditText)findViewById(R.id.ET_Phone);
        address=(EditText)findViewById(R.id.ET_Address);
        postalcode=(EditText)findViewById(R.id.ET_Postal);


        save=(Button)findViewById(R.id.btnSaveNewAddress);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);


        awesomeValidation.addValidation(this,R.id.ET_Name, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.ET_Phone,"[0]{1}[0-9]{9}$",R.string.invalid_number);
        awesomeValidation.addValidation(this,R.id.ET_Address, RegexTemplate.NOT_EMPTY,R.string.invalid_address);
        awesomeValidation.addValidation(this,R.id.ET_Postal,RegexTemplate.NOT_EMPTY,R.string.Empty_postalcode);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddShippingDetails();
            }
        });
    }

    public void AddShippingDetails(){


        if(awesomeValidation.validate()){
            AlertDialog.Builder builder=new AlertDialog.Builder(Newaddress.this);
            builder.setMessage("Are you sure this details?").setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String Name=name.getText().toString();
                    String Contact=contact.getText().toString();
                    String Postcode=postalcode.getText().toString();
                    String Address=address.getText().toString();


                    String id=databaseReference.push().getKey();
                    delivery cashOndelivery=new delivery(id,Name,Contact,Postcode,Address);
                    databaseReference.child(id).setValue(cashOndelivery);

                    name.setText("");
                    Toast.makeText(Newaddress.this,"Sucesfully Enter your Details!! ",Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getApplicationContext(),Verify_OTP.class);
                    //intent.putExtra("Contact",Contact);
                    Bundle extras = new Bundle();
                    extras.putString("Contact",Contact);
                    extras.putString("ID",id);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }).setNegativeButton("Cancle",null);

            AlertDialog alert=builder.create();
            alert.show();


        }else{
            Toast.makeText(Newaddress.this,"Please complte the form!!!!! ",Toast.LENGTH_SHORT).show();
        }
    }
}