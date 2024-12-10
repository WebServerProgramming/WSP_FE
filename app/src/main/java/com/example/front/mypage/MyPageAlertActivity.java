package com.example.front.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.front.R;

public class MyPageAlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mypage_alert);

        RadioGroup radioGroupNew = findViewById(R.id.rg_mypage_alert_new);
        RadioGroup radioGroupRemind = findViewById(R.id.rg_mypage_alert_remind);
        RadioButton rbNewOn = findViewById(R.id.rb_mypage_alert_new_on);
        RadioButton rbNewOff = findViewById(R.id.rb_mypage_alarm_new_off);
        RadioButton rbRemindOn = findViewById(R.id.rb_mypage_alert_remind_on);
        RadioButton rbRemindOff = findViewById(R.id.rb_mypage_alert_remind_off);

        String alertNew = getSharedPreferences("alert", Context.MODE_PRIVATE)
                .getString("alertNew", "on");
        String alertRemind = getSharedPreferences("alert", Context.MODE_PRIVATE)
                .getString("alertRemind", "on");

        if (alertNew.equals("on")) {
            rbNewOn.setChecked(true);
        }
        else rbNewOff.setChecked(true);

        if (alertRemind.equals("on")) {
            rbRemindOn.setChecked(true);
        }
        else rbRemindOff.setChecked(true);

        Button btnBack = findViewById(R.id.btn_mypage_alert_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnDone = findViewById(R.id.btn_mypage_alert_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String preferencesNew, preferencesRemind;
                if (rbNewOn.isChecked()) {
                    preferencesNew = "on";
                }
                else preferencesNew = "off";
                if (rbRemindOn.isChecked()) {
                    preferencesRemind = "on";
                }
                else preferencesRemind = "off";

                getSharedPreferences("alert", MODE_PRIVATE).edit()
                        .putString("alertNew", preferencesNew)
                        .putString("alertRemind", preferencesRemind)
                        .apply();
                finish();
            }
        });



    }
}