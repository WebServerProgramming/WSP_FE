package com.example.front.club;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.main.MainRoomData;
import com.example.front.retrofit.ClubListResponse;
import com.example.front.retrofit.RetrofitAPI;
import com.example.front.retrofit.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubListActivity extends AppCompatActivity {

    // RecyclerView
    private ArrayList<ClubListData> arrayList;
    private ClubListAdapter clubListAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private ClubListData clubListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_club_list);

        recyclerView = findViewById(R.id.rv_club_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        clubListAdapter = new ClubListAdapter(arrayList);
        recyclerView.setAdapter(clubListAdapter);

        RetrofitAPI apiService = RetrofitClientInstance.getApiService(this);
        // 동아리 목록 조회 API 호출
        apiService.getAllClubList().enqueue(new Callback<ClubListResponse>() {
            @Override
            public void onResponse(Call<ClubListResponse> call, Response<ClubListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ClubListResponse clubListResponse = response.body();

                    // 결과 로그 출력
                    Log.d("API Result", "Code: " + clubListResponse.getResult().getCode());
                    Log.d("API Message", "Message: " + clubListResponse.getResult().getMessage());

                    // 동아리 목록 출력
                    for (ClubListResponse.Club club : clubListResponse.getPayload()) {
                        clubListData = new ClubListData(R.drawable.basketball, club.getName(), "수정사항",
                                "★" + String.format("%.2f", club.getRate()), club.getId());
                        arrayList.add(clubListData);
                        Log.d("Club", "ID: " + club.getId() + ", Name: " + club.getName());
                    }
                    clubListAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API Error", "Response code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<ClubListResponse> call, Throwable t) {
                Log.e("API Error", "Failure: " + t.getMessage());
            }
        });

        Button btnBack = findViewById(R.id.btn_club_list_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}