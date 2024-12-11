package com.example.front.room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.retrofit.RetrofitAPI;
import com.example.front.retrofit.RetrofitClientInstance;
import com.example.front.retrofit.RoomResponse;
import com.example.front.retrofit.SingleIntResponse;
import com.example.front.retrofit.UserResponse;
import com.example.front.retrofit.VoteResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomVoteActivity extends AppCompatActivity {


    // RecyclerView
    private ArrayList<RoomVoteData> arrayList;
    private RoomVoteAdapter roomVoteAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_vote);


        recyclerView = findViewById(R.id.rv_room_vote);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        roomVoteAdapter = new RoomVoteAdapter(arrayList);
        recyclerView.setAdapter(roomVoteAdapter);

        arrayList.add(new RoomVoteData("투표", 1));
        arrayList.add(new RoomVoteData("행사", 2));
        arrayList.add(new RoomVoteData("정기행사", 3));
        roomVoteAdapter.notifyDataSetChanged();

        Button btnBack = findViewById(R.id.btn_room_vote_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnDone = findViewById(R.id.btn_room_vote_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(roomVoteAdapter.selectedVoteId == -1) {
                    return;
                }
                Intent intent = getIntent();
                RetrofitAPI apiService = RetrofitClientInstance.getApiService(RoomVoteActivity.this);
                // 동아리 목록 조회 API 호출
                apiService.postVote(intent.getIntExtra("noticeId",0),roomVoteAdapter.selectedVoteId).enqueue(new Callback<SingleIntResponse>() {
                    @Override
                    public void onResponse(Call<SingleIntResponse> call, Response<SingleIntResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            SingleIntResponse singleIntResponse = response.body();

                            // 결과 로그 출력
                            Log.d("API Result", "Code: " + singleIntResponse.getResult().getCode());
                            Log.d("API Message", "Message: " + singleIntResponse.getResult().getMessage());

                            Log.d("User Info", " Name: " + singleIntResponse.getPayload());

                        } else {
                            Log.e("API Error", "Response code: " + response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<SingleIntResponse> call, Throwable t) {
                        Log.e("API Error", "Failure: " + t.getMessage());
                    }
                });
                finish();
            }
        });

        Intent intent = getIntent();
        String TAG = "API vote";
        RetrofitAPI apiService = RetrofitClientInstance.getApiService(this);
        // API 호출
        apiService.getVote(intent.getIntExtra("clubId",0)).enqueue(new Callback<VoteResponse>() {
            @Override
            public void onResponse(Call<VoteResponse> call, Response<VoteResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    VoteResponse voteResponse = response.body();
                    // 로그로 출력
                    Log.d(TAG, "Code: " + voteResponse.getResult().getCode());
                    Log.d(TAG, "Message: " + voteResponse.getResult().getMessage());

                    for (VoteResponse.Payload payload : voteResponse.getPayload()) {
                        Log.d(TAG, "Vote ID: " + payload.getVote().getId());
                        Log.d(TAG, "Vote Title: " + payload.getVote().getTitle());
                        Log.d(TAG, "User Name: " + payload.getVote().getUser().getName());
                        Log.d(TAG, "Club Name: " + payload.getVote().getClub().getClubName());

                        List<VoteResponse.Items> items = payload.getItems();
                        for (VoteResponse.Items item : items) {
                            Log.d(TAG, "Item ID: " + item.getId());
                            Log.d(TAG, "Item Name: " + item.getName());
                        }
                    }
                } else {
                    Log.e(TAG, "Response Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<VoteResponse> call, Throwable t) {
                Log.e(TAG, "API Call Failed: " + t.getMessage(), t);
            }
        });
    }
}