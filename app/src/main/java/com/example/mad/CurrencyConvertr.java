package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Spliterator;

public class CurrencyConvertr extends AppCompatActivity {

    Spinner sp1,sp2;
    EditText ed1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_convertr);

        ed1 = findViewById(R.id.txtAmount);
        sp1 = findViewById(R.id.spfrom);
        sp2 = findViewById(R.id.spto);
        b1 = findViewById(R.id.btncnvrt);

        String[] from = {"USD"};
        ArrayAdapter ad = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, from);
        sp1.setAdapter(ad);

        String[] to = {"Srilankan Rupess"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, to);
        sp2.setAdapter(ad1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double tot;
                Double amount = Double.parseDouble(ed1.getText().toString());
                if (sp1.getSelectedItem().toString() == "USD" && sp2.getSelectedItem().toString() == "Indian Rupess") {
                    tot = amount * 70.0;
                    Toast.makeText(getApplicationContext(), tot.toString(), Toast.LENGTH_SHORT).show();
                } else if (sp1.getSelectedItem().toString() == "USD" && sp2.getSelectedItem().toString() == "Srilankan Rupess") {
                    tot = amount * 180.0;
                    Toast.makeText(getApplicationContext(), tot.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}