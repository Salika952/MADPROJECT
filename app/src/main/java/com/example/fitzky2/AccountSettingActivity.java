package com.example.fitzky2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountSettingActivity extends AppCompatActivity {
    DatabaseReference reff2;

    EditText txtNewUn;
    TextView currUn;
    Button update,deac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        currUn = (TextView) findViewById(R.id.btn_curr_username);
        currUn.setText(VariableClass.username);
        txtNewUn = (EditText)findViewById(R.id.btn_new_username);
        update = (Button)findViewById(R.id.btn_update_Details);

        reff2 = FirebaseDatabase.getInstance().getReference("Users");
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff2.child(VariableClass.phoneNo).child("name").setValue(txtNewUn.getText().toString());
                VariableClass.username=txtNewUn.getText().toString();
                Toast.makeText(AccountSettingActivity.this, "Username changed successfully", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(AccountSettingActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });



    }
}
