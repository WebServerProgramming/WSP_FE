package com.example.front.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.front.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String AUTH_URL = "https://chawoomi.link/oauth2/authorization/kakao";
    private static final String CALLBACK_URL = "https://chawoomi.link/oauth2/callback/token";

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        webView = findViewById(R.id.webView);
        ImageView iv = findViewById(R.id.iv_login_kakao);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.setVisibility(View.VISIBLE);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(@NonNull WebView view, @NonNull WebResourceRequest request) {
                        String url = request.getUrl().toString();

                        if (url.startsWith(CALLBACK_URL)) {
                            handleCallbackUrl(url);
                            return true;
                        }
                        return super.shouldOverrideUrlLoading(view, request);
                    }
                });
                webView.loadUrl(AUTH_URL);
            }
        });
    }

    private void handleCallbackUrl(String url) {
        Log.d(TAG, "Callback URL: " + url);
        webView.setVisibility(View.INVISIBLE);
        String accessToken = getQueryParam(url, "accessToken");
        String refreshToken = getQueryParam(url, "refreshToken");

        if (accessToken != null && refreshToken != null) {
            Log.d(TAG, "Access Token: " + accessToken);
            Log.d(TAG, "Refresh Token: " + refreshToken);
            saveTokens(accessToken, refreshToken);
        } else {
            Log.e(TAG, "failed token parsing");
        }
    }

    private String getQueryParam(String url, String paramName) {
        Pattern pattern = Pattern.compile(paramName + "=([^&]+)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private void saveTokens(String accessToken, String refreshToken) {
        getSharedPreferences("auth", MODE_PRIVATE).edit()
                .putString("access_token", accessToken)
                .putString("refresh_token", refreshToken)
                .apply();
        Log.i(TAG, "token saved");
        Intent intent = new Intent(LoginActivity.this, LoginSelectionActivity.class);
        startActivity(intent);
    }
}