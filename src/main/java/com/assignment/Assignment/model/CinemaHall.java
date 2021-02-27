package com.assignment.Assignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cinema_hall")
public class CinemaHall {
    @Id
    private String _id;
    private String chName;
    private String chCity;
    private String chState;
    private String chCountry;

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

    public String getChCity() {
        return chCity;
    }

    public void setChCity(String chCity) {
        this.chCity = chCity;
    }

    public String getChState() {
        return chState;
    }

    public void setChState(String chState) {
        this.chState = chState;
    }

    public String getChCountry() {
        return chCountry;
    }

    public void setChCountry(String chCountry) {
        this.chCountry = chCountry;
    }
}
