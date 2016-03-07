package com.taslak.moviediary;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface IMovie {
    @GET("/")
    void getMovie(@Query("i") String imdbId, Callback<Movie> callbackMovie);
}
