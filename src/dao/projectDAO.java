package dao;

import models.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by chenggu on 4/4/16.
 */
public class projectDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalProject");
    EntityManager em = emf.createEntityManager();

    // crud
    // create Movie
    private Movie createMovie(Movie movies) {
        em.getTransaction().begin();
        em.persist(movies);
        em.getTransaction().commit();
        return movies; // movies here is a managed object
    }
    // readMovieById
    private Movie readMovieById(Integer id) {
        return em.find(Movie.class, id);
    }
    // readAllMovies
    private List<Movie> readAllMovies() {
        // JPQL
        Query query = em.createQuery("select movie from Movie movie");
        return (List<Movie>) query.getResultList();
    }
    // updateMovie
    private Movie updateMovie(Movie movies) {
        em.getTransaction().begin();
        em.merge(movies);
        em.getTransaction().commit();
        return movies;
    }
    // deleteMovie
    private void deleteMovie(int id) {
        em.getTransaction().begin();
        Movie movies = em.find(Movie.class, id);
        em.remove(movies);
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        projectDAO dao = new projectDAO();
        Movie movie = new Movie("test3", 1982, "Love", "RUS", 2.4);
        movie = dao.createMovie(movie);
        System.out.println(movie.getName());

//        Movie dianying = dao.readMovieById(3);
//        System.out.println(dianying.getName());

//        dao.deleteMovie(1);

//        List<Movie> movies = dao.readAllMovies();
//        for(Movie movie: movies) {
//            System.out.println(movie.getName());
//        }

//        dianying.setName("Avater");
//        dao.updateMovie(dianying);
    }

}