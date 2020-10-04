package com.example.mad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import android.support.v7.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.MyviewHolder> {

     private Context context;
     private ArrayList<delivery> deliver;

    public DeliveryAdapter(Context c,ArrayList<delivery> deliver){
        this.context=c;
        this.deliver=deliver;
    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_info,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryAdapter.MyviewHolder holder, int position) {
        final delivery deliver=this.deliver.get(position);

        holder.txtName.setText(deliver.getName());
        holder.txtContact.setText(deliver.getContact());
        holder.txtPostalcode.setText(deliver.getPostalcode());
        holder.txtAddress.setText(deliver.getAddress());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("delivery").child(deliver.getId());
                databaseReference.removeValue();
                Toast.makeText(context,"Deleted Sucessfully!!!",Toast.LENGTH_SHORT).show();

            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(v.getContext(),AdminUpdateData.class);

                intent.putExtra("id",deliver.getId());
                intent.putExtra("name",deliver.getName());
                intent.putExtra("contact",deliver.getContact());
                intent.putExtra("postalcode",deliver.getPostalcode());
                intent.putExtra("address",deliver.getAddress());

                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return deliver.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtContact,txtPostalcode,txtAddress;
        Button btnDelete,btnUpdate;
        CardView cardView;

        public MyviewHolder(View itemView) {
            super(itemView);

            txtName= itemView.findViewById(R.id.tvName);
            txtContact= itemView.findViewById(R.id.tvContact);
            txtPostalcode=itemView.findViewById(R.id.tvPostalcode);
            txtAddress=itemView.findViewById(R.id.tvAddress);
            btnDelete=itemView.findViewById(R.id.btnDelete);
            btnUpdate=itemView.findViewById(R.id.btnUpdate);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
