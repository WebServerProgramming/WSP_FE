package com.example.front.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragSchedule extends Fragment {
    private MaterialCalendarView calendarView;
    private ArrayList<CalendarDay> calendarDayList = new ArrayList<>(); // month: month - 1

    private HashMap<CalendarDay, String> eventMap = new HashMap<>();
    private View view;

    private Map<CalendarDay, List<Main2Data>> dateMain2Map = new HashMap<>();

    // RecyclerView
    private ArrayList<Main2Data> arrayList;
    private Main2Adapter main2Adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_schedule, container, false);

        recyclerView = view.findViewById(R.id.rv_calendar);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        main2Adapter = new Main2Adapter(arrayList);
        recyclerView.setAdapter(main2Adapter);


        calendarView = view.findViewById(R.id.cv_calendar);

        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            if (selected) {
                arrayList = new ArrayList<>();
                main2Adapter = new Main2Adapter(arrayList);
                recyclerView.setAdapter(main2Adapter);
                List<Main2Data> event = dateMain2Map.get(date);

                if (event != null) {
                    arrayList.addAll(event);
                    main2Adapter.notifyDataSetChanged();
                }
            }
        });

        Main2Data main2Data = new Main2Data(R.drawable.basketball,"투표 종료일: 행사 참가자", CalendarDay.from(2024,9,5));
        addData(main2Data.getCalendarDay(), main2Data);
        main2Data = new Main2Data(R.drawable.tennis,"투표 시작일: 동아리 회식 인원 투표", CalendarDay.from(2024,9,5));
        addData(main2Data.getCalendarDay(), main2Data);

        calendarDayList.add(CalendarDay.from(2024,9,5));
        calendarDayList.add(CalendarDay.from(2024,9,13));
        calendarDayList.add(CalendarDay.from(2024,9,15));

        Decorator decorator = new Decorator(calendarDayList, getActivity());
        calendarView.addDecorator(decorator);

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }

    // 날짜에 문자열을 추가하는 메서드
    public void addData(CalendarDay calendarDay, Main2Data main2Data) {
        // 해당 날짜에 이미 저장된 리스트가 있으면 가져오고, 없으면 새로 만듦
        List<Main2Data> values = dateMain2Map.getOrDefault(calendarDay, new ArrayList<>());
        values.add(main2Data);
        dateMain2Map.put(calendarDay, values);
    }
}
