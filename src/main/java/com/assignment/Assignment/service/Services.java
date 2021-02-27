package com.assignment.Assignment.service;

import com.assignment.Assignment.dao.DaoInf;
import com.assignment.Assignment.model.Cinema;
import com.assignment.Assignment.model.CinemaHall;
import com.assignment.Assignment.model.Person;
import com.assignment.Assignment.model.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Services implements ServicesInf {
    @Autowired
    DaoInf daoInf;

    @Override
    public String index() {
        return daoInf.index();
    }

    @Override
    public Map createCinema(Cinema cinema) {
        return daoInf.createCinema(cinema);
    }

    @Override
    public Map createCinemaHall(CinemaHall cinemaHall) {
        return daoInf.createCinemaHall(cinemaHall);
    }

    @Override
    public Map createScreen(Screen screen) {
        return daoInf.createScreen(screen);
    }

    @Override
    public Map deleteCinema(String id) {
        return daoInf.deleteCinema(id);
    }

    @Override
    public Map getAllCinema() {
        return daoInf.getAllCinema();
    }

    @Override
    public Map getAllCinemaHall() {
        return daoInf.getAllCinemaHall();
    }

    @Override
    public Map deleteCinemaHall(String id) {
        return daoInf.deleteCinemaHall(id);
    }

    @Override
    public Map getScreenBasedOnMovie(String movieId) {
        return daoInf.getScreenBasedOnMovie(movieId);
    }

    @Override
    public Map getScreenBasedOnHall(String hallId) {
        return daoInf.getScreenBasedOnHall(hallId);
    }

    @Override
    public Map deleteScreenBasedOnMovie(String movieId) {
        return daoInf.deleteScreenBasedOnMovie(movieId);
    }

    @Override
    public Map deleteScreenBasedOnHall(String hallId) {
        return daoInf.deleteScreenBasedOnHall(hallId);
    }

    @Override
    public Map deleteScreen(String id) {
        return daoInf.deleteScreen(id);
    }

    @Override
    public Map editCinema(String id, String movieGenre, String releasedDate, String producer, String director) {
        return daoInf.editCinema(id, movieGenre, releasedDate, producer, director);
    }

    @Override
    public Map editCinemaHall(String id, String hallCity, String hallState, String hallCountry) {
        return daoInf.editCinemaHall(id, hallCity, hallState, hallCountry);
    }

    @Override
    public Map editScreen(String id, String chId, String movieId, String sCapacity, String sShowDate, String sShowTime) {
        return daoInf.editScreen(id, chId, movieId, sCapacity, sShowDate, sShowTime);
    }

    @Override
    public Map getAllCinemaByDate(String startDate, String endDate) {
        return daoInf.getAllCinemaByDate(startDate, endDate);
    }

    @Override
    public Map createUser(Person person) {
        return daoInf.createUser(person);
    }

    @Override
    public Map bookTicket(String userId, String screenId) {
        return daoInf.bookTicket(userId,screenId);
    }

    @Override
    public Map getTicket(String ticketId) {
        return daoInf.getTicket(ticketId);
    }

    @Override
    public Map cancelTicket(String ticketId) {
        return daoInf.cancelTicket(ticketId);
    }

    @Override
    public Map editTicket(String bookingId, String screenId) {
        return daoInf.editTicket(bookingId, screenId);
    }

    @Override
    public Map getCinemaByFilter(String movieName, String chName, String sShowDate) {
        return daoInf.getCinemaByFilter(movieName, chName, sShowDate);
    }


}
