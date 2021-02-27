package com.assignment.Assignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "booking_status")
public class Booking {

    @Id
    private String _id;
    private String movieName;
    private String chName;
    private String bookingDate;
    private String bookingPersonName;
    private String bookingPersonContactNumber;
    private String bookingPersonEmail;
    private boolean isCancelled;
    private String screenId;
    private String bookingCreatedDate;

    public String getBookingCreatedDate() {
        return bookingCreatedDate;
    }

    public void setBookingCreatedDate(String bookingCreatedDate) {
        this.bookingCreatedDate = bookingCreatedDate;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingPersonName() {
        return bookingPersonName;
    }

    public void setBookingPersonName(String bookingPersonName) {
        this.bookingPersonName = bookingPersonName;
    }

    public String getBookingPersonContactNumber() {
        return bookingPersonContactNumber;
    }

    public void setBookingPersonContactNumber(String bookingPersonContactNumber) {
        this.bookingPersonContactNumber = bookingPersonContactNumber;
    }

    public String getBookingPersonEmail() {
        return bookingPersonEmail;
    }

    public void setBookingPersonEmail(String bookingPersonEmail) {
        this.bookingPersonEmail = bookingPersonEmail;
    }
}
