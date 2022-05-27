//lesson 4

package orlanda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import orlanda.model.Item;
import orlanda.model.Person;

public class CascadeLesson {

    private static final Configuration configuration;

    static {
        configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
    }

    public static void main(String[] args) {
        persist();
    }

    public static void persist() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = new Person("Han Solo", 30);
            Item item = new Item("Millenium Falcon", person);

            person.getItems().add(item);

            session.save(person);

            session.getTransaction().commit();
        }
    }
}
