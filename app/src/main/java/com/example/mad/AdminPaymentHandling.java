package com.example.mad;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdminPaymentHandling extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<delivery> list;
    private DeliveryAdapter deliveryAdapter;

    DatabaseReference dat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_payment_handling);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<delivery>();

        dat= FirebaseDatabase.getInstance().getReference().child("delivery");
        dat.addListenerForSingleValueEvent(valueEventListener);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshotl : dataSnapshot.getChildren()) {
                delivery d = dataSnapshotl.getValue(delivery.class);
                list.add(d);
            }
            deliveryAdapter = new DeliveryAdapter(AdminPaymentHandling.this, list);
            recyclerView.setAdapter(deliveryAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(AdminPaymentHandling.this, "Opss...Somethind is wrong", Toast.LENGTH_SHORT).show();
        }
    };
}