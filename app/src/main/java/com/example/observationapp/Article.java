package com.example.observationapp;

public class Article {
    private String title;
    private String video;
    private String picture;

    public Article(){
        title = "";
        video = "";
        picture = "";
    }

    public Article(String title, String video, String picture){
        this.title = title;
        this.video = video;
        this.picture = picture;
    }

    public String getTitle() { return title; }
    public String getVideo(){return video; }
    public String getPicture() { return picture; }
}
