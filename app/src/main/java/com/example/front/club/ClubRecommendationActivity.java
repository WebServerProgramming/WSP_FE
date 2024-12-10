package com.example.front.club;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.main.MainScheduleDetailAdapter;
import com.example.front.main.MainScheduleDetailData;
import com.example.front.retrofit.ChatResponse;
import com.example.front.retrofit.ClubListResponse;
import com.example.front.retrofit.RetrofitAPI;
import com.example.front.retrofit.RetrofitClientInstance;
import com.google.android.material.internal.TextWatcherAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubRecommendationActivity extends AppCompatActivity {
    // RecyclerView
    private ArrayList<ClubRecommendationData> arrayList;
    private ClubRecommnedationAdapter clubRecommendationAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private EditText etClubRecommendation;
    private ImageButton btnClubRecommendationDone;
    private boolean isEtFilled;

    private boolean isWorking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_club_recommendation);

        Button btnBack = findViewById(R.id.btn_club_recommendation_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        etClubRecommendation = findViewById(R.id.et_club_recommendation);
        btnClubRecommendationDone = findViewById(R.id.btn_club_recommendation_done);

        btnClubRecommendationDone.setEnabled(false);
        etClubRecommendation.addTextChangedListener(textWatcher);

        // recyclerView
        recyclerView = findViewById(R.id.rv_club_recommendation);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        clubRecommendationAdapter = new ClubRecommnedationAdapter(arrayList);
        recyclerView.setAdapter(clubRecommendationAdapter);

        isWorking = false;
        btnClubRecommendationDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWorking) {
                    return;
                }
                isWorking = true;
                String temp = etClubRecommendation.getText().toString();
                ClubRecommendationData clubRecommendationData =
                        new ClubRecommendationData(temp,true);
                arrayList.add(clubRecommendationData);
                clubRecommendationAdapter.notifyItemInserted(arrayList.size());
                etClubRecommendation.setText("");

                RetrofitAPI apiService = RetrofitClientInstance.getApiService(ClubRecommendationActivity.this);
                // 동아리 목록 조회 API 호출
                apiService.getChat(temp).enqueue(new Callback<ChatResponse>() {
                    @Override
                    public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ChatResponse chatResponse = response.body();

                            // 결과 로그 출력
                            Log.d("API Result", "Code: " + chatResponse.getResult().getCode());
                            Log.d("API Message", "Message: " + chatResponse.getResult().getMessage());

                            // GPT 답변 추가
                            ClubRecommendationData clubRecommendationDataAI = new ClubRecommendationData(chatResponse.getPayload(), false);
                            arrayList.add(clubRecommendationDataAI);
                            clubRecommendationAdapter.notifyItemInserted(arrayList.size());
                        } else {
                            Log.e("API Error", "Response code: " + response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<ChatResponse> call, Throwable t) {
                        Log.e("API Error", "Failure: " + t.getMessage());
                    }
                });
                isWorking = false;
            }
        });

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            isEtFilled = !etClubRecommendation.getText().toString().trim().isEmpty();
            checkAllFields();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void checkAllFields() {
        if (isEtFilled && !isWorking) {
            btnClubRecommendationDone.setEnabled(true);
        } else {
            btnClubRecommendationDone.setEnabled(false);
        }
    }


}