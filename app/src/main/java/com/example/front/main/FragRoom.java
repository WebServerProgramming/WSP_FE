package com.example.front.main;

import static com.example.front.R.drawable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.club.ClubListActivity;
import com.example.front.retrofit.ClubResponse;
import com.example.front.retrofit.RetrofitAPI;
import com.example.front.retrofit.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragRoom extends Fragment {

    private View view;

    // RecyclerView
    private ArrayList<MainRoomData> arrayList;
    private MainRoomAdapter mainRoomAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private MainRoomData mainRoomData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_club, container, false);

        recyclerView = view.findViewById(R.id.rv_main_club);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        mainRoomAdapter = new MainRoomAdapter(arrayList);
        recyclerView.setAdapter(mainRoomAdapter);

        RetrofitAPI apiService = RetrofitClientInstance.getApiService(getContext());
        // 동아리 목록 조회 API 호출
        apiService.getMyClubList().enqueue(new Callback<ClubResponse>() {
            @Override
            public void onResponse(Call<ClubResponse> call, Response<ClubResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ClubResponse clubResponse = response.body();

                    // 결과 로그 출력
                    Log.d("API Result", "Code: " + clubResponse.getResult().getCode());
                    Log.d("API Message", "Message: " + clubResponse.getResult().getMessage());

                    // 동아리 목록 출력
                    for (ClubResponse.Club club : clubResponse.getPayload()) {
                        mainRoomData = new MainRoomData(club.getId(), drawable.basketball, club.getName());
                        arrayList.add(mainRoomData);
                        Log.d("Club", "ID: " + club.getId() + ", Name: " + club.getName());
                    }
                    mainRoomAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API Error", "Response code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<ClubResponse> call, Throwable t) {
                Log.e("API Error", "Failure: " + t.getMessage());
            }
        });
        Log.d("List", arrayList.toString());


        Button btn = view.findViewById(R.id.btn_main_club_create);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClubListActivity.class);
                startActivity(intent);
            }
        });

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }


}
