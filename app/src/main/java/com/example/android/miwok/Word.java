package com.example.android.miwok;

public class Word {
    private String miWork;
    private String english;
    private int picture=0;
    private int mediaResourceID;


    public Word(int picture,String english,String miWork,int mediaResourceID){
        this.english=english;
        this.picture=picture;
        this.miWork=miWork;
        this.mediaResourceID= mediaResourceID;
    }
    public Word(String english,String miWork,int mediaResourceID) {
        this.miWork = miWork;
        this.english = english;
        this.mediaResourceID = mediaResourceID;
    }

    public int getMediaResourceID() {
        return mediaResourceID;
    }

    public String getEnglish() {
        return english;
    }

    public String getMiWork() {
        return miWork;
    }

    public int getPicture() {
        return picture;
    }


    }

