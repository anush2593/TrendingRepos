package com.example.trendingreposofgithub.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.trendingreposofgithub.R;
import com.example.trendingreposofgithub.model.Repos;
import java.util.List;

//This class is used to fill the recyclerview
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private static List<Repos> listOfRepos;

    public CustomAdapter( List<Repos> listOfRepos){
       this.listOfRepos = listOfRepos;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView author, description, language;
        public MyViewHolder(View view) {
            super(view);
            //here butterknife could be used
            author =  view.findViewById(R.id.author_name);
            description = view.findViewById(R.id.description);
            language = view.findViewById(R.id.lang_stars);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Repos repo = listOfRepos.get(position);
        if(!repo.getAuthor().isEmpty() || !repo.getName().isEmpty()){
            holder.author.setText(repo.getAuthor()+"/" +repo.getName());
        } else {
            holder.author.setText("Author: " + " " +repo.getAuthor() + "/" + "Name: " + " " +repo.getName());
        }
        if(!repo.getDescription().isEmpty()){
            holder.description.setText(repo.getDescription());
        } else{
            holder.description.setText("Sorry, there is no description");
        }
        String stars = "Stars :" + " " + String.valueOf(repo.getStars())+ "\n";
        holder.language.setText(stars  + "Language:" + " " +repo.getLanguage());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer repoPositionInteger = (Integer) v.getTag();
                Context context = v.getContext();
                //RepoDetailActivity the activity which holds the details fragment
                Intent intent=new Intent(context, RepoDetailActivity.class);
                intent.putExtra(DetailsFragment.ARG_ITEM_ID, repoPositionInteger);
                context.startActivity(intent);
            }
        });

    }

    public void setListOfRepos(List<Repos> listOfRepos){
        this.listOfRepos = listOfRepos;
        notifyDataSetChanged();
    }


    @Override
     public int getItemCount() {
        if(listOfRepos!= null){
            return listOfRepos.size();
        } else {
            return 0;
        }
     }
}
