package com.example.fitzky.managediscount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitzky.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewDiscount extends AppCompatActivity {

    TextView vid,vname,vpercentage,vprice,ditemid;
    Button vsearch,vupdate,vdelete;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_discount);

        vid =findViewById(R.id.view_dis_id);
        vname = findViewById(R.id.view_dis_name);
        vpercentage = findViewById(R.id.view_dis_percentage);
        vprice = findViewById(R.id.view_dis_price);
        ditemid = findViewById(R.id.ditem_id);

        vsearch = findViewById(R.id.srchbtn);
        vupdate = findViewById(R.id.btnEdit);
        vdelete = findViewById(R.id.btnDel);

        vsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String did = ditemid.getText().toString();
                DatabaseReference showdbref = FirebaseDatabase.getInstance().getReference().child("Discount").child(did);
                showdbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            vid.setText(dataSnapshot.child("dID").getValue().toString());
                            vname.setText(dataSnapshot.child("dName").getValue().toString());
                            vpercentage.setText(dataSnapshot.child("dPercentage").getValue().toString());
                            vprice.setText(dataSnapshot.child("dPrice").getValue().toString());
                            ditemid.setText(dataSnapshot.child("ditemid").getValue().toString());
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        vupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDiscount.this,UpdateDiscount.class);
                TextView textView = findViewById(R.id.ditem_id);
                String tid =textView.getText().toString();
                intent.putExtra("key",tid);
            }
        });

        vdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference delref = FirebaseDatabase.getInstance().getReference().child("Discount");
                delref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String delid = ditemid.getText().toString();
                        if (dataSnapshot.hasChild(delid)){
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Discount").child(delid);
                            dbRef.removeValue();
                            ClearControls();

                            Toast.makeText(getApplicationContext(),"Data Deleted Successfully",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No Data to Delete",Toast.LENGTH_SHORT).show();
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
        vid.setText("");
        vname.setText("");
        vpercentage.setText("");
        vprice.setText("");
    }
}