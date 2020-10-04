package com.example.fitzky2.Discount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fitzky2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDiscount extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText udid,udname,type, udpercentage,udprice;
    Button btnupdate;
    DatabaseReference dbRef;
    Discount dis;

    private Spinner spinnerDisType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_discount);

        spinnerDisType = findViewById(R.id.spinnerItemType);
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
                    String[] textSizes = getResources().getStringArray(R.array.font_sizes);
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_spinner_item, textSizes);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDisType.setAdapter(adapter);
                    spinnerDisType.setSelection(adapter.getPosition("type"));
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
                        String type = spinnerDisType.getSelectedItem().toString();
                        int ddper = Integer.parseInt(udpercentage.getText().toString());
                        int ddprice = Integer.parseInt(udprice.getText().toString());

                        if (dataSnapshot.hasChild(ddid)){
                            dis = new Discount(ddid, ddname, type, ddper, ddprice);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (adapterView.getId() == R.id.spinnerItemType)
        {
            String valuefromSpinner = adapterView.getItemAtPosition(position).toString();
            type.getText().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}