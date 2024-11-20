package com.example.front.club;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.front.R;

public class ClubRegistrationActivity extends AppCompatActivity {
    private EditText etClubRegistration;
    private Button btnClubRegistrationDone;
    private boolean isEtFilled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_club_registration);

        Button btnBack = findViewById(R.id.btn_club_registration_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        etClubRegistration = findViewById(R.id.et_club_registration);
        btnClubRegistrationDone = findViewById(R.id.btn_club_registration_done);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            isEtFilled = !etClubRegistration.getText().toString().trim().isEmpty();
            checkAllFields();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void checkAllFields() {
        if (isEtFilled) {
            btnClubRegistrationDone.setEnabled(true);
        } else {
            btnClubRegistrationDone.setEnabled(false);
        }
    }
}