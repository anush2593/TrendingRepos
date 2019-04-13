package com.example.trendingreposofgithub.retrofit;

import android.arch.lifecycle.MutableLiveData;

import com.example.trendingreposofgithub.model.Repos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetTrendingRepos {
    @GET("/repositories")
    Call<List<Repos>> getRepos(@Query("language") String lang);


}
