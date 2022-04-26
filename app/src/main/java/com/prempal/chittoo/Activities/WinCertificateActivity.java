package com.prempal.chittoo.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.prempal.chittoo.R;

public class WinCertificateActivity extends AppCompatActivity {

    TextView answerTimer;
    EditText answerInput;
    Button submitAnswer;
    int time=30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_certificate);

        answerTimer = findViewById(R.id.answerTimer);
        answerInput = findViewById(R.id.answerInput);
        submitAnswer = findViewById(R.id.submitAnswer);
        submitAnswer.setVisibility(View.GONE);
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                answerTimer.setText("0:"+checkDigit(time));
                time--;
            }
            public void onFinish() {
                answerTimer.setText("Time's Up!");
                answerInput.setEnabled(false);
//                submitAnswer.setEnabled(true);
                submitAnswer.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

}