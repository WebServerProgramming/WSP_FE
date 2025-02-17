package com.example.front.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.retrofit.CalendarResponse;
import com.example.front.retrofit.RetrofitAPI;
import com.example.front.retrofit.RetrofitClientInstance;
import com.example.front.retrofit.RoomResponse;
import com.example.front.room.RoomData;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragSchedule extends Fragment {
    private View view;
    private MaterialCalendarView calendarView;
    private ArrayList<CalendarDay> calendarDayList = new ArrayList<>(); // month: month - 1

    private Map<CalendarDay, List<MainScheduleData>> dateMain2Map = new HashMap<>();

    // RecyclerView
    private ArrayList<MainScheduleData> arrayList;
    private MainScheduleAdapter mainScheduleAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_schedule, container, false);

        dateMain2Map = new HashMap<>();

        recyclerView = view.findViewById(R.id.rv_main_schedule);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        mainScheduleAdapter = new MainScheduleAdapter(arrayList);
        recyclerView.setAdapter(mainScheduleAdapter);


        calendarView = view.findViewById(R.id.cv_main_schedule);

        Calendar currentDate = Calendar.getInstance();
        calendarView.setSelectedDate(currentDate.getTime());

        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            if (selected) {
                arrayList = new ArrayList<>();
                mainScheduleAdapter = new MainScheduleAdapter(arrayList);
                recyclerView.setAdapter(mainScheduleAdapter);
                List<MainScheduleData> event = dateMain2Map.get(date);

                if (event != null) {
                    arrayList.addAll(event);
                    mainScheduleAdapter.notifyDataSetChanged();
                }
            }
        });

        MainScheduleData mainScheduleData = new MainScheduleData(R.drawable.basketball, "투표 종료일: 행사 참가자", CalendarDay.from(2024, 10, 5));
        addData(mainScheduleData.getCalendarDay(), mainScheduleData);
        mainScheduleData = new MainScheduleData(R.drawable.tennis, "투표 시작일: 동아리 회식 인원 투표", CalendarDay.from(2024, 10, 5));
        addData(mainScheduleData.getCalendarDay(), mainScheduleData);

        calendarDayList.add(CalendarDay.from(2024, 10, 5));
        calendarDayList.add(CalendarDay.from(2024, 10, 13));
        calendarDayList.add(CalendarDay.from(2024, 10, 15));

        Decorator decorator = new Decorator(calendarDayList, getActivity());
        calendarView.addDecorator(decorator);


        RetrofitAPI apiService = RetrofitClientInstance.getApiService(getActivity());
        // 동아리 목록 조회 API 호출
        apiService.getAllCalendar().enqueue(new Callback<CalendarResponse>() {
            @Override
            public void onResponse(Call<CalendarResponse> call, Response<CalendarResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CalendarResponse calendarResponse = response.body();

                    // 결과 로그 출력
                    Log.d("API Result", "Code: " + calendarResponse.getResult().getCode());
                    Log.d("API Message", "Message: " + calendarResponse.getResult().getMessage());
                    for (String payload : calendarResponse.getPayload()) {
                        Log.d("Review", "Payload: " + payload);
                    }
                } else {
                    Log.e("API Error", "Response code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<CalendarResponse> call, Throwable t) {
                Log.e("API Error", "Failure: " + t.getMessage());
            }
        });

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }

    // 날짜에 문자열을 추가하는 메서드
    public void addData(CalendarDay calendarDay, MainScheduleData mainScheduleData) {
        // 해당 날짜에 이미 저장된 리스트가 있으면 가져오고, 없으면 새로 만듦
        List<MainScheduleData> values = dateMain2Map.getOrDefault(calendarDay, new ArrayList<>());
        values.add(mainScheduleData);
        dateMain2Map.put(calendarDay, values);
    }
}
