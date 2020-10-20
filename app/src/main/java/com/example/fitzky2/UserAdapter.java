package com.example.fitzky2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitzky2.Model.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Users> user;

    public UserAdapter(Context c, ArrayList<Users> user){
        this.context = c;
        this.user =user;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      final  Users user = this.user.get(position);

        holder.userName.setText(user.getName()+"");
        holder.userPassword.setText(user.getPassword()+"");
        holder.userContact.setText(user.getPhone()+"");



        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            DatabaseReference    dref = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getPhone());
                dref.removeValue();
                Toast.makeText(context,"Deleted Sucessfully!!!",Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName,userPassword,userContact;
        Button btnDelete;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userPassword = itemView.findViewById(R.id.userPassword);
            userContact= itemView.findViewById(R.id.userContact);

            cardView = itemView.findViewById(R.id.cardView);

            btnDelete=itemView.findViewById(R.id.btn_delete);

        }
    }
}
