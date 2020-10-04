package com.example.fitzky2.Discount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fitzky2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDiscount extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText txtID,txtName, dtype, txtPercentage,txtPrice;
    Button btnAdd;
    DatabaseReference dbRef;
    Discount dis;

    private Spinner spinnerdistype;

    private void clearControls(){
        txtID.setText("");
        txtName.setText("");
        txtPercentage.setText("");
        txtPrice.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount);

        spinnerdistype = findViewById(R.id.spinnerDisType);
        txtID = findViewById(R.id.dis_ID);
        txtName = findViewById(R.id.dis_Name);
        txtPercentage = findViewById(R.id.dis_Percentage);
        txtPrice = findViewById(R.id.dis_Price);

        btnAdd = findViewById(R.id.btn_Add);


        dis = new Discount();

        String[] textSizes = getResources().getStringArray(R.array.font_sizes);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdistype.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Discount");
                try {
                    if (TextUtils.isEmpty(txtID.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter an ID", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Name", Toast.LENGTH_SHORT).show();
                    else {
                        dis.setdID(txtID.getText().toString().trim());
                        dis.setdName(txtName.getText().toString().trim());
                        dis.setType(spinnerdistype.getSelectedItem().toString());
                        dis.setdPercentage(Integer.parseInt(txtPercentage.getText().toString().trim()));
                        dis.setdPrice(Integer.parseInt(txtPrice.getText().toString().trim()));

                        String dtype = spinnerdistype.getSelectedItem().toString();
                        int per = Integer.parseInt(txtPercentage.getText().toString().trim());
                        int pri = Integer.parseInt(txtPrice.getText().toString().trim());

                        if (0< per){
                            if (per<100){
                                if (0 < pri){
                                    String disid = txtID.getText().toString();
                                    dbRef.child(disid).setValue(dis);
                                    Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                                    clearControls();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Invalid Discount Price", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Invalid Discount Percentage ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Invalid Discount Percentage ", Toast.LENGTH_SHORT).show();
                        }

                        //dbRef.push().setValue(dis);
                        //Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        //clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Discount Percentage or Price", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView.getId() == R.id.spinnerDisType)
        {
            String valuefromSpinner = adapterView.getItemAtPosition(position).toString();
            dtype.getText().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
