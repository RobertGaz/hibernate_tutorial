//lesson 3

package orlanda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import orlanda.model.Item;
import orlanda.model.Person;

import java.util.ArrayList;
import java.util.Collections;

public class OneToManyLesson {

    private static final Configuration configuration;

    static {
        configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
    }

    public static void main(String[] args) {
//        getPersonWithItems();
//        addItem();
//        createPersonAndItem();
//        deleteItemFromPerson();
//        deletePerson();
        changeOwner();

    }

    public static void getPersonWithItems() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = session.get(Person.class, 1);
            System.out.println(person);

            session.getTransaction().commit();
            System.out.println(person.getItems());
        }
    }

    public static void addItem() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = session.get(Person.class, 1);
            Item item = new Item("HIBERNATE ITEM", person);

            session.save(item);

            session.getTransaction().commit();
        }
    }

    public static void createPersonAndItem() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = new Person("Julia", 30);
            Item item = new Item("HIBERNATE ITEM #2", person);

            person.setItems(new ArrayList<>(Collections.singletonList(item))); //хорошая практика

            session.save(person);
            session.save(item);

            session.getTransaction().commit();
        }
    }

    public static void deleteItemFromPerson() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = session.get(Person.class, 5);
            person.getItems().forEach(item -> session.remove(item));

            person.getItems().clear(); //хорошая практика

            session.getTransaction().commit();
        }
    }

    public static void deletePerson() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = session.get(Person.class, 6);
            session.remove(person);

            person.getItems().forEach(item -> item.setOwner(null)); //хорошая практика

            session.getTransaction().commit();
        }
    }

    public static void changeOwner() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Item item = session.get(Item.class, 10);
            Person person = session.get(Person.class, 2);

            item.setOwner(person);

            person.getItems().add(item); //хорошая практика

            session.getTransaction().commit();
        }
    }
}
