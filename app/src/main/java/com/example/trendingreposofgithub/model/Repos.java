package com.example.trendingreposofgithub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//POJO class
public class Repos {
    @SerializedName("author")
    @Expose
    String author;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("description")
    @Expose
    String description;
    @SerializedName("language")
    @Expose
    String language;
    @SerializedName("stars")
    @Expose
    int stars;



     public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public int getStars() {
        return stars;
    }




    public Repos(String author, String name,
                 String description, String language, int stars){
        this.author = author;
        this.name = name;
        this.description=description;
        this.language = language;
        this.stars=stars;
    }

    @Override
    public String toString() {
        String text = "Name:" + " " + name + " " + "\n" + "Description:" + " " + description
                + "\n" + "Language Used: " + " " + language
                + "\n" + "Stars: " + " " + stars;
        return text ;
    }
}
