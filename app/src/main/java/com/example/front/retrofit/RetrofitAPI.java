package com.example.front.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/oauth2/authorization/kakao")
    Call<TokenResponse> getTokens(@Query("accessToken") String accessToken,
                                  @Query("refreshToken") String refreshToken);

    // get registered club
    @GET("/v1/api/club/my")
    Call<ClubResponse> getMyClubList();

    // get user info
    @GET("/v1/api/user")
    Call<UserResponse> getUser();

    // get review for club
    @GET("/v1/api/notice/{clubId}")
    Call<ReviewResponse> getReview(@Path("clubId") int clubId);

    // get notice list
    @GET("v1/api/notice")
    Call<RoomResponse> getRoom();

    // get notice
    @GET("v1/api/notice/{noticeId}")
    Call<NoticeResponse> getNotice(@Path("noticeId") int noticeId);

    // get every club list with rating
    @GET("/v1/api/club")
    Call<ClubListResponse> getAllClubList();
}
