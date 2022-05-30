// https://habr.com/ru/post/135176/

package orlanda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import orlanda.model.Item;
import orlanda.model.Passport;
import orlanda.model.Person;
import orlanda.model.Song;

public class CacheLesson {
    private static final Configuration configuration;

    static {
        configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Song.class);
    }

    public static void main(String[] args) {
//        firstLevelCache();
        secondLevelCache();
    }

    public static void firstLevelCache() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            // вытащили из БД
            Person person = session.get(Person.class, 1);

            // вытащили из кэша
            person = session.get(Person.class, 1);

            session.getTransaction().commit();

        }
    }

    public static void secondLevelCache() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            // вытащили из БД
            Person person = session.get(Person.class, 1);

            session.getTransaction().commit();



            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            // вытащили из кэша
            person = session.get(Person.class, 1);

            session.getTransaction().commit();

        }
    }
}
