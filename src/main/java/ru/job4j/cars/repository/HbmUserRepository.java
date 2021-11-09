package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.function.Function;

public class HbmUserRepository implements UserRepository, AutoCloseable {

    private static final class Lazy {
        private static final HbmUserRepository INST = new HbmUserRepository();
    }

    public static HbmUserRepository instOf() {
        return HbmUserRepository.Lazy.INST;
    }

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public User findUserByEmail(String email) {
        return this.tx(
                session -> {
                    String hql = "select distinct u from User u"
                            + " where u.email = :email";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("email", email);
                    return (User) hqlQuery.uniqueResult();
                });
    }

    @Override
    public User addUser(User user) {
        this.tx(
                session -> session.save(user)
        );
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return this.tx(
                session -> {
                    return session.createQuery("from User").list();
                });
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    /*public static void main(String[] args) {
        User newUser = User.of("testUser@testUser.ru", "123456", "testUser", "testUser");
        HbmUserRepository.instOf().addUser(newUser);

        User foundByEmail = HbmUserRepository.instOf().findUserByEmail("testUser@testUser.ru");
        System.out.println(foundByEmail);

        List<User> allUsers = HbmUserRepository.instOf().getAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }
    }*/

}
