package com.example.front.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.club.ClubReviewAdapter;
import com.example.front.club.ClubReviewData;
import com.example.front.retrofit.RetrofitAPI;
import com.example.front.retrofit.RetrofitClientInstance;
import com.example.front.retrofit.ReviewResponse;
import com.example.front.retrofit.ScheduleDetailResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScheduleDetailActivity extends AppCompatActivity {

    // RecyclerView
    private ArrayList<MainScheduleDetailData> arrayList;
    private MainScheduleDetailAdapter mainScheduleDetailAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private MainScheduleDetailData mainScheduleDetailData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_schedule_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnBack = findViewById(R.id.btn_main_schedule_detail_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.rv_main_schedule_detail);
        gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(gridLayoutManager);

        arrayList = new ArrayList<>();
        mainScheduleDetailAdapter = new MainScheduleDetailAdapter(arrayList);
        recyclerView.setAdapter(mainScheduleDetailAdapter);

        Intent intent = getIntent();

        RetrofitAPI apiService = RetrofitClientInstance.getApiService(this);
        // 동아리 목록 조회 API 호출
        apiService.getScheduleDetail(intent.getIntExtra("noticeId", 1)).enqueue(new Callback<ScheduleDetailResponse>() {
            @Override
            public void onResponse(Call<ScheduleDetailResponse> call, Response<ScheduleDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ScheduleDetailResponse scheduleDetailResponse = response.body();

                    // 결과 로그 출력
                    Log.d("API Result", "Code: " + scheduleDetailResponse.getResult().getCode());
                    Log.d("API Message", "Message: " + scheduleDetailResponse.getResult().getMessage());

                    // 리뷰 목록 출력
                    for (ScheduleDetailResponse.Payload payload : scheduleDetailResponse.getPayload()) {
                        mainScheduleDetailData = new MainScheduleDetailData(payload.getName());
                        arrayList.add(mainScheduleDetailData);
                        Log.d("Name", "Content: " + payload.getName());
                    }
                    mainScheduleDetailAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API Error", "Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ScheduleDetailResponse> call, Throwable t) {
                Log.e("API Error", "Failure: " + t.getMessage());
            }
        });
    }
}