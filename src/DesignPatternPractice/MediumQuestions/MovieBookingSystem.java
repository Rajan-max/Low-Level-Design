package DesignPatternPractice.MediumQuestions;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum SeatCategory {
    SILVER,
    GOLD,
    PLATINUM
}

enum City {
    Bangalore,
    Hyderabad,
    Chennai
}

// User Class
record User1(String userId, String name) {
}


// Seat Class
class Seat {
    SeatCategory seatCategory;
    private int seatId;
    private boolean isBooked;

    public Seat() {
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public void setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
    }
}

// Screen Class
class Screen {
    private final Lock lock = new ReentrantLock();
    private int screenId;
    private List<Seat> seats;

    public Screen() {
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Lock getLock() {
        return lock;
    }
}

// Movie Class
class Movie {
    private String movieName;
    private String movieDuration;

    public Movie() {
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }
}

// Show Class
class Show {
    private int showId;
    private Movie movie;
    private String timing;

    public Show() {

    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
}


// Theatre Class
class Theatre {
    private int theatreId;
    private String name;
    private List<Show> shows;
    private List<Screen> screens;

    public Theatre() {
        shows = new ArrayList<>();
        screens = new ArrayList<>();
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }
}

class TheatreController {
    Map<String, List<Theatre>> cityTheatreMap;

    public TheatreController() {
        cityTheatreMap = new HashMap<>();
    }

    public void addTheatre(String city, Theatre theatre) {
        List<Theatre>arr=cityTheatreMap.getOrDefault(city,new ArrayList<>());
        arr.add(theatre);
        cityTheatreMap.put(city,arr);
    }

    public List<Theatre> getAllTheatre(String city) {
        return cityTheatreMap.getOrDefault(city, new ArrayList<>());
    }
}


// Booking Class
record Booking(String bookingId, Show show, Seat seat, User1 user) {
}

// Payment Class
class Payment1 {
    public void processPayment(Booking booking) {
        System.out.println("Payment processed for booking id: " + booking.bookingId());
    }
}

// MovieBookingSystem Class
class MovieBookingSystemProcessor {
    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();
    private final TheatreController theatreController;

    public MovieBookingSystemProcessor(TheatreController theatreController) {
        this.theatreController=theatreController;
    }

    public Booking bookTicket(String cityName, String movieName, String time, int seatId, User1 user) {
        Theatre theatre = findTheatre(cityName, movieName);
        if (theatre == null) {
            throw new RuntimeException("No theatre found for movie " + movieName + " in city " + cityName);
        }
        Show show = findShow(theatre, movieName, time);
        if (show == null) {
            throw new RuntimeException("No show found for movie " + movieName + " in theatre " + theatre.getName());
        }
        Seat seat = findSeat(theatre, seatId);
        if (seat == null) {
            throw new RuntimeException("Invalid seat id: " + seatId);
        }
        if (isSeatAlreadyBooked(show, seat)) {
            throw new RuntimeException("Seat " + seatId + " already booked for show " + show.getShowId());
        }
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, show, seat, user);
        seat.setBooked(true);
        bookings.put(bookingId, booking);
        return booking;
    }

    private Theatre findTheatre(String cityName, String movieName) {
        for (Theatre theatre : theatreController.getAllTheatre(cityName)) {
            for (Show show : theatre.getShows()) {
                if (show.getMovie().getMovieName().equalsIgnoreCase(movieName)) {
                    return theatre;
                }
            }
        }
        return null;
    }


    private Show findShow(Theatre theatre, String movieName, String time) {
        for (Show show : theatre.getShows()) {
            if (show.getTiming().equals(time) && show.getMovie().getMovieName().equalsIgnoreCase(movieName)) {
                return show;
            }
        }
        return null;
    }

    private Seat findSeat(Theatre theatre, int seatId) {
        for (Screen screen : theatre.getScreens()) {
            for (Seat seat : screen.getSeats()) {
                if (seat.getSeatId() == seatId) {
                    return seat;
                }
            }
        }
        return null;
    }

    private boolean isSeatAlreadyBooked(Show show, Seat seat) {
        for (Booking booking : bookings.values()) {
            if (booking.show().equals(show) && booking.seat().equals(seat)) {
                return true;
            }
        }
        return false;
    }

    public boolean cancelTicket(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.seat().setBooked(false);
            bookings.remove(bookingId);
            return true;
        }
        return false;
    }

}


class MovieBookingSystem {
    TheatreController theatreController=new TheatreController();

    public MovieBookingSystem() {
        initialize();
    }

    MovieBookingSystemProcessor movieBookingSystemProcessor = new MovieBookingSystemProcessor(theatreController);

    void initialize() {
        Theatre theatre = getTheatre();
        theatreController.addTheatre(String.valueOf(City.Bangalore), theatre);
        theatreController.addTheatre(String.valueOf(City.Hyderabad), theatre);
    }

    private Theatre getTheatre() {
        Theatre theatre = new Theatre();
        theatre.setName("Pvr Inox");
        theatre.setTheatreId(101);
        theatre.setScreens(createScreen());
        theatre.setShows(createShows());
        return theatre;
    }

    private List<Show> createShows() {
        Show show1 = new Show();
        show1.setShowId(1001);
        show1.setMovie(createMovie("a"));
        show1.setTiming("8:00 AM");
        Show show2 = new Show();
        show2.setShowId(1001);
        show2.setMovie(createMovie("b"));
        show2.setTiming("12:00 AM");
        return List.of(show1, show2);
    }

    private Movie createMovie(String temp) {  //Hardcoded
        Movie movie1 = new Movie();
        movie1.setMovieName("BAHUBALI");
        movie1.setMovieDuration("3:00hrs");

        Movie movie2 = new Movie();
        movie2.setMovieName("PUSHPA");
        movie2.setMovieDuration("3:00hrs");
        if (temp.equals("a")) {
            return movie1;
        }
        return movie2;
    }

    private List<Screen> createScreen() {
        Screen screen1 = new Screen();
        screen1.setScreenId(51);
        screen1.setSeats(getSeats());

        Screen screen2 = new Screen();
        screen2.setScreenId(52);
        screen2.setSeats(getSeats());

        return List.of(screen1, screen2);
    }

    private List<Seat> getSeats() {
        List<Seat> allSeat = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setBooked(Boolean.FALSE);
            if (i <= 40) {
                seat.setSeatCategory(SeatCategory.SILVER);
            } else if (i <= 70) {
                seat.setSeatCategory(SeatCategory.GOLD);
            } else {
                seat.setSeatCategory(SeatCategory.PLATINUM);
            }
            allSeat.add(seat);
        }
        return allSeat;
    }

    public Booking bookTicket(String cityName, String movieName, String time, int seatId, User1 user) {
         return movieBookingSystemProcessor.bookTicket(cityName,movieName,time,seatId,user);
    }
}

class BookingShow{
    public static void main(String[] args) {
        MovieBookingSystem movieBookingSystem=new MovieBookingSystem();
        //movieBookingSystem.initialize();
        User1 user = new User1("1", "John Doe");

        // Booking a ticket
        try {
            Booking booking = movieBookingSystem.bookTicket("Hyderabad", "BAHUBALI","8:00 AM" ,1, user);
            System.out.println("Ticket booked: " + booking.bookingId());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Booking already booked ticket
        try {
            Booking booking = movieBookingSystem.bookTicket("Hyderabad", "BAHUBALI","8:00 AM" ,1, user);
            System.out.println("Ticket booked: " + booking.bookingId());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
