package ru.job4j.cars.persistence;

import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

public class UserRepository {

    private HbmStore store = HbmStore.instOf();

    private static final class Lazy {
        private static final UserRepository INST = new UserRepository();
    }

    public static UserRepository instOf() {
        return UserRepository.Lazy.INST;
    }

    public User addUser(User user) {
        store.tx(
                session -> session.save(user)
        );
        return user;
    }

    public User findUserByEmail(String email) {
        return store.tx(
                session -> {
                    String hql = "select distinct u from User u"
                            + " join fetch u.posts"
                            + " where u.email = :email";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("email", email);
                    return (User) hqlQuery.uniqueResult();
                });
    }


}
