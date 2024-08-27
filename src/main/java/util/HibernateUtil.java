package util;

import model.Match;
import model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private final static SessionFactory sessionFactory;

    static {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        config.addAnnotatedClass(Match.class);
        config.addAnnotatedClass(Player.class);

        sessionFactory = config.buildSessionFactory();
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
