package com.example.firstprogramworldskills;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/api/sendCode")
    Call<Email> getEmailInformation(@Header("email") String email);
}
