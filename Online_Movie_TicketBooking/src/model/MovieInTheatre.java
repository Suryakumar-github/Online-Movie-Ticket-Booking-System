package model;

public class MovieInTheatre {
    public enum  Status{
        ACTIVE,
        INACTIVE
    }
    private int id;
    private int theatreId;
    private int movieId;
    private int showtimeId;
    private double price;
    private Status status ;

    public MovieInTheatre(int id,int theatreId, int movieId,int showtimeId,double price,Status status){
        this.id = id;
        this.theatreId = theatreId;
        this.movieId = movieId;
        this.showtimeId = showtimeId;
        this.price = price;
        this.status = status;
    }
    public MovieInTheatre(int theatreId, int movieId,int showtimeId,double price,Status status){
        this.theatreId = theatreId;
        this.movieId = movieId;
        this.showtimeId = showtimeId;
        this.price = price;
        this.status = status;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    @Override
    public String toString() {
        return "MovieInTheatre{" +
                "id=" + id +
                ", theatreId=" + theatreId +
                ", movieId=" + movieId +
                ", showtimeId=" + showtimeId +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}

