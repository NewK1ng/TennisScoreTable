package dao;

import jakarta.transaction.Transactional;
import model.Match;
import model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import util.HibernateUtil;

import java.util.Optional;


public class PlayerDAO {

    @Transactional
    public Player create(String name) throws Exception {

        Player player = new Player(name);

        try {

            Session session = HibernateUtil.getCurrentSession();

            session.beginTransaction();

            session.persist(player);

            session.getTransaction().commit();

            return player;
        } catch (Exception e) {
            throw new Exception("Player with the name " + name + " already exists");
        }
    }

    @Transactional
    public Optional<Player> read(String name) throws Exception {

        try {
            Session session = HibernateUtil.getCurrentSession();

            session.beginTransaction();

            Player player = session.createQuery("from Player where name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResult();

            session.getTransaction().commit();

            return Optional.ofNullable(player);
        } catch (Exception e) {
            throw new Exception("Something went wrong while reading player " + name);
        }
    }


}
