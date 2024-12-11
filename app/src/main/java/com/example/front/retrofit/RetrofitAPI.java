package com.example.front.retrofit;

import com.example.front.retrofit.request.RoomReviewRequest;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    @GET("/v1/api/review/{clubId}")
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

    // get user list for schedule
    @GET("/v1/api/club/{clubId}/people")
    Call<ScheduleDetailResponse> getScheduleDetail(@Path("clubId") int clubId);

    // get ChatGPT response for recommendation
    @GET("/v1/api/chat")
    Call<ChatResponse> getChat(@Query("keyword") String keyword);

    // post review
    @POST("/v1/api/review/{clubId}/save")
    Call<SingleStringResponse> postReview(@Path("clubId") int clubId, @Body RoomReviewRequest request);

    // post club registration
    @POST("/v1/api/club/{clubId}/join")
    Call<SingleStringResponse> postClubRegistration(@Path("clubId") int clubId);

    // get vote
    @GET("/v1/api/vote/{clubId}")
    Call<VoteResponse> getVote(@Path("clubId") int clubId);

    // post vote
    @POST("/v1/api/vote/{voteId}/{itemId}/attend")
    Call<SingleIntResponse> postVote(@Path("voteId") int voteId, @Path("itemId") int itemId);
}
