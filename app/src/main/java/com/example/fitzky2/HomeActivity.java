package com.example.fitzky2;

import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class HomeActivity extends AppCompatActivity {
Button profile,feedback;
    DatabaseReference RootRef;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        tv1 = (TextView)findViewById(R.id.home_username);
        tv1.setText(VariableClass.username);
        profile = (Button) findViewById(R.id.btn_profile);
        feedback=(Button) findViewById(R.id.btn_feedback);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });






    }
}
