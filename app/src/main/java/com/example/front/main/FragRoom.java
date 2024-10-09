package com.example.front.main;

import static com.example.front.R.*;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import java.util.ArrayList;

public class FragRoom extends Fragment {

    private View view;

    // RecyclerView
    private ArrayList<Main1Data> arrayList;
    private Main1Adapter main1Adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main1, container, false);

        recyclerView = view.findViewById(R.id.rv_main1);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        main1Adapter = new Main1Adapter(arrayList);
        recyclerView.setAdapter(main1Adapter);


        Button btnMain1Add = view.findViewById(R.id.btn_main1_add);

        Main1Data main1Data = new Main1Data(drawable.basketball,"예시");
        arrayList.add(main1Data);
        main1Adapter.notifyDataSetChanged();


        btnMain1Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RoomListActivity.class);
                startActivity(intent);
            }
        });

        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }


}
