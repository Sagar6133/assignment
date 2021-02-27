package com.assignment.Assignment.controller;

import com.assignment.Assignment.model.Cinema;
import com.assignment.Assignment.model.CinemaHall;
import com.assignment.Assignment.model.Person;
import com.assignment.Assignment.model.Screen;
import com.assignment.Assignment.service.ServicesInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class MongoController {

    @Autowired
    ServicesInf servicesInf;
    //for testing purpose
    @RequestMapping(value = {"/index"})
    public String test(){
        return servicesInf.index();
    }

    //creating a cinema
    @RequestMapping(value={"/createCinema"}, method = RequestMethod.POST)
    @ResponseBody
    public Map createCinema(@RequestBody Cinema cinema){
        Map map= new LinkedHashMap();
        if(!(cinema.getMovieName().isEmpty() && cinema.getMovieName()==null)){
           Map m= servicesInf.createCinema(cinema);
           return m;
        }else{
            map.put("message","invalid credentials");
            return map;
        }
    }

    // deleting a cinema
    @RequestMapping(value={"/deleteCinema/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Map deleteMap(@PathVariable String id){
        return servicesInf.deleteCinema(id);
    }

    // getting all cinema which inside db
    @RequestMapping(value={"/getAllCinema"}, method = RequestMethod.GET)
    @ResponseBody
    public Map getAllCinema(){
        return servicesInf.getAllCinema();
    }

    // editing a existing cinema
    @RequestMapping(value={"/editCinema"}, method = RequestMethod.PATCH)
    @ResponseBody
    public Map editCinema(@RequestHeader(value = "movieGenre", required = false) String movieGenre,
                          @RequestHeader(value = "releasedDate", required = false) String releasedDate,
                          @RequestHeader(value = "producer", required = false) String producer,
                          @RequestHeader(value = "director", required = false) String director,
                          @RequestHeader(value = "id", required = false) String id){
        return servicesInf.editCinema(id, movieGenre, releasedDate, producer, director);
    }


    // creating a cinema hall
    @RequestMapping(value={"/createCinemaHall"}, method = RequestMethod.POST)
    @ResponseBody
    public Map createCinemaHall(@RequestBody CinemaHall cinemaHall){
        Map map= new LinkedHashMap();
        if(!(cinemaHall.getChName().isEmpty() && cinemaHall.getChName()==null)){
            Map m= servicesInf.createCinemaHall(cinemaHall);
            return m;
        }else{
            map.put("message","invalid credentials");
            return map;
        }
    }


    // getting all cinema halls inside db
    @RequestMapping(value={"/getAllCinemaHall"}, method = RequestMethod.GET)
    @ResponseBody
    public Map getAllCinemaHall(){
        return servicesInf.getAllCinemaHall();
    }

    // deleting a cinema  hall
    @RequestMapping(value={"/deleteCinemaHall/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Map deleteCinemaHall(@PathVariable String id){
        return servicesInf.deleteCinemaHall(id);
    }

    // editing a cinema hall
    @RequestMapping(value={"/editCinemaHall"}, method = RequestMethod.PATCH)
    @ResponseBody
    public Map editCinemaHall(@RequestHeader(value = "hallCity", required = false) String hallCity,
                              @RequestHeader(value = "hallState", required = false) String hallState,
                              @RequestHeader(value = "hallCountry", required = false) String hallCountry,
                              @RequestHeader(value = "id", required = false) String id){
        return servicesInf.editCinemaHall(id, hallCity, hallState, hallCountry);
    }

    //creating a screen
    @RequestMapping(value={"/createScreen"}, method = RequestMethod.POST)
    @ResponseBody
    public Map createScreen(@RequestBody Screen screen){
        Map map= new LinkedHashMap();
        if(!(screen.getChName().isEmpty() && screen.getChName()==null)){
            Map m= servicesInf.createScreen(screen);
            return m;
        }else{
            map.put("message","invalid credentials");
            return map;
        }
    }

    //edit a screen
    @RequestMapping(value={"/editScreen"}, method = RequestMethod.PATCH)
    @ResponseBody
    public Map editScreen(@RequestHeader(value = "chId", required = false) String chId,
                          @RequestHeader(value = "sCapacity", required = false) String sCapacity,
                          @RequestHeader(value = "sShowDate", required = false) String sShowDate,
                          @RequestHeader(value = "sShowTime", required = false) String sShowTime,
                          @RequestHeader(value = "movieId", required = false) String movieId,
                          @RequestHeader(value = "id", required = false) String id){
        return servicesInf.editScreen(id, chId, movieId, sCapacity, sShowDate, sShowTime);
    }

    //get screen based on movie
    @RequestMapping(value={"/getScreenBasedOnMovie/{movieId}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map getScreenBasedOnMovie(@PathVariable String movieId){
        return servicesInf.getScreenBasedOnMovie(movieId);
    }
    //delete screen based on movie
    @RequestMapping(value={"/deleteScreenBasedOnMovie/{movieId}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Map deleteScreenBasedOnMovie(@PathVariable String movieId){
        return servicesInf.deleteScreenBasedOnMovie(movieId);
    }

    //get screen based on hall
    @RequestMapping(value={"/getScreenBasedOnHall/{hallId}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map getScreenBasedOnHall(@PathVariable String hallId){
        return servicesInf.getScreenBasedOnHall(hallId);
    }
    //delete screen based on hall
    @RequestMapping(value={"/deleteScreenBasedOnHall/{hallId}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Map deleteScreenBasedOnHall(@PathVariable String hallId){
        return servicesInf.deleteScreenBasedOnHall(hallId);
    }
    //delete screen based on id
    @RequestMapping(value={"/deleteScreen/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public Map deleteScreen(@PathVariable String id){
        return servicesInf.deleteScreen(id);
    }
    // assignment number  1 c)
    @RequestMapping(value={"/getCinemaByFilter"}, method = RequestMethod.GET)
    @ResponseBody
    public Map getCinemaByFilter(@RequestParam(value = "movieName", required = false) String movieName,
                                  @RequestParam(value = "chName", required = false) String chName,
                                 @RequestParam(value = "sShowDate", required = false) String sShowDate){
        return servicesInf.getCinemaByFilter(movieName, chName, sShowDate);
    }
   // Part 2: //  1) Get the list of Movies which are available between a given start and end date.
    @RequestMapping(value={"/getCinemaByDate"}, method = RequestMethod.GET)
    @ResponseBody
    public Map getAllCinemaByDate(@RequestParam(value = "startDate", required = false) String startDate,
                            @RequestParam(value = "endDate", required = false) String endDate){
        return servicesInf.getAllCinemaByDate(startDate, endDate);
    }


    @RequestMapping(value={"/createUser"}, method = RequestMethod.POST)
    @ResponseBody
    public Map createUser(@RequestBody Person person){
        Map map= new LinkedHashMap();
        if(!(person.getEmail().isEmpty() && person.getEmail()==null)){
            Map m= servicesInf.createUser(person);
            return m;
        }else{
            map.put("message","invalid credentials");
            map.put("user", null);
            return map;
        }
    }
    // part 2 //  2. Create a movie booking.
    @RequestMapping(value={"/bookTicket"}, method = RequestMethod.POST)
    @ResponseBody
    public Map bookTicket(@RequestHeader(value = "uId", required = false) String userId,
                          @RequestHeader(value = "screenId", required = false) String screenId){
        return servicesInf.bookTicket(userId, screenId);
    }
    // part 2 // 3. View a booking:
    @RequestMapping(value={"/getTicket/{ticketId}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map getTicket(@PathVariable String ticketId){
        return servicesInf.getTicket(ticketId);
    }
    //part 2   // 4.Cancel a booking.
    @RequestMapping(value={"/cancelTicket/{ticketId}"}, method = RequestMethod.GET)
    @ResponseBody
    public Map cancelTicket(@PathVariable String ticketId){
        return servicesInf.cancelTicket(ticketId);
    }
    //part 2 // 5. Edit a booking:
    @RequestMapping(value={"/editTicket/{ticketId}"}, method = RequestMethod.PATCH)
    @ResponseBody
    public Map editTicket(@RequestHeader(value = "bookingId", required = false) String bookingId,
                          @RequestHeader(value = "screenId", required = false) String screenId){
        return servicesInf.editTicket(bookingId, screenId);
    }


}
