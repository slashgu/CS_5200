package dao;

import models.Likes;
import models.Movie;
import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by chenggu on 4/12/16.
 */
public class userDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalProject");
    EntityManager em = emf.createEntityManager();
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalProject");
//    EntityManager em = emf.createEntityManager();

    // crud
    // create User
    public User createUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    // readUserById
    public User readUserById(Integer id) {
        return em.find(User.class, id);
        // TODO: find the likes of the user
    }

    //

    // readAllUsers
    public List<User> readAllUsers() {
        Query query = em.createQuery("select user from User user");
        return (List<User>) query.getResultList();
    }
    // updateUser
    public User updateUser(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        return user;
    }
    // deleteUser
    public void deleteUser(int id) {
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.remove(user);
        em.getTransaction().commit();
    }

    // add a likes
    public Likes createLikes(Likes likes) {
        em.getTransaction().begin();
        em.persist(likes);
        em.getTransaction().commit();
        return likes;
    }


    public static void main(String[] args) {
        userDAO dao = new userDAO();
        projectDAO movieDao = new projectDAO();

//        User Cheng = dao.readUserById(1);
        Movie movie_test = new Movie("hello", null, null, null, null, "world");
        movie_test = movieDao.createMovie(movie_test);
        Likes likes = new Likes(1, movie_test.getId());
        dao.createLikes(likes);

//        List<Movie> movies = Cheng.getLikes();
//        for(Movie movie : movies) {
//            System.out.println(movie.getName());
//        }
    }
}
