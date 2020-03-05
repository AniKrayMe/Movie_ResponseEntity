package com.example.demo.moddel;

import java.util.Objects;

public class Movie {

    //region Properties

    private String title;
    private String genre;
    private String country;
    //endregion

    //region Constructor

    public Movie(String title, String genre, String country) {
        this.title = title;
        this.genre = genre;
        this.country = country;
    }

    public Movie() {
    }
    //endregion

    //region Getter and Setter

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //endregion

    //region toString HashCode and equals

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(genre, movie.genre) &&
                Objects.equals(country, movie.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, country);
    }
    //endregion

}
