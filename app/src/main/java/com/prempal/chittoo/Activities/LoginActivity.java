package com.prempal.chittoo.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.prempal.chittoo.R;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    TextView registerLink;
    Button getOTP;
    EditText phoneNumber;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        registerLink = findViewById(R.id.registerLink);
        getOTP = findViewById(R.id.getOTP);
        phoneNumber = findViewById(R.id.loginPhoneInput);
        
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validateMobile(phoneNumber.getText().toString())){
                    getOTP.setEnabled(true);
                }else {
                    getOTP.setEnabled(false);
                    phoneNumber.setError("Invalid Mobile Number");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getOTP.setOnClickListener(new View.OnClickListener() {

            //            String mobile = phoneNumber.getText().toString();
            @Override
            public void onClick(View v) {
                if (validateMobile(phoneNumber.getText().toString())) {
                    getOTP.setEnabled(true);
                    Intent intent = new Intent(LoginActivity.this, OtpVerificationActivity.class);
                    intent.putExtra("mobile", phoneNumber.getText().toString());
                    startActivity(intent);
                }
            }
        });
        
    }

    boolean validateMobile(String phone){
        Pattern p = Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}