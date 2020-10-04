package com.example.fitzky2.paymnet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitzky2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminUpdateData extends AppCompatActivity {

    EditText etName,etContact,etPostalcode,etAddress;
    Button btnUpdate;
    String id,name,contact,postalcode,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_data);

        etName=findViewById(R.id.etName);
        etContact=findViewById(R.id.etContact);
        etPostalcode=findViewById(R.id.etPostalcode);
        etAddress=findViewById(R.id.etAddress);

        btnUpdate=findViewById(R.id.btnUpdate);

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        name=intent.getStringExtra("name");
        contact=intent.getStringExtra("contact");
        postalcode=intent.getStringExtra("postalcode");
        address=intent.getStringExtra("address");

        etName.setText(name);
        etContact.setText(contact);
        etPostalcode.setText(postalcode);
        etAddress.setText(address);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(AdminUpdateData.this);
                builder.setMessage("Are you sure this details?").setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("delivery").child(id);
                        String name,contact,postalcode,address;

                        name=etName.getText().toString();
                        contact=etContact.getText().toString();
                        postalcode=etPostalcode.getText().toString();
                        address=etAddress.getText().toString();

                        delivery d=new delivery(id,name,contact,postalcode,address);
                        databaseReference.setValue(d);
                        Toast.makeText(AdminUpdateData.this,"Shipment details update successfull...",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdminUpdateData.this,AdminPaymentHandling.class));
                        finish();
                    }
                }).setNegativeButton("Cancle",null);

                AlertDialog alert=builder.create();
                alert.show();


            }
        });
    }
}
