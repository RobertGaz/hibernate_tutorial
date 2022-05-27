//lesson 1

package orlanda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import orlanda.model.Person;


public class SessionQueriesLesson {

    private static final Configuration configuration;

    static {
        configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class);
    }

    public static void main( String[] args ) {
//        get();
//        save();
//        update();
        delete();
    }

    public static void get() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            Person person = session.get(Person.class, 1);
            System.out.println(person);
            session.getTransaction().commit();
        }
    }

    public static void save() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            Person person = new Person("SARS", 34);
            session.save(person);
            session.getTransaction().commit();

            System.out.println("saved with id: " + person.getId());
        }
    }

    public static void update() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            Person person = session.get(Person.class, 1);
            person.setAge(99);
            session.getTransaction().commit();
        }
    }

    public static void delete() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            Person person = session.get(Person.class, 3);
            session.delete(person);
            session.getTransaction().commit();
        }
    }
}
