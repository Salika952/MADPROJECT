package com.example.fitzky2.Product;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fitzky2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText id, name, qty, description, type, price;
    Button add, cancel, up;
    ImageView img;
    public Uri imguri;

    private Spinner spinnerItemType;


    DatabaseReference dbref;
    //StorageReference mStorageRef;
    Product prd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        spinnerItemType = findViewById(R.id.spinnerItemType);

        id = findViewById(R.id.txt_uid);
        name = findViewById(R.id.txt_uname);
        qty = findViewById(R.id.txt_uingre);
        description = findViewById(R.id.txt_udescription);

        price = findViewById(R.id.txt_uprice);


        add = findViewById(R.id.btn_uadd);
        cancel = findViewById(R.id.btn_ucancel);
        up = findViewById(R.id.btn_upload);

        String[] textSizes = getResources().getStringArray(R.array.font_sizes);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItemType.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Product");

                String iid = id.getText().toString();
                if (iid.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                String iname = name.getText().toString();
                if (iname.matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                int iingre = Integer.parseInt(qty.getText().toString().trim());
                String idescription = description.getText().toString();
                String itype = spinnerItemType.getSelectedItem().toString();
                int iprice = Integer.parseInt(price.getText().toString().trim());

                prd = new Product(iid, iname, iingre,iprice, idescription, itype);


                dbref.child(iid).setValue(prd);

                Toast.makeText(getApplicationContext(), "Data Added Successfully.", Toast.LENGTH_SHORT).show();
                clearControlls();

            }
        });
    }
    public void clearControlls()
    {
        id.setText("");
        name.setText("");
        qty.setText("");
        description.setText("");
//        type.setText("");
        price.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView.getId() == R.id.spinnerItemType)
        {
            String valuefromSpinner = adapterView.getItemAtPosition(position).toString();
            type.getText().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}