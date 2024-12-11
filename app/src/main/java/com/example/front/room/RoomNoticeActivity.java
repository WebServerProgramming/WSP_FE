package com.example.front.room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.front.R;
import com.example.front.retrofit.RetrofitAPI;
import com.example.front.retrofit.RetrofitClientInstance;
import com.example.front.retrofit.NoticeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomNoticeActivity extends AppCompatActivity {
    private TextView tvRoomNoticeTitle;
    private TextView tvRoomNoticeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_notice);

        tvRoomNoticeTitle = findViewById(R.id.tv_room_notice_title);
        tvRoomNoticeContent = findViewById(R.id.tv_room_notice_content);

        Button btnBack = findViewById(R.id.btn_room_notice_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        int APIInput = intent.getIntExtra("noticeId",0);

        RetrofitAPI apiService = RetrofitClientInstance.getApiService(this);
        // 동아리 목록 조회 API 호출
        apiService.getNotice(APIInput).enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NoticeResponse noticeResponse = response.body();

                    // 결과 로그 출력
                    Log.d("API Result", "Code: " + noticeResponse.getResult().getCode());
                    Log.d("API Message", "Message: " + noticeResponse.getResult().getMessage());

                    // 공지 출력
                    tvRoomNoticeTitle.setText(noticeResponse.getPayload().getTitle());
                    tvRoomNoticeContent.setText(noticeResponse.getPayload().getContent());
                    Log.d("Notice", "Title:" + noticeResponse.getPayload().getTitle() + "Content:" + noticeResponse.getPayload().getContent());
                } else {
                    Log.e("API Error", "Response code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {
                Log.e("API Error", "Failure: " + t.getMessage());
            }
        });
    }
    
}