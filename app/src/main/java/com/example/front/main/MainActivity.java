package com.example.front.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.front.R;
import com.example.front.mypage.MyPageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnMainMypage;

    // BottomNavigationView
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private FragRoom fragRoom;
    private FragSchedule fragSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnMainMypage = findViewById(R.id.btn_main_mypage);
        bottomNavigationView = findViewById(R.id.bnv_main);

        btnMainMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_room) {
                    setFrag(0);
                }
                else if (item.getItemId() == R.id.menu_calendar) {
                    setFrag(1);
                }
                return true;
                // return false;
            }
        });

        // init fragment
        fragRoom = new FragRoom();
        fragSchedule = new FragSchedule();

        setFrag(0);
    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.fl_main, fragRoom);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.fl_main, fragSchedule);
                ft.commit();
                break;
        }
    }
}