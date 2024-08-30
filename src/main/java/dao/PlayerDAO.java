package dao;

import model.Player;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Optional;


public class PlayerDAO {

    public void save(Player player) throws Exception {

        try (Session session = HibernateUtil.getCurrentSession();){

            session.beginTransaction();

            session.persist(player);

            session.getTransaction().commit();

        } catch (Exception e) {
            throw new Exception("Player with the name " + player.getName() + " already exists");
        }
    }

    public Optional<Player> findByName(String name) throws Exception {

        try (Session session = HibernateUtil.getCurrentSession()) {

            session.beginTransaction();

            Player player = session.createQuery("from Player where name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResult();

            session.getTransaction().commit();

            return Optional.ofNullable(player);
        } catch (Exception e) {
            throw new Exception("Something went wrong while finding player " + name);
        }
    }


}
