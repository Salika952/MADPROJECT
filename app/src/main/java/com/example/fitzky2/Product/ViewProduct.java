package com.example.fitzky2.Product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitzky2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProduct extends AppCompatActivity {

    TextView id, name, qty, description, type, price, id2;
    ImageButton update,delete,search;
    ImageView imageView;

    DatabaseReference dbref;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        id = findViewById(R.id.txtsearch);
        name = findViewById(R.id.txtname);
        qty = findViewById(R.id.txtingre);
        description = findViewById(R.id.txtdesc);
        type = findViewById(R.id.txttype);
        price = findViewById(R.id.txtprice);
        id2 = findViewById(R.id.txtid);

        imageView = findViewById(R.id.imgView);

        update = findViewById(R.id.btnvupdate);
        delete = findViewById(R.id.btnvdelete);
        search = findViewById(R.id.btnsearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iid = id.getText().toString();
                DatabaseReference showdref = FirebaseDatabase.getInstance().getReference().child("Product").child(iid);

                showdref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren())
                        {
                            id.setText(dataSnapshot.child("id").getValue().toString());
                            name.setText(dataSnapshot.child("name").getValue().toString());
                            qty.setText(dataSnapshot.child("qty").getValue().toString());
                            description.setText(dataSnapshot.child("description").getValue().toString());
                            type.setText(dataSnapshot.child("type").getValue().toString());
                            price.setText(dataSnapshot.child("price").getValue().toString());
                            id2.setText(dataSnapshot.child("id").getValue().toString());

                            String type = dataSnapshot.child("type").getValue().toString();
                            if(type.equals("T-Shirt"))
                            {
                                imageView.setImageResource(R.drawable.t1);
                            }else if(type.equals("Shoes"))
                            {
                                imageView.setImageResource(R.drawable.s1);
                            }else if(type.equals("Suppliments"))
                            {
                                imageView.setImageResource(R.drawable.sp1);
                            }else if (type.equals("Weights"))
                            {
                                imageView.setImageResource(R.drawable.w1);
                            }else {
                                imageView.setImageResource(R.drawable.reload);
                            }

                        }else
                        {
                            Toast.makeText(getApplicationContext(), "No Source to Display.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        update = (ImageButton) findViewById(R.id.btnvupdate);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewProduct.this, UpdateProduct.class);
                TextView textView = findViewById(R.id.txtsearch);
                String tid =textView.getText().toString();
                intent.putExtra("key",tid);


                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Product");

                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String did = id.getText().toString();
                        if(dataSnapshot.hasChild(did))
                        {
                            dbref = FirebaseDatabase.getInstance().getReference().child("Product").child(did);
                            dbref.removeValue();
                            clearControlls();

                            Toast.makeText(getApplicationContext(), "Data Deleted Successfully",Toast.LENGTH_SHORT).show();

                        }else
                        {
                            Toast.makeText(getApplicationContext(), "No Data to Delete",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
    public void clearControlls()
    {
        id.setText("");
        name.setText("");
        qty.setText("");
        description.setText("");
        type.setText("");
        price.setText("");
    }
}