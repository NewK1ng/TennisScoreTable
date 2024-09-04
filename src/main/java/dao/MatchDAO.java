package dao;

import model.Match;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

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

    public long findNumberOfMatches() throws Exception {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            long numberOfMatches = session.createQuery("select count(id) from Match", Long.class).uniqueResult();

            session.getTransaction().commit();

            return numberOfMatches;
        } catch (Exception e) {
            throw new Exception("Something went wrong when getting matches quantity");
        }
    }

    public long findNumberOfMatchesByPlayerName(String name) throws Exception {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            long numberOfMatches = session.createQuery("select count(id) from Match " +
                            "where player1.name ilike :name or player2.name ilike :name", Long.class)
                    .setParameter("name", "%" + name + "%")
                    .uniqueResult();

            session.getTransaction().commit();

            return numberOfMatches;
        } catch (Exception e) {
            throw new Exception("Something went wrong when getting matches quantity");
        }
    }

    public List<Match> findAllPaginated(int offset, int limit) throws Exception {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            List<Match> matchesList = session.createQuery("select m from Match m" +
                            " join fetch m.player1 " +
                            "join fetch m.player2 " +
                            "join fetch m.winner " +
                            "order by m.id asc", Match.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();

            session.getTransaction().commit();

            return matchesList;
        } catch (Exception e) {
            throw new Exception("Something went wrong when getting matches");
        }
    }

    public List<Match> findByPlayerNamePaginated(int offset, int limit, String name) throws Exception {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();

            List<Match> matchesList = session.createQuery("select m from Match m" +
                            " join fetch m.player1 " +
                            "join fetch m.player2 " +
                            "join fetch m.winner " +
                            "where m.player1.name ilike :name or m.player2.name ilike :name", Match.class)
                    .setParameter("name", "%" + name + "%")
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();

            session.getTransaction().commit();

            return matchesList;
        } catch (Exception e) {
            throw new Exception("Something went wrong when getting matches");
        }
    }
}
