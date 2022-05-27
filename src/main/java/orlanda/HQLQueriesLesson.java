//lesson 2

package orlanda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import orlanda.model.Person;

import java.util.List;

public class HQLQueriesLesson {

    private static final Configuration configuration;

    static {
        configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class);
    }

    public static void main( String[] args ) {
        delete();
    }

    public static void get() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            List<Person> persons = session.createQuery("from Person where age < 100 and name like 'S%'").getResultList();
            persons.forEach(System.out::println);

            // проапдейтит
            persons.get(0).setAge(11);

            session.getTransaction().commit();
        }
    }

    public static void update() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            session.createQuery("update Person set name='David' where id > 3").executeUpdate();

            session.getTransaction().commit();
        }
    }

    public static void delete() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            session.createQuery("delete Person where id > 3").executeUpdate();

            session.getTransaction().commit();
        }
    }
}
