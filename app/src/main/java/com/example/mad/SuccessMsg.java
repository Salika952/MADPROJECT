package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SuccessMsg extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_msg);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String contact = extras.getString("Contact");
        String id = extras.getString("ID");
        //Toast.makeText(this, ""+contact, Toast.LENGTH_SHORT).show();

        txt = findViewById(R.id.textView30);
        String x = (contact+"/n"+id);
        txt.setText(x);

    }
}