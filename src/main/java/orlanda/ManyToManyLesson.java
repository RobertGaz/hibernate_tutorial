//lesson 6

package orlanda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import orlanda.model.Item;
import orlanda.model.Passport;
import orlanda.model.Person;
import orlanda.model.Song;

public class ManyToManyLesson {

    private static final Configuration configuration;

    static {
        configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Song.class);
    }

    public static void main(String[] args) {
//        save()
//        addSongToPerson();
        deleteListening();
    }

    public static void save() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = new Person("Diana", 24);
            Song song = new Song("Hard Bass");

            person.getListenedSongs().add(song);
//            song.getListeners().add(person); // и без этого сработало

            session.save(person);
            session.save(song);

            session.getTransaction().commit();
        }
    }

    public static void addSongToPerson() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = session.get(Person.class, 1);
            Song song = new Song("no_mat - 1992");

            person.getListenedSongs().add(song);
            song.getListeners().add(person);

            session.save(song);

            session.getTransaction().commit();
        }
    }

    public static void deleteListening() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.getTransaction().begin();

            Person person = session.get(Person.class, 2);
            Song song = person.getListenedSongs().get(0);

            person.getListenedSongs().remove(song);

            session.getTransaction().commit();
        }
    }

}
