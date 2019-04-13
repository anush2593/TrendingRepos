package com.example.trendingreposofgithub.ui;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.trendingreposofgithub.R;
import com.example.trendingreposofgithub.model.Repos;
import com.example.trendingreposofgithub.view_model.ReposViewModel;

import java.util.ArrayList;
import java.util.List;


public class DetailsFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    private Repos repo;
    private List<Repos> reposList = new ArrayList<>();
    private View rootView;
    private ReposViewModel viewModel;

    public DetailsFragment() {
    }

    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(DetailsFragment.this).get(ReposViewModel.class);
        if (getArguments().containsKey(ARG_ITEM_ID)) {

            viewModel.init();
            viewModel.getRepos().observe(this, new Observer<List<Repos>>() {
                @Override
                public void onChanged(@Nullable List<Repos> repos) {
                    progressBar = getActivity().findViewById(R.id.progressBar_cyclic);

                    reposList.addAll(repos);
                    repo = reposList.get(getArguments().getInt(ARG_ITEM_ID));
                    Activity activity = getActivity();
                    CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
                    if (appBarLayout != null) {
                        appBarLayout.setTitle(repo.getAuthor().toUpperCase());
                    }
                    if (repo != null) {
                        String text = repo.toString();
                        ((TextView) rootView.findViewById(R.id.item_detail)).setText(text);
                    }
                    progressBar.setVisibility(View.GONE);
                }
            });

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_details, container, false);

        return rootView;

    }


}
