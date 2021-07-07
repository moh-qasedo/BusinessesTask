package com.example.secondtask.Api;

import com.example.secondtask.Model.YeldResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface YeldApi {

    @GET("businesses/search?term=restaurants&latitude=37.786882&longitude=-122.399972&price=1,2,3&limit=50")
    Observable<YeldResponse> getRestaurants(@Header("Authorization") String authorization);
}

