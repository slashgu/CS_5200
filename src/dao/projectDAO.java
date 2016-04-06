package dao;

import models.Movies;

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
    // create Movies
    private Movies createMovie(Movies movies) {
        em.getTransaction().begin();
        em.persist(movies);
        em.getTransaction().commit();
        return movies; // movies here is a managed object
    }
    // readMovieById
    private Movies readMovieById(Integer id) {
        return em.find(Movies.class, id);
    }
    // readAllMovies
    private List<Movies> readAllMovies() {
        // JPQL
        Query query = em.createQuery("select movie from Movies movie");
        return (List<Movies>) query.getResultList();
    }
    // updateMovie
    private Movies updateMovie(Movies movies) {
        em.getTransaction().begin();
        em.merge(movies);
        em.getTransaction().commit();
        return movies;
    }
    // deleteMovie
    private void deleteMovie(int id) {
        em.getTransaction().begin();
        Movies movies = em.find(Movies.class, id);
        em.remove(movies);
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        projectDAO dao = new projectDAO();
        Movies movie = new Movies("test2", 1982, "Love", "RUS", 2.4);
        movie = dao.createMovie(movie);
        System.out.println(movie.getName());

//        Movies dianying = dao.readMovieById(3);
//        System.out.println(dianying.getName());

//        dao.deleteMovie(1);

//        List<Movies> movies = dao.readAllMovies();
//        for(Movies movie: movies) {
//            System.out.println(movie.getName());
//        }

//        dianying.setName("Avater");
//        dao.updateMovie(dianying);
    }

}