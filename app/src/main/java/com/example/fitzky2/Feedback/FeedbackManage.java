package com.example.fitzky2.Feedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitzky2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackManage extends AppCompatActivity {

    EditText email,title,description;
    Button btnadd,btnview,btnup,btndel;
    DatabaseReference dbref;
    Feedback fedbk;

    private void clearControls(){
        email.setText("");
        title.setText("");
        description.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_manage);

        email = findViewById(R.id.email_input);
        title = findViewById(R.id.title_input);
        description = findViewById(R.id.description_input);

        btnadd = findViewById(R.id.add_feedback_btn);
        btnview = findViewById(R.id.view_feedback_btn);
        btnup = findViewById(R.id.edit_feedback_btn);
        btndel = findViewById(R.id.delete_feedback_btn);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Feedback");
                //try {
                    if (TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter an Email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(title.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Title", Toast.LENGTH_SHORT).show();
                    else {
                     String Email=   email.getText().toString().trim();
                      String Title = title.getText().toString().trim();
                       String Description =description.getText().toString().trim();

                        fedbk   =new Feedback( Email,  Title, Description) ;


                        String emailid = email.getText().toString();
                        dbref.child(emailid).setValue(fedbk);
                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();


                    }
                //} catch (NumberFormatException e) {
                    //Toast.makeText(getApplicationContext(), "Invalid  Input", Toast.LENGTH_SHORT).show();
                //}

            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String femail = email.getText().toString();
                DatabaseReference showdbref = FirebaseDatabase.getInstance().getReference().child("Feedback").child(femail);
                showdbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()){
                            email.setText(dataSnapshot.child("email").getValue().toString());
                            title.setText(dataSnapshot.child("title").getValue().toString());
                            description.setText(dataSnapshot.child("description").getValue().toString());

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

        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference updbref = FirebaseDatabase.getInstance().getReference().child("Feedback");
                updbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String ffemail = email.getText().toString();
                        String ffddname = title.getText().toString();
                        String ffdes = description.getText().toString();


                        if (dataSnapshot.hasChild(ffemail)){
                            fedbk = new Feedback(ffemail, ffddname, ffdes);
                            dbref = FirebaseDatabase.getInstance().getReference().child("Feedback").child(ffemail);
                            dbref.setValue(fedbk);
                            clearControls();

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

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference delref = FirebaseDatabase.getInstance().getReference().child("Feedback");
                delref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String delemail = email.getText().toString();
                        if (dataSnapshot.hasChild(delemail)){
                            dbref = FirebaseDatabase.getInstance().getReference().child("Feedback").child(delemail);
                            dbref.removeValue();
                            clearControls();

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


}

