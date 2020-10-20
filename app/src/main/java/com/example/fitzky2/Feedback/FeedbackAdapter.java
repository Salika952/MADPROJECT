package com.example.fitzky2.Feedback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitzky2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Feedback> feedback;

    public FeedbackAdapter(Context c,ArrayList<Feedback> feedback){

        this.context=c;
        this.feedback=feedback;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Feedback feedback=this.feedback.get(position);

        holder.userEmail.setText(feedback.getEmail()+"");
        holder.user_title.setText(feedback.getTitle()+"");
        holder.user_description.setText(feedback.getDescription()+"");




        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference dref = FirebaseDatabase.getInstance().getReference().child("Feedback").child(feedback.getEmail());
                dref.removeValue();
                Toast.makeText(context,"Deleted Sucessfully!!!",Toast.LENGTH_SHORT).show();

            }
        });

    }




    @Override
    public int getItemCount() {
        return feedback.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userEmail,user_title,user_description;
        Button btnDelete;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userEmail = itemView.findViewById(R.id.userEmail);
            user_title = itemView.findViewById(R.id.user_feedback_title);
            user_description= itemView.findViewById(R.id.user_feedback_description);

            cardView = itemView.findViewById(R.id.cardView);

            btnDelete=itemView.findViewById(R.id.btn_delete);



        }
    }
}
