package com.example.fitzky.managediscount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitzky.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDiscount extends AppCompatActivity {

    EditText udid,udname,udpercentage,udprice;
    Button btnupdate;
    DatabaseReference dbRef;
    Discount dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_discount);

        Intent intent = getIntent();
        String tid = intent.getStringExtra("key");
        TextView textView = findViewById(R.id.udis_id);
        textView.setText(tid);

        udid = findViewById(R.id.udis_id);
        udname = findViewById(R.id.udis_name);
        udpercentage = findViewById(R.id.udis_percentage);
        udprice = findViewById(R.id.udis_price);

        btnupdate = findViewById(R.id.udis_btn);

        udid.setText(tid);
        String did=udid.getText().toString();
        DatabaseReference showdbRef = FirebaseDatabase.getInstance().getReference().child("Discount").child(did);

        showdbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    udid.setText(dataSnapshot.child("dID").getValue().toString());
                    udname.setText(dataSnapshot.child("dName").getValue().toString());
                    udpercentage.setText(dataSnapshot.child("dPercentage").getValue().toString());
                    udprice.setText(dataSnapshot.child("dPrice").getValue().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(),"No Data to Display",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference updbref = FirebaseDatabase.getInstance().getReference().child("Discount");
                updbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String ddid = udid.getText().toString();
                        String ddname = udname.getText().toString();
                        int ddper = Integer.parseInt(udpercentage.getText().toString());
                        int ddprice = Integer.parseInt(udprice.getText().toString());

                        if (dataSnapshot.hasChild(ddid)){
                            dis = new Discount(ddid, ddname, ddper, ddprice);
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Discount").child(ddid);
                            dbRef.setValue(dis);
                            ClearControls();

                            Toast.makeText(getApplicationContext(),"Successfully Updated", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No Source to Update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    public void ClearControls(){
        udid.setText("");
        udname.setText("");
        udpercentage.setText("");
        udprice.setText("");
    }
}