package com.prempal.chittoo.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.prempal.chittoo.R;

public class SignUpActivity extends AppCompatActivity {

    TextView loginLink;
    Button signUp;
    EditText firstName, lastName, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginLink = findViewById(R.id.loginLink);
        signUp = findViewById(R.id.signUp);
        firstName = findViewById(R.id.signUpFirstNameInput);
        lastName = findViewById(R.id.signUpLastNameInput);
        password = findViewById(R.id.signUpPasswordInput);
        email = findViewById(R.id.signUpEmailInput);

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}