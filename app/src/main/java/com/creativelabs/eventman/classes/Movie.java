package com.creativelabs.eventman.classes;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id;
    private String title;
    private int year;
    private String casts;
    private String directors;
    private String bgm;
    private String plot;
    private String image;

    public Movie() {
    }

    public Movie(String title, int year, String cast, String director, String bgm, String plot, String image) {
        this.title = title;
        this.year = year;
        this.casts = cast;
        this.directors = director;
        this.bgm = bgm;
        this.plot = plot;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCast() {
        return casts;
    }

    public void setCast(String cast) {
        this.casts = cast;
    }

    public String getDirector() {
        return directors;
    }

    public void setDirector(String director) {
        this.directors = director;
    }

    public String getBgm() {
        return bgm;
    }

    public void setBgm(String bgm) {
        this.bgm = bgm;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
