package com.example.fitzky2.paymnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fitzky2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verify_OTP extends AppCompatActivity {

    String verificationCodeBySystem;
    Button verify_btn;
    EditText phoneNoEnteredByThecustomers;
    ProgressBar progressBar;
    String contact,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify__o_t_p);

        verify_btn = findViewById(R.id.btnVerify);
        phoneNoEnteredByThecustomers = findViewById(R.id.ET_otp);
        progressBar = findViewById(R.id.progress_bar);

        progressBar.setVisibility((View.GONE));


        //Contact = getIntent().getStringExtra("Contact");
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        contact = extras.getString("Contact");
        id = extras.getString("ID");

        sendVerificationCodeToCustomer(contact);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=phoneNoEnteredByThecustomers.getText().toString();

                if(code.isEmpty() || code.length()<6){
                    phoneNoEnteredByThecustomers.setError("Wrong OTP..");
                    phoneNoEnteredByThecustomers.requestFocus();
                    return;
                }
                progressBar.setVisibility((View.VISIBLE));
                verifycode(code);
            }
        });
    }

    private void sendVerificationCodeToCustomer(String Contact) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+94" + Contact,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationCodeBySystem=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            String code=phoneAuthCredential.getSmsCode();
            if(code!=null){
                progressBar.setVisibility(View.VISIBLE);
                verifycode(code);

            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Verify_OTP.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };

    private void verifycode(String codeByUser){
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationCodeBySystem,codeByUser);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(Verify_OTP.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(),SuccessMsg.class);
                            //intent.putExtra("ContactVerify",Contact);
                            //startActivity(intent);
                            Bundle extras = new Bundle();
                            extras.putString("Contact",contact);
                            extras.putString("ID",id);
                            intent.putExtras(extras);
                            startActivity(intent);



                            //Intent intent=new Intent(getApplicationContext(),successMassage.class);
                            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            //startActivity(intent);
                        }else{
                            Toast.makeText(Verify_OTP.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
