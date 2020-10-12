package com.example.fitzky2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitzky2.Prevalent.Prevalent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ChangePwdActivity extends AppCompatActivity {

String pwd;
String phoneNo;
DatabaseReference reff1;
EditText txtNewPwd;
TextView currPwd;
Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);


        pwd = VariableClass.password;
        phoneNo = VariableClass.phoneNo;
        //tv2.setText(VariableClass.password);
        txtNewPwd = (EditText)findViewById(R.id.btn_new_pwd);
        currPwd = (TextView) findViewById(R.id.btn_curr_pwd);
        currPwd.setText(VariableClass.password);
        save = (Button)findViewById(R.id.btn_update);
        reff1 = FirebaseDatabase.getInstance().getReference("Users");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff1.child(phoneNo).child("password").setValue(txtNewPwd.getText().toString());
                Toast.makeText(ChangePwdActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(ChangePwdActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });




    }
}
