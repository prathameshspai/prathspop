package com.example.android.popularmoviesapp;

import java.util.List;

public class Movie {
    private String image, title, rate, date, overview;

    /**
     * No args constructor for use in serialization
     */
    public Movie() {
    }

    public Movie(String image, String title, String rate, String date, String overview) {
        this.image=image;
        this.title=title;
        this.rate=rate;
        this.date=date;
        this.overview=overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String image) {
        this.overview = overview;
    }


}