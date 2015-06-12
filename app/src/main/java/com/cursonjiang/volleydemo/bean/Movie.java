package com.cursonjiang.volleydemo.bean;

/**
 * Created by root on 15/6/13.
 */
public class Movie {

    /**
     * name : The Shawshank Redemption
     * image : http://www.jycoder.com/json/movies/1.jpg
     * year : 1995
     * rating : 9.6
     */
    private String name;
    private String image;
    private String year;
    private String rating;

    public Movie() {
    }

    public Movie(String image, String name, String rating, String year) {
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.year = year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }
}
