package dao;

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
    }

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

//    public static void main(String[] args) {
//        userDAO dao = new userDAO();
////        List<User> users = dao.readAllUsers();
////        for(User user : users) {
////            System.out.println(user.getFirstName());
////        }
//        User user = dao.readUserById(1);
//        System.out.println(user.getFirstName());
//    }
}
