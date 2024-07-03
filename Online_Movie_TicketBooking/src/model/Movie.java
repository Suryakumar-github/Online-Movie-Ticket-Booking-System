package model;

public class Movie {
    public enum MovieType {
        action, romance, comedy, horor
    }

    public enum Language {
        tamil,telugu,hindi
    }
    private int movieId;
    private String name;
    private MovieType type; // action, romance, comedy, horror
    private Language language; // tamil, telugu, hindi
    private float duration;

    public Movie(int movieId,String name, MovieType type, Language language,float duration){
        this.movieId = movieId;
        this.name = name;
        this.type = type;
        this.language = language;
        this.duration = duration;
    }

    public Movie(String name, MovieType type, Language language,float duration){
        this.name = name;
        this.type = type;
        this.language = language;
        this.duration = duration;
    }
    public Movie(){

    }
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieType getType() {
        return type;
    }

    public void setType(MovieType type) {
        this.type = type;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", language=" + language +
                ", duration=" + duration +
                '}';
    }
}

