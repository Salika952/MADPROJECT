package com.example.fitzky2.Product;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitzky2.R;
import com.example.fitzky2.paymnet.PaymentType;
import com.example.fitzky2.paymnet.delivery;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class productAdapter extends RecyclerView.Adapter<productAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Product> product;

    public productAdapter(Context c, ArrayList<Product> product){
        this.context = c;
        this.product =product;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = this.product.get(position);

        holder.proName.setText(product.getName()+"");
        holder.proPrice.setText(product.getPrice()+"");
        holder.proQuantity.setText(product.getQty()+"");
        holder.proType.setText(product.getType()+"");


        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(v.getContext(),PaymentType.class);
                v.getContext().startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView proName,proPrice,proQuantity, proType;
        Button btnBuy;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            proName = itemView.findViewById(R.id.proName);
            proPrice = itemView.findViewById(R.id.proPrice);
            proQuantity= itemView.findViewById(R.id.proQuantity);
            proType = itemView.findViewById(R.id.protype);
            cardView = itemView.findViewById(R.id.cardView);
            btnBuy=itemView.findViewById(R.id.btnBuy);
        }
    }
}
