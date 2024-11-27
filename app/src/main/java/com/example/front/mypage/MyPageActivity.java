package com.example.front.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.front.R;
import com.example.front.main.MainRoomData;
import com.example.front.retrofit.UserResponse;
import com.example.front.retrofit.RetrofitAPI;
import com.example.front.retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity {

    private TextView tvMypageNickname;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mypage);
        
        tvMypageNickname = findViewById(R.id.tv_mypage_nickname);

        RetrofitAPI apiService = RetrofitClientInstance.getApiService(this);
        // 동아리 목록 조회 API 호출
        apiService.getUser().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserResponse userResponse = response.body();

                    // 결과 로그 출력
                    Log.d("API Result", "Code: " + userResponse.getResult().getCode());
                    Log.d("API Message", "Message: " + userResponse.getResult().getMessage());

                    // 유저 정보 출력
                    tvMypageNickname.setText(userResponse.getPayload().getName());
                    Log.d("User Info", " Name: " + userResponse.getPayload().getName());

                } else {
                    Log.e("API Error", "Response code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("API Error", "Failure: " + t.getMessage());
            }
        });

        Button btn = findViewById(R.id.btn_mypage_alert);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, MyPageAlertActivity.class);
                startActivity(intent);
            }
        });

        Button btnBack = findViewById(R.id.btn_mypage_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}