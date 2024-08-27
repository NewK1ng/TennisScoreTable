package dao;

import jakarta.transaction.Transactional;
import model.Player;
import org.hibernate.Session;
import util.HibernateUtil;


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
            throw new Exception();
        }

    }






}
