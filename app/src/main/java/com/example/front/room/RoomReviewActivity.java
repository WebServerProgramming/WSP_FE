package com.example.front.room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.front.R;
import com.example.front.club.ClubRegistrationActivity;
import com.example.front.retrofit.RetrofitAPI;
import com.example.front.retrofit.RetrofitClientInstance;
import com.example.front.retrofit.SingleStringResponse;
import com.example.front.retrofit.request.RoomReviewRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomReviewActivity extends AppCompatActivity {
    private EditText etRoomReview;
    private Button btnDone;
    private boolean isEtFilled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_review);

        Button btnBack = findViewById(R.id.btn_room_review_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDone = findViewById(R.id.btn_room_review_done);
        etRoomReview = findViewById(R.id.et_room_review_review);
        RatingBar ratingBar = findViewById(R.id.rb_room_review);

        etRoomReview.addTextChangedListener(textWatcher);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                RoomReviewRequest roomReviewRequest = new RoomReviewRequest(ratingBar.getNumStars(), etRoomReview.getText().toString());

                RetrofitAPI apiService = RetrofitClientInstance.getApiService(RoomReviewActivity.this);
                // 동아리 목록 조회 API 호출
                apiService.postReview(intent.getIntExtra("clubId", 0), roomReviewRequest).enqueue(new Callback<SingleStringResponse>() {
                    @Override
                    public void onResponse(Call<SingleStringResponse> call, Response<SingleStringResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            SingleStringResponse singleStringResponse = response.body();

                            // 결과 로그 출력
                            Log.d("API Result", "Code: " + singleStringResponse.getResult().getCode());
                            Log.d("API Message", "Message: " + singleStringResponse.getResult().getMessage());
                            Log.d("API Payload", "Payload" + singleStringResponse.getPayload());
                        } else {
                            Log.e("API Error", "Response code: " + response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<SingleStringResponse> call, Throwable t) {
                        Log.e("API Error", "Failure: " + t.getMessage());
                    }
                });
                finish();
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            isEtFilled = !etRoomReview.getText().toString().trim().isEmpty();
            checkAllFields();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void checkAllFields() {
        if (isEtFilled) {
            btnDone.setEnabled(true);
        } else {
            btnDone.setEnabled(false);
        }
    }
}