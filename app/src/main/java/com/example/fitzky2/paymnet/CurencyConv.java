package com.example.fitzky2.paymnet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitzky2.R;

public class CurencyConv extends AppCompatActivity {

    TextView txtResult;
    EditText etvalue;
    RadioButton USI,SriLanka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curency_conv);

        etvalue=findViewById(R.id.txtAmount);
        txtResult=findViewById(R.id.txtResult);
        Button btnConvrt = (Button)findViewById(R.id.btncnvrt);
        USI=findViewById(R.id.radioButton1);
        SriLanka=findViewById(R.id.radioButton2);

        btnConvrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etvalue.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Please enter a number : ",Toast.LENGTH_SHORT).show();
                    return;
                }
                float inputValue=Float.parseFloat(etvalue.getText().toString());
                if(USI.isChecked()){
                    txtResult.setText("Result: "+" $"+ (convertToUSI(inputValue)));
                    USI.setChecked(true);
                    SriLanka.setChecked(false);
                }
                else{
                    txtResult.setText("Result :" +"Rs." +(convertToSriLanka(inputValue)));
                    SriLanka.setChecked(true);
                    USI.setChecked(false);
                }
            }
        });
    }
    public static float convertToUSI(float inputValue) {
        return (inputValue/180);
    }
    public static float convertToSriLanka(float inputValue) {
        return (inputValue*180);
    }




}
