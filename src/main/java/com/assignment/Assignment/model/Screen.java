package com.assignment.Assignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "screen")
public class Screen {
    @Id
    private String _id;
    private String chName;
    private String chId;
    private int sCapacity;
    private String sShowDate;
    private String sShowTime;
    private int sBooked;
    private String movieName;
    private String movieId;
    private boolean isBooked;

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChId() {
        return chId;
    }

    public void setChId(String chId) {
        this.chId = chId;
    }

    public int getsCapacity() {
        return sCapacity;
    }

    public void setsCapacity(int sCapacity) {
        this.sCapacity = sCapacity;
    }

    public String getsShowDate() {
        return sShowDate;
    }

    public void setsShowDate(String sShowDate) {
        this.sShowDate = sShowDate;
    }

    public String getsShowTime() {
        return sShowTime;
    }

    public void setsShowTime(String sShowTime) {
        this.sShowTime = sShowTime;
    }

    public int getsBooked() {
        return sBooked;
    }

    public void setsBooked(int sBooked) {
        this.sBooked = sBooked;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
