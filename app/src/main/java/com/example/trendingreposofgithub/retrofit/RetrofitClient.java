package com.example.trendingreposofgithub.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitClient {
    public static final String BASE_URL = "https://github-trending-api.now.sh";
    private static Retrofit retrofitInstance;

    private RetrofitClient(){}

    public static Retrofit getInstance(){
        if (retrofitInstance == null){
            retrofitInstance =  new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitInstance;
    }


}
