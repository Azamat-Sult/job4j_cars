package ru.job4j.cars.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.cars.model.*;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class HbmStore implements Store, AutoCloseable {

    private static final class Lazy {
        private static final HbmStore INST = new HbmStore();
    }

    public static HbmStore instOf() {
        return HbmStore.Lazy.INST;
    }

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public List<Post> findAllActivePosts(int carBrandIdFilter, boolean showTodayPosts) {
        return this.tx(
                session -> {
                    String hql = "select distinct p from Post p"
                            + " join fetch p.carBrand"
                            + " join fetch p.carModel"
                            + " join fetch p.bodyType"
                            + " join fetch p.author"
                            + " where p.sold = false";
                    if (carBrandIdFilter != 0) {
                        hql += " and p.carBrand = :carBrand";
                    }
                    if (showTodayPosts) {
                        hql += " and p.created between :start and :end";
                    }

                    Query hqlQuery = session.createQuery(hql);

                    if (carBrandIdFilter != 0) {
                        hqlQuery.setParameter("carBrand", getCarBrandById(carBrandIdFilter));
                    }
                    if (showTodayPosts) {
                        long currentTimeMillis = System.currentTimeMillis();
                        Date endTime = new Date(currentTimeMillis);
                        Date startTime = new Date(currentTimeMillis - 24 * 60 * 60 * 1000);
                        hqlQuery.setParameter("start", startTime);
                        hqlQuery.setParameter("end", endTime);
                    }
                    return hqlQuery.list();
                });
    }

    @Override
    public User findUserByEmail(String email) {
        return this.tx(
                session -> {
                    String hql = "select distinct u from User u"
                            + " join fetch u.posts"
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
    public Post addPost(Post post) {
        this.tx(
                session -> session.save(post)
        );
        return post;
    }

    @Override
    public void saveCarImage(int postId, String imageFileName) {
        this.tx(
                session -> {
                    String hql = "update Post p set p.photo = :photo where p.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("photo", imageFileName);
                    hqlQuery.setParameter("id", postId);
                    return hqlQuery.executeUpdate();
                });
    }

    @Override
    public List<CarBrand> getAllCarBrands() {
        return this.tx(
                session -> {
                    String hql = "select distinct cb from CarBrand cb"
                            + " join fetch cb.carModels";
                    return session.createQuery(hql).list();
                });
    }

    @Override
    public CarBrand getCarBrandById(int id) {
        return this.tx(
                session -> {
                    String hql = "select distinct cb from CarBrand cb"
                            + " join fetch cb.carModels"
                            + " where cb.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return (CarBrand) hqlQuery.uniqueResult();
                });
    }

    @Override
    public CarModel getCarModelById(int id) {
        return this.tx(
                session -> {
                    String hql = "select distinct cm from CarModel cm"
                            + " join fetch cm.carBrand"
                            + " where cm.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return (CarModel) hqlQuery.uniqueResult();
                });
    }

    @Override
    public List<BodyType> getBodyTypes() {
        return this.tx(
                session -> {
                    String hql = "from BodyType";
                    return session.createQuery(hql).list();
                });
    }

    @Override
    public BodyType getBodyTypeById(int id) {
        return this.tx(
                session -> {
                    String hql = "from BodyType where id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return (BodyType) hqlQuery.uniqueResult();
                });
    }

    @Override
    public void changePostStatus(int id) {
        this.tx(
                session -> {
                    String hql = "update Post p set p.sold = true where p.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return hqlQuery.executeUpdate();
                }
        );
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
}