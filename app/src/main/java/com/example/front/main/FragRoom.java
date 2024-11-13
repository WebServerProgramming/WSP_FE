package com.example.front.main;

import static com.example.front.R.drawable;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

public class FragRoom extends Fragment {

    private View view;

    // RecyclerView
    private ArrayList<MainRoomData> arrayList;
    private MainRoomAdapter mainRoomAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

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



        MainRoomData mainRoomData = new MainRoomData(drawable.basketball,"예시");
        arrayList.add(mainRoomData);
        mainRoomAdapter.notifyDataSetChanged();

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
