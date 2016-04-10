package dao;

import models.Actor;
import models.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by chenggu on 4/4/16.
 */
public class projectDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalProject");
    EntityManager em = emf.createEntityManager();

    // crud
    // create MovieRest
    public Movie createMovie(Movie movies) {
        em.getTransaction().begin();
        em.persist(movies);
        em.getTransaction().commit();
        return movies; // movies here is a managed object
    }
    // readMovieById
    public Movie readMovieById(Integer id) {
        return em.find(Movie.class, id);
    }

    // readAllMovies
    public List<Movie> readAllMovies() {
        // JPQL
        Query query = em.createQuery("select movie from Movie movie");
        return (List<Movie>) query.getResultList();
    }
    // updateMovie
    public Movie updateMovie(Movie movie) {
        em.getTransaction().begin();
        em.merge(movie);
        em.getTransaction().commit();
        return movie;
    }
    // deleteMovie
    public void deleteMovie(int id) {
        em.getTransaction().begin();
        Movie movie = em.find(Movie.class, id);
        em.remove(movie);
        em.getTransaction().commit();
    }

    public Actor createActor(Actor actor) {
        em.getTransaction().begin();
        em.persist(actor);
        em.getTransaction().commit();
        return actor;
    }

    // add an actor
    public Actor addActor(int movieId, Actor actor) {
        em.getTransaction().begin();
        Movie movie = em.getReference(Movie.class, movieId);
        actor.setMovie(movie);
        em.persist(actor);
        em.getTransaction().commit();
        return actor;
    }

    public static void main(String[] args) {
        projectDAO dao = new projectDAO();
//        MovieRest movie = new MovieRest("test", 1997, null, "USA", 7.7);
//        movie = dao.createMovie(movie);
//        System.out.println(movie.getName());

//        MovieRest dianying = dao.readMovieById(3);
//        System.out.println(dianying.getName());

//        dao.deleteMovie(1);

//        List<MovieRest> movies = dao.readAllMovies();
//        for(MovieRest movie: movies) {
//            System.out.println(movie.getName());
//        }

//        dianying.setName("Avater");
//        dao.updateMovie(dianying);

//        Actor hicks = new Actor("test1", "test1", new Date(), null);
//        dao.addActor(2, hicks);


        Movie aliens = dao.readMovieById(3);
////        System.out.println(alians.getActors().size());
//
        List<Actor> actors = aliens.getActors();
        for(Actor actor : actors) {
            System.out.println(actor.getFirstName());
        }
    }

}