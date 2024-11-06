package com.example.front.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.front.R;
import com.example.front.main.MainActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        ImageView iv = findViewById(R.id.iv_login_kakao);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LoginSelectionActivity.class);
                startActivity(intent);
            }
        });
        /*iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 카카오 로그인 URL
                String kakaoAuthUrl = "https://chawoomi.link/oauth2/authorization/kakao";

                // CustomTabsIntent 설정
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setShowTitle(true);
                CustomTabsIntent customTabsIntent = builder.build();

                // Custom Tab으로 카카오 로그인 페이지 열기
                customTabsIntent.launchUrl(LoginActivity.this, Uri.parse(kakaoAuthUrl));
            }
        });
        /*cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginSelectionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 여기에서 리디렉션 URL을 처리하기 위해 Intent를 받을 수 있습니다.
        Intent intent = getIntent();
        Uri data = intent.getData();

        if (data != null && data.toString().contains("code=")) {
            String authorizationCode = data.getQueryParameter("code");
            handleAuthorizationCode(authorizationCode);
        }
    }

    private void handleAuthorizationCode(String code) {
        Intent intent = new Intent(this,LoginSelectionActivity.class);
        startActivity(intent);
        // 받은 authorization code로 액세스 토큰을 요청하는 로직을 구현합니다.
    }

}