package com.example.trendingreposofgithub;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.trendingreposofgithub.model.Repos;
import com.example.trendingreposofgithub.ui.CustomAdapter;
import com.example.trendingreposofgithub.view_model.ReposViewModel;
import java.util.ArrayList;
import java.util.List;


// An activity representing a list of trending repos for a week.
//In the real-world app this activity should have
// //different presentations for handset and tablet-size devices.

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ReposViewModel viewModel;
    private List<Repos> reposList = new ArrayList<>();
    private CustomAdapter mDataAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repo_list_activity);
        progressBar = findViewById(R.id.progressBar_cyclic);

        if(!isNetworkAvailable()){
            Toast.makeText(this, "Please connect to network...", Toast.LENGTH_SHORT).show();
        } else {
            //fetches the data from api, I have used the "weekly" string
            //cause there were two options of trending repos daily and weekly.
            //in the real-world app this would be String choice = (get the input from edittext, button, text, etc.)
            generateTrendingRepos(reposList);


        }
    }

    public void generateTrendingRepos( final List<Repos> repoList) {

        mRecyclerView = findViewById(R.id.recycler_view);
        mDataAdapter = new CustomAdapter(repoList);
        viewModel = ViewModelProviders.of(MainActivity.this).get(ReposViewModel.class);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this,
                getResources().getInteger(R.integer.numberOfColumns));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mDataAdapter);

        viewModel.init();
        viewModel.getRepos().observe(this, new Observer<List<Repos>>() {
                @Override
                public void onChanged(@Nullable List<Repos> repos) {
                    repoList.addAll(repos);
                    mDataAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);

                }
            });


    }

    //checks if network is available
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}