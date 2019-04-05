package com.example.trendingreposofgithub.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.trendingreposofgithub.repository.ReposRepository;
import com.example.trendingreposofgithub.model.Repos;
import java.util.List;

public class ReposViewModel extends ViewModel {
    private final MutableLiveData<Repos> selected = new MutableLiveData<>();
    private MutableLiveData<List<Repos>> data;
    private ReposRepository reposRepository;

    ReposViewModel(){
        reposRepository = new ReposRepository();
    }

    public void init() {
        if (this.data != null) {
            return;
        }
       data = reposRepository.getRepos();
    }


    public MutableLiveData<List<Repos>> getRepos() {
        return this.data;
    }



}
