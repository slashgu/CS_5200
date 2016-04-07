package dao;

import models.Actor;
import models.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by chenggu on 4/7/16.
 */
public class actorDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FinalProject");
    EntityManager em = emf.createEntityManager();

    private Actor addActor(Movie movie, Actor actor) {
        em.getTransaction().begin();
        em.merge(actor);
        em.getTransaction().commit();
        return actor;
    }

//    public static void main(String[] args) {
//        actorDAO dao = new actorDAO();
//        Actor Cheng = new Actor("Cheng", "Gu", new Date(), null);
//        Cheng = dao.addActor(Cheng);
//        System.out.println(Cheng.getFirstName());
//    }
}
