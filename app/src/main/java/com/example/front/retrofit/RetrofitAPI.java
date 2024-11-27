package com.example.front.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/oauth2/authorization/kakao")
    Call<TokenResponse> getTokens(@Query("accessToken") String accessToken,
                                  @Query("refreshToken") String refreshToken);

    @GET("/v1/api/club")
    Call<ClubResponse> getClubList();
}
