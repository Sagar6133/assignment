package com.assignment.Assignment.service;

import com.assignment.Assignment.model.Cinema;
import com.assignment.Assignment.model.CinemaHall;
import com.assignment.Assignment.model.Person;
import com.assignment.Assignment.model.Screen;

import java.util.Map;

public interface ServicesInf {
    String index();

    Map createCinema(Cinema cinema);

    Map createCinemaHall(CinemaHall cinemaHall);

    Map createScreen(Screen screen);

    Map deleteCinema(String id);

    Map getAllCinema();

    Map getAllCinemaHall();

    Map deleteCinemaHall(String id);

    Map getScreenBasedOnMovie(String movieId);

    Map getScreenBasedOnHall(String hallId);

    Map deleteScreenBasedOnMovie(String movieId);

    Map deleteScreenBasedOnHall(String hallId);

    Map deleteScreen(String id);

    Map editCinema(String id, String movieGenre, String releasedDate, String producer, String director);

    Map editCinemaHall(String id, String hallCity, String hallState, String hallCountry);

    Map editScreen(String id, String chId, String movieId, String sCapacity, String sShowDate, String sShowTime);

    Map getAllCinemaByDate(String startDate, String endDate);

    Map createUser(Person person);

    Map bookTicket(String userId, String screenId);

    Map getTicket(String ticketId);

    Map cancelTicket(String ticketId);

    Map editTicket(String bookingId, String screenId);

    Map getCinemaByFilter(String movieName, String chName, String sShowDate);
}
