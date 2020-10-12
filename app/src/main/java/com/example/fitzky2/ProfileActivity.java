package com.example.fitzky2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    Button chngePwd,accSet;
    DatabaseReference reff2;
    Button deac;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        tv1 = (TextView)findViewById(R.id.username);
        tv1.setText(VariableClass.username);


        chngePwd = (Button)findViewById(R.id.btn_change_pwd);
        chngePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProfileActivity.this,ChangePwdActivity.class);
                startActivity(intent);
            }
        });

        accSet = (Button)findViewById(R.id.btn_account_setting);
        accSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProfileActivity.this,AccountSettingActivity.class);
                startActivity(intent);
            }
        });

        deac = (Button)findViewById(R.id.btn_deactivate);
        reff2 = FirebaseDatabase.getInstance().getReference("Users");
        deac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff2.child(VariableClass.phoneNo).removeValue();
                Intent intent =new Intent(ProfileActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
