package com.example.trendingreposofgithub.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.trendingreposofgithub.model.Repos;
import com.example.trendingreposofgithub.retrofit.GetTrendingRepos;
import com.example.trendingreposofgithub.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//Repository class of MVVM architecture, used to make a call to API using Refrotit2
public class ReposRepository {

    public ReposRepository() {
    }

    public MutableLiveData<List<Repos>> getRepos() {
        final MutableLiveData<List<Repos>> pojoMutableLiveData = new MutableLiveData<>();
        GetTrendingRepos getTrendingRepos = RetrofitClient.getInstance().getTrendingRepos();
        Call<List<Repos>> call = getTrendingRepos.getRepos("kotlin");
        call.enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                if (response.isSuccessful()) {
                    pojoMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Repos>> call, Throwable t) {
                Log.d("ReposRepositoty", "onFailure");
            }
        });
        return pojoMutableLiveData;
    }


}
