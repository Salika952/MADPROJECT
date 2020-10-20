package com.example.fitzky2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fitzky2.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin_handling_user_profile extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Users> user;
    private UserAdapter userAdapter;
    DatabaseReference dref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_handling_user_profile);


        recyclerView = findViewById(R.id.recycler_profile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        user = new ArrayList<Users>();

        dref = FirebaseDatabase.getInstance().getReference().child("Users");
        dref.addListenerForSingleValueEvent(valueEventListener);


    }
        ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                Users users1 = dataSnapshot1.getValue(Users.class);
                user.add(users1);

            }
            userAdapter = new UserAdapter(Admin_handling_user_profile.this,user);
            recyclerView.setAdapter(userAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
