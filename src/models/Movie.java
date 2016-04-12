package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by chenggu on 4/4/16.
 */
@Entity
// TODO: public dnum genra
@Access(AccessType.PROPERTY)
public class Movie {
    private int id;
    private String name;
    private Integer year;
    private String genra;
    private String country;
    private Double rating;
    private String comments;
    private List<Actor> actors;

    public Movie() {
    }

    public Movie(String name, Integer year, String genra, String country, Double rating, String comments) {
//        this.id = id;
        this.name = name;
        this.year = year;
        this.genra = genra;
        this.country = country;
        this.rating = rating;
        this.comments = comments;
    }

    @Basic
    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "movie")
    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "genra")
    public String getGenra() {
        return genra;
    }

    public void setGenra(String genra) {
        this.genra = genra;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        MovieRest movies = (MovieRest) o;
//
//        if (id != movies.id) return false;
//        if (name != null ? !name.equals(movies.name) : movies.name != null) return false;
//        if (year != null ? !year.equals(movies.year) : movies.year != null) return false;
//        if (genra != null ? !genra.equals(movies.genra) : movies.genra != null) return false;
//        if (country != null ? !country.equals(movies.country) : movies.country != null) return false;
//        if (rating != null ? !rating.equals(movies.rating) : movies.rating != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (year != null ? year.hashCode() : 0);
//        result = 31 * result + (genra != null ? genra.hashCode() : 0);
//        result = 31 * result + (country != null ? country.hashCode() : 0);
//        result = 31 * result + (rating != null ? rating.hashCode() : 0);
//        return result;
//    }
}
