package com.example.trendingreposofgithub.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    public static final String BASE_URL = "https://github-trending-api.now.sh";
    private static RetrofitClient retrofitInstance;
    private GetTrendingRepos getTrendingRepos;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.getTrendingRepos = retrofit.create(GetTrendingRepos.class);
    }

    public static RetrofitClient getInstance() {
        if (retrofitInstance == null) {
            retrofitInstance = new RetrofitClient();
        }

        return retrofitInstance;
    }

    public GetTrendingRepos getTrendingRepos() {
        return this.getTrendingRepos;
    }
}
