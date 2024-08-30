package dao;

import model.Match;
import org.hibernate.Session;
import util.HibernateUtil;

public class MatchDAO {

    public void save(Match match) throws Exception {

        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            session.persist(match);

            session.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Something went wrong with saving match to the DB");
        }

    }

}
