package com.assignment.Assignment.dao;


import com.assignment.Assignment.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class Dao implements DaoInf {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public String index() {
        Cinema cinema= new Cinema();
        cinema.setMovieName("Chiku");
        mongoTemplate.save(cinema);
        return "Test";
    }

    @Override
    public Map createCinema(Cinema cinema) {
        Cinema cinema1= new Cinema();
        cinema1.setMovieName(cinema.getMovieName());
        cinema1.setDirector(cinema.getDirector());
        cinema1.setMovieGenre(cinema.getMovieGenre());
        cinema1.setProducer(cinema.getProducer());
        cinema1.setReleasedDate(cinema.getReleasedDate());
        Cinema createdCinema= mongoTemplate.insert(cinema1);

        Map map= new LinkedHashMap();

        if(createdCinema== null){
            map.put("message","unable to create cinema");
            map.put("cinema",null);
            return map;
        }else{
            map.put("message","cinema created Successfully !!");
            map.put("cinema",createdCinema);
            return map;
        }
    }

    @Override
    public Map createCinemaHall(CinemaHall cinemaHall) {
        CinemaHall hall= mongoTemplate.insert(cinemaHall);
        Map map= new LinkedHashMap();

        if(hall== null){
            map.put("message","unable to create cinema hall");
            map.put("cinema",null);
            return map;
        }else{
            map.put("message","cinema hall created Successfully !!");
            map.put("cinema",hall);
            return map;
        }
    }

    @Override
    public Map createScreen(Screen screen) {
        Map map= new LinkedHashMap();

        Query searchMovie= new Query();
        searchMovie.addCriteria(Criteria.where("movieName").is(screen.getMovieName()));
        boolean isMovieExist= mongoTemplate.exists(searchMovie, Cinema.class);

        Query search = new Query();
        search.addCriteria(Criteria.where("chName").is(screen.getChName()));
        boolean isCinemaHallExist= mongoTemplate.exists(search, CinemaHall.class);

        if (isMovieExist && isCinemaHallExist){
            Cinema cinema= mongoTemplate.findOne(searchMovie, Cinema.class);
            CinemaHall hall = mongoTemplate.findOne(search, CinemaHall.class);
            Screen screen1= new Screen();
            screen1.setChId(hall.get_id());
            screen1.setChName(hall.getChName());
            screen1.setMovieId(cinema.get_id());
            screen1.setMovieName(cinema.getMovieName());
            screen1.setsCapacity(screen.getsCapacity());
            screen1.setsBooked(screen.getsBooked());
            screen1.setsShowDate(screen.getsShowDate());
            screen1.setsShowTime(screen.getsShowTime());
            screen1.setBooked(false);
            Screen screen2=  mongoTemplate.insert(screen1);
            map.put("message","screen created successfully !!");
            map.put("screen",screen2);
            return map;
        } else {
            map.put("message","movie name and cinema hall name is invalid");
            map.put("screen",null);
            return map;
        }
    }

    @Override
    public Map deleteCinema(String id) {
        Map map= new LinkedHashMap();

        Query serchCinema= new Query();
        serchCinema.addCriteria(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(serchCinema,Cinema.class);

        if(result.getDeletedCount()!=0){
            Query deleteQueryScreen = new Query();
            deleteQueryScreen.addCriteria(Criteria.where("movieId").is(id));
            mongoTemplate.remove(deleteQueryScreen,Screen.class);

            map.put("message","cinema and all related screens are deleted");
            return map;
        }else{
            map.put("message","id is not valid");
            return map;
        }
    }

    @Override
    public Map getAllCinema() {
        Map map= new LinkedHashMap();

        List<Cinema> cinemas= mongoTemplate.findAll(Cinema.class);

        if (cinemas.size()==0){
            map.put("message","no cinema found");
            map.put("cinemas",null);
            return map;
        } else {
            map.put("message","cinemas found");
            map.put("cinemas",cinemas);
            return map;
        }
    }

    @Override
    public Map getAllCinemaHall() {
        Map map= new LinkedHashMap();

        List<CinemaHall> cinemaHalls= mongoTemplate.findAll(CinemaHall.class);

        if (cinemaHalls.size()==0){
            map.put("message","no cinema hall found");
            map.put("cinemas",null);
            return map;
        } else {
            map.put("message","cinemas halls found");
            map.put("cinemas",cinemaHalls);
            return map;
        }
    }

    @Override
    public Map deleteCinemaHall(String id) {
        Map map= new LinkedHashMap();

        Query serchCinemaHall= new Query();
        serchCinemaHall.addCriteria(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(serchCinemaHall,CinemaHall.class);

        if(result.getDeletedCount()!=0){
            map.put("message","cinema hall is deleted");
            return map;
        }else{
            map.put("message","id is not valid");
            return map;
        }
    }

    @Override
    public Map getScreenBasedOnMovie(String movieId) {
        Map map= new LinkedHashMap();

        Query serchCinemaHall= new Query();
        serchCinemaHall.addCriteria(Criteria.where("movieId").is(movieId));
        List<Screen> screens = mongoTemplate.find(serchCinemaHall,Screen.class);

        if(screens.size()!=0){
            map.put("message","Screens are found");
            map.put("screen", screens);
            return map;
        }else{
            map.put("message","id is not valid");
            map.put("screen", null);
            return map;
        }
    }

    @Override
    public Map getScreenBasedOnHall(String hallId) {
        Map map= new LinkedHashMap();

        Query serchCinemaHall= new Query();
        serchCinemaHall.addCriteria(Criteria.where("chId").is(hallId));
        List<Screen> screens = mongoTemplate.find(serchCinemaHall,Screen.class);

        if(screens.size()!=0){
            map.put("message","Screens are found");
            map.put("screen", screens);
            return map;
        }else{
            map.put("message","id is not valid");
            map.put("screen", null);
            return map;
        }
    }

    @Override
    public Map deleteScreenBasedOnMovie(String movieId) {
        Map map= new LinkedHashMap();

        Query serchCinemaHall= new Query();
        serchCinemaHall.addCriteria(Criteria.where("movieId").is(movieId));
        DeleteResult result = mongoTemplate.remove(serchCinemaHall,Screen.class);

        if(result.getDeletedCount()!=0){
            map.put("message","Screens is deleted");
            return map;
        }else{
            map.put("message","id is not valid");
            return map;
        }
    }

    @Override
    public Map deleteScreenBasedOnHall(String hallId) {
        Map map= new LinkedHashMap();

        Query serchCinemaHall= new Query();
        serchCinemaHall.addCriteria(Criteria.where("chId").is(hallId));
        DeleteResult deleteResult = mongoTemplate.remove(serchCinemaHall,Screen.class);

        if(deleteResult.getDeletedCount()!=0){
            map.put("message","Screens is deleted");
            return map;
        }else{
            map.put("message","id is not valid");
            return map;
        }
    }

    @Override
    public Map deleteScreen(String id) {
        Map map= new LinkedHashMap();

        Query serchCinemaHall= new Query();
        serchCinemaHall.addCriteria(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(serchCinemaHall,Screen.class);

        if(result.getDeletedCount()!=0){
            map.put("message","screen is deleted");
            return map;
        }else{
            map.put("message","id is not valid");
            return map;
        }
    }

    @Override
    public Map editCinema(String id, String movieGenre, String releasedDate, String producer, String director) {
        Map map= new LinkedHashMap();

        Query query= new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        boolean isExist= mongoTemplate.exists(query,Cinema.class);
        if(isExist){
            Update update= new Update();
            update.set("movieGenre",movieGenre);
            update.set("releasedDate",releasedDate);
            update.set("producer",producer);
            update.set("director",director);

            UpdateResult result = mongoTemplate.updateFirst(query,update, Cinema.class);

            if (result.getModifiedCount()!=0){
                map.put("message","cinema updated");
                map.put("cinema",mongoTemplate.findOne(query,Cinema.class));
                return map;
            } else {
                map.put("message","cinema not updated");
                map.put("cinema", null);
                return map;
            }
        } else {
            map.put("message","cinema does not exist");
            map.put("cinema", null);
            return map;
        }

    }

    @Override
    public Map editCinemaHall(String id, String hallCity, String hallState, String hallCountry) {
        Map map= new LinkedHashMap();

        Query query= new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        boolean isExist= mongoTemplate.exists(query,CinemaHall.class);
        if(isExist){
            Update update= new Update();
            update.set("chCity",hallCity);
            update.set("chState",hallState);
            update.set("chCountry",hallCountry);

            UpdateResult result = mongoTemplate.updateFirst(query,update, CinemaHall.class);

            if (result.getModifiedCount()!=0){
                map.put("message","cinema hall updated");
                map.put("cinema_hall",mongoTemplate.findOne(query,CinemaHall.class));
                return map;
            } else {
                map.put("message","cinema hall not updated");
                map.put("cinema_hall", null);
                return map;
            }
        } else {
            map.put("message","cinema hall does not exist");
            map.put("cinema_hall", null);
            return map;
        }
    }

    @Override
    public Map editScreen(String id, String chId, String movieId, String sCapacity, String sShowDate, String sShowTime) {
        Map map= new LinkedHashMap();

        Query query= new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        boolean isExistScreen= mongoTemplate.exists(query,Screen.class);

        Query query1= new Query();
        query1.addCriteria(Criteria.where("_id").is(chId));
        boolean isExistCinemaHall= mongoTemplate.exists(query1,CinemaHall.class);

        Query query2= new Query();
        query2.addCriteria(Criteria.where("_id").is(id));
        boolean isExistCinema= mongoTemplate.exists(query2,Cinema.class);

        if(isExistScreen && isExistCinemaHall && isExistCinema){
            Update update= new Update();
            update.set("sCapacity", sCapacity);
            update.set("sShowDate", sShowDate);
            update.set("sShowTime", sShowTime);
            update.set("chId", chId);
            update.set("chName",mongoTemplate.findOne(query1,CinemaHall.class).getChName());
            update.set("movieId",movieId);
            update.set("movieName", mongoTemplate.findOne(query2,Cinema.class));
            mongoTemplate.updateFirst(query, update, Screen.class);

            map.put("message","screen updated successfully !!");
            map.put("screen",mongoTemplate.findOne(query,Screen.class));
            return map;
        } else {
            map.put("message","screen updated failed, ids are not valid");
            map.put("screen",null);
            return map;
        }
    }

    @Override
    public Map getAllCinemaByDate(String startDate, String endDate) {
        Map map= new LinkedHashMap();

        Query query= new Query();
        query.addCriteria(Criteria.where("releaseDate").gte(startDate).lte(endDate));
        List<Cinema> cinemas = mongoTemplate.find(query,Cinema.class);

        if (cinemas.size()!=0){
            map.put("message","cinema found !!");
            map.put("cinema", cinemas);
            return map;
        } else {
            map.put("message","no cinema found !!");
            map.put("cinema", null);
            return map;
        }
    }

    @Override
    public Map createUser(Person person) {
        Map map= new LinkedHashMap();

        Query serchUser= new Query();
        serchUser.addCriteria(Criteria.where("email").is(person.getEmail()));
        boolean result = mongoTemplate.exists(serchUser,Person.class);

        if(!result){
            Person person1=mongoTemplate.insert(person);

            map.put("message","user created");
            map.put("user", person1);
            return map;
        }else{
            map.put("message","user already exists");
            map.put("user", null);
            return map;
        }
    }

    @Override
    public Map bookTicket(String userId, String screenId) {
        Map map= new LinkedHashMap();

        Query serchUser= new Query();
        serchUser.addCriteria(Criteria.where("_id").is(userId));
        boolean result1 = mongoTemplate.exists(serchUser,Person.class);

        Query serchScreen= new Query();
        serchScreen.addCriteria(Criteria.where("_id").is(userId));
        boolean result2 = mongoTemplate.exists(serchScreen,Screen.class);

        if (result1 && result2){
            Person user = mongoTemplate.findOne(serchUser, Person.class);
            Screen screen = mongoTemplate.findOne(serchScreen, Screen.class);

            String pattern = "dd-MM-yyyy";
            String dateInString =new SimpleDateFormat(pattern).format(new Date());
            Booking booking = new Booking();
            booking.setBookingCreatedDate(dateInString);
            booking.setBookingDate(screen.getsShowDate());
            booking.setChName(screen.getChName());
            booking.setMovieName(screen.getMovieName());
            booking.setBookingPersonContactNumber(user.getContactNumber());
            booking.setBookingPersonEmail(user.getEmail());
            booking.setBookingPersonName(user.getName());
            booking.setCancelled(false);
            booking.setScreenId(screen.get_id());
            Booking addedBooking = mongoTemplate.insert(booking);

            map.put("message","Ticket booked successfully");
            map.put("ticket", addedBooking);
            return map;

        } else {
            map.put("message","ids are not valid");
            map.put("ticket", null);
            return map;
        }
    }

    @Override
    public Map getTicket(String ticketId) {
        Map map= new LinkedHashMap();

        Query serchBooking= new Query();
        serchBooking.addCriteria(Criteria.where("_id").is(ticketId));
        Booking result = mongoTemplate.findOne(serchBooking,Booking.class);

        if (result!= null){
            map.put("message","booking found successfully");
            map.put("booking", result);
            return map;
        } else {
            map.put("message","invalid booking id");
            map.put("booking", null);
            return map;
        }
    }

    @Override
    public Map cancelTicket(String ticketId) {

        Map map= new LinkedHashMap();

        Query serchBooking= new Query();
        serchBooking.addCriteria(Criteria.where("_id").is(ticketId));
        Booking result = mongoTemplate.findOne(serchBooking,Booking.class);

        if (result!= null) {

            if (result.isCancelled() == true){
                map.put("message", "this is a cancelled booking, not allow further changes");
                map.put("booking", result);
                return map;
             } else {
                Update update = new Update();
                update.set("isCancelled",true);
                UpdateResult rows= mongoTemplate.updateFirst(serchBooking, update, Booking.class);
                if(rows.getModifiedCount()!=0){
                    map.put("message", "cancelled successfully");
                    map.put("booking", mongoTemplate.findOne(serchBooking, Booking.class));
                    return map;
                } else {
                    map.put("message", "some thing went wrong, try again");
                    map.put("booking", mongoTemplate.findOne(serchBooking, Booking.class));
                    return map;
                }
             }
        } else {
            map.put("message","invalid booking id");
            map.put("booking", null);
            return map;
        }
    }

    @Override
    public Map editTicket(String bookingId, String screenId) {
        Map map= new LinkedHashMap();

        Query serchScreen= new Query();
        serchScreen.addCriteria(Criteria.where("_id").is(screenId));
        Screen result2 = mongoTemplate.findOne(serchScreen,Screen.class);

        Query serchBooking= new Query();
        serchBooking.addCriteria(Criteria.where("_id").is(bookingId));
        Booking result3 = mongoTemplate.findOne(serchBooking,Booking.class);

        if( result2!=null && result3!=null){
            if(result3.isCancelled() == true){
                map.put("message", "this is a cancelled booking, not allow further changes");
                map.put("booking", null);
                return map;
            } else {
                String pattern = "dd-MM-yyyy";
                String dateInString =new SimpleDateFormat(pattern).format(new Date());
                Update update = new Update();
                update.set("movieName", result2.getMovieName());
                update.set("chName", result2.getChName());
                update.set("screenId", result2.get_id());
                update.set("bookingCreatedDate", dateInString);
                UpdateResult rows= mongoTemplate.updateFirst(serchBooking, update, Booking.class);
                if(rows.getModifiedCount()!=0){
                    map.put("message", "updated successfully");
                    map.put("booking", mongoTemplate.findOne(serchBooking, Booking.class));
                    return map;
                } else {
                    map.put("message", "some thing went wrong, try again");
                    map.put("booking", mongoTemplate.findOne(serchBooking, Booking.class));
                    return map;
                }
            }
        }else {
            map.put("message","invalid booking id");
            map.put("booking", null);
            return map;
        }
    }

    @Override
    public Map getCinemaByFilter(String movieName, String chName, String sShowDate) {
        Map map= new LinkedHashMap();
        List list= new ArrayList();
        Criteria criteria= new Criteria();
        criteria.andOperator(Criteria.where("movieName").is(movieName),Criteria.where("chName").is(chName),
                Criteria.where("sShowDate").is(sShowDate) );
        Query query= new Query(criteria);
        List<Screen> screens= mongoTemplate.find(query,Screen.class);


        for (Screen s:screens){
            Query query1= new Query();
            query1.addCriteria(Criteria.where("_id").is(s.get_id()));
            list.add(mongoTemplate.findOne(query1, Cinema.class));
        }

        if (list.size()!=0){
            map.put("message","cinemas found successfully !!");
            map.put("booking", list);
            return map;
        } else {
            map.put("message","no cinemas found");
            map.put("booking", null);
            return map;
        }
    }


}
