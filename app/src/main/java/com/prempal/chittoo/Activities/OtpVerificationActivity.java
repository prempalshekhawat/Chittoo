package com.prempal.chittoo.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.prempal.chittoo.R;

import java.util.concurrent.TimeUnit;

public class OtpVerificationActivity extends AppCompatActivity {

    Button requestOTP, signIn;
    TextView timer;
    EditText otpNumber;
    int time=30;
    private String verificationCode;
    private FirebaseAuth mAuth;
    private final String countryCode = "+91";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        requestOTP = findViewById(R.id.requestOtp);
        requestOTP.setEnabled(false);
        signIn = findViewById(R.id.signIn);
        timer = findViewById(R.id.timer);
        otpNumber = findViewById(R.id.otpInput);
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        String mobile = intent.getStringExtra("mobile");

        signIn.setOnClickListener(new View.OnClickListener() {

            //            String code = otpNumber.getText().toString();
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(otpNumber.getText().toString()) || (otpNumber.getText().toString()).length() < 6) {
                    otpNumber.setError("Enter A Valid OTP");
                    otpNumber.requestFocus();
                }

                else{
                    verifyCode(otpNumber.getText().toString());
                }
            }
        });

        sendVerificationCodeToUser(mobile);

        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("Wait "+"0:"+checkDigit(time));
                time--;
            }
            public void onFinish() {
                timer.setText("Request Another OTP");
                requestOTP.setEnabled(true);
            }
        }.start();

    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    private void SigninWithPhone(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(OtpVerificationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(OtpVerificationActivity.this, "Login successfull!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else {
                            Toast.makeText(OtpVerificationActivity.this, "INVALID OTP", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationCode = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if (code != null || code.length() == 6) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OtpVerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };



    private void sendVerificationCodeToUser(String mobile) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(countryCode+mobile)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(OtpVerificationActivity.this)
                .setCallbacks(mCallbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, code);
        SigninWithPhone(credential);
    }


}