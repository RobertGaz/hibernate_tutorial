//lesson 5

package orlanda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import orlanda.model.Item;
import orlanda.model.Passport;
import orlanda.model.Person;

public class OneToOneLesson {

    private static final Configuration configuration;

    static {
        configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Passport.class);
    }

    public static void main(String[] args) {
//        save();
        get();
    }

    public static void save() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = new Person("Obi Wan", 60);
            Passport passport = new Passport(person, 1202);

            person.setPassport(passport); //хорошая практика

            session.save(person);

            session.getTransaction().commit();
        }
    }

    public static void get() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = session.get(Person.class, 1);
            Passport passport = person.getPassport();
            System.out.println(passport);

            session.getTransaction().commit();
        }
    }

}
