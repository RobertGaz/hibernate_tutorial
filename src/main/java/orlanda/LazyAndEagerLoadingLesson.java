//lesson 7

package orlanda;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import orlanda.model.Item;
import orlanda.model.Passport;
import orlanda.model.Person;
import orlanda.model.Song;

public class LazyAndEagerLoadingLesson {
    private static final Configuration configuration;

    static {
        configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Song.class);
    }

    public static void main(String[] args) {
//        getPersonThenItems();
//        getItemThenPerson();
        getItemsInOtherSesion();
    }

    public static void getPersonThenItems() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = session.get(Person.class, 1);
            System.out.println("Получили человека");

            Hibernate.initialize(person.getItems());

            session.getTransaction().commit();

            System.out.println(person.getItems());
        }
    }

    public static void getItemThenPerson() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Item item = session.get(Item.class, 1);
            System.out.println("Получили item");

            System.out.println(item.getOwner());

            session.getTransaction().commit();
        }
    }


    public static void getItemsInOtherSesion() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            System.out.println("--- Сессия 1 ---");
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = session.get(Person.class, 1);
            System.out.println("Получили человека");

            session.getTransaction().commit();


            System.out.println("--- Сессия 2 ---");
            session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            person = (Person) session.merge(person);
            Hibernate.initialize(person.getItems());
            System.out.println("Загрузили его items");

            session.getTransaction().commit();

            System.out.println("--- Вне сессии ---");

            System.out.println(person.getItems());

        }
    }
}
