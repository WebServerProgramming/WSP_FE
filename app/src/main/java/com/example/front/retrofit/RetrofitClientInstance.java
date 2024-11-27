package com.example.front.retrofit;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://chawoomi.link";

    public static Retrofit getRetrofitInstance(Context context) {
        if (retrofit == null) {
            // Logging Interceptor 추가
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Authorization 헤더를 추가하는 Interceptor
            Interceptor authInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // SharedPreferences에서 토큰 가져오기
                    String accessToken = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                            .getString("access_token", null);
                    Log.d("Access Token", "Token: " + accessToken);


                    // Authorization 헤더 추가
                    Request.Builder requestBuilder = original.newBuilder();
                    if (accessToken != null) {
                        requestBuilder.header("Authorization", "Bearer " + accessToken);
                    }

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            };

            // OkHttpClient에 Interceptors 추가
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging) // 로깅 인터셉터
                    .addInterceptor(authInterceptor) // Authorization 헤더 추가 인터셉터
                    .build();

            // Retrofit 인스턴스 생성
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RetrofitAPI getApiService(Context context) {
        return getRetrofitInstance(context).create(RetrofitAPI.class);
    }
}
