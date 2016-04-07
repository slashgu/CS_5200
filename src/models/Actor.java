package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chenggu on 4/6/16.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Actor {
    private int id;
    private String firstName;
    private String lastName;
    private Date dob;
    private Movie movie;

    public Actor() {
    }

    public Actor(String firstName, String lastName, Date dob, Movie movie) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.movie = movie;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "dob")
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (id != actor.id) return false;
        if (firstName != null ? !firstName.equals(actor.firstName) : actor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(actor.lastName) : actor.lastName != null) return false;
        if (dob != null ? !dob.equals(actor.dob) : actor.dob != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        return result;
    }
}
