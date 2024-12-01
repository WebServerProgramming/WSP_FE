package com.example.front.club;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.main.MainRoomData;
import com.example.front.main.MainScheduleAdapter;
import com.example.front.main.MainScheduleData;
import com.example.front.retrofit.ReviewResponse;
import com.example.front.retrofit.RetrofitAPI;
import com.example.front.retrofit.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubReviewActivity extends AppCompatActivity {
    private RatingBar rbClubReview;

    // RecyclerView
    private ArrayList<ClubReviewData> arrayList;
    private ClubReviewAdapter clubReviewAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private ClubReviewData clubReviewData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_club_review);

        rbClubReview = findViewById(R.id.rb_club_review);

        Button btnBack = findViewById(R.id.btn_club_review_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.rv_club_review);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        clubReviewAdapter = new ClubReviewAdapter(arrayList);
        recyclerView.setAdapter(clubReviewAdapter);

        Intent intent = getIntent();

        RetrofitAPI apiService = RetrofitClientInstance.getApiService(this);
        // 동아리 목록 조회 API 호출
        apiService.getReview(intent.getIntExtra("clubId", 0)).enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ReviewResponse reviewResponse = response.body();

                    // 결과 로그 출력
                    Log.d("API Result", "Code: " + reviewResponse.getResult().getCode());
                    Log.d("API Message", "Message: " + reviewResponse.getResult().getMessage());

                    // 총합 점수 출력
                    rbClubReview.setRating(reviewResponse.getPayload().getTotalRate().floatValue());
                    Log.d("Total Rate", Double.toString(reviewResponse.getPayload().getTotalRate()));

                    // 리뷰 목록 출력
                    for (ReviewResponse.Reviews review : reviewResponse.getPayload().getReviews()) {
                        clubReviewData = new ClubReviewData(review.getContent(), review.getRate());
                        arrayList.add(clubReviewData);
                        Log.d("Review", "Content: " + review.getContent() + ", Rate: " + review.getRate());
                    }
                    clubReviewAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API Error", "Response code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                Log.e("API Error", "Failure: " + t.getMessage());
            }
        });
    }
}