package com.example.front.main;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.front.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class FragCalendar extends Fragment {
    private MaterialCalendarView calendarView;
    private TextView eventTextView;
    private ArrayList<CalendarDay> calendarDayList = new ArrayList<>(); // month: month - 1

    private HashMap<CalendarDay, String> eventMap = new HashMap<>();
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main2, container, false);

        calendarView = view.findViewById(R.id.cv_calendar);
        eventTextView = view.findViewById(R.id.tv_calendar_2);

        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            if (selected) {
                String event = eventMap.get(date);
                if (event != null) {
                    eventTextView.setText(event);
                } else {
                    eventTextView.setText("선택된 날짜에는 일정이 없습니다.");
                }
            }
        });
        calendarDayList.add(CalendarDay.from(2024,9,5));
        calendarDayList.add(CalendarDay.from(2024,9,13));
        calendarDayList.add(CalendarDay.from(2024,9,15));
        eventMap.put(calendarDayList.get(0), "투표 시작일: 정기 모임 참석 여부");
        eventMap.put(calendarDayList.get(1), "투표 마감일: 정기 모임 참석 여부");
        eventMap.put(calendarDayList.get(2), "투표 시작일: 정기 모임 참석 여부");
        Decorator decorator = new Decorator(calendarDayList, getActivity());
        calendarView.addDecorator(decorator);

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }
}
