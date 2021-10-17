package ru.job4j.cars.filter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepository implements AutoCloseable {

    private static final class Lazy {
        private static final AdRepository INST = new AdRepository();
    }

    public static AdRepository instOf() {
        return AdRepository.Lazy.INST;
    }

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public Post addPost(Post post) {
        this.tx(
                session -> session.save(post)
        );
        return post;
    }

    public User addUser(User user) {
        this.tx(
                session -> session.save(user)
        );
        return user;
    }

    public List<Post> findLastDayPosts() {
        return this.tx(
                session -> session.createQuery(
                        "select distinct p from Post p join fetch p.author "
                                + "where p.created between current_date - 1 and current_date ").list()
        );
    }

    public List<Post> findPostsWithPhoto() {
        return this.tx(
                session -> session.createQuery(
                        "select distinct p from Post p join fetch p.author "
                                + "where p.photo != 'noPhoto.jpg' ").list()
        );
    }

    public List<Post> findPostsByCarBrand(String carBrand) {
        return this.tx(
                session -> {
                    String hql = "from Post where carBrand = :carBrand";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("carBrand", carBrand);
                    return hqlQuery.list();
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

    public static void main(String[] args) {
        AdRepository adRepo = AdRepository.instOf();

        User user1 = User.of("email_1@mail.ru", "user 1", "pass 1");

        Post post1 = Post.of("carBrand 1", "bodyType 1", "desc 1", user1);
        post1.setCreated(new Date(System.currentTimeMillis() - 1 * 12 * 60 * 60 * 1000));

        Post post2 = Post.of("carBrand 2", "bodyType 2", "desc 2", user1);
        post2.setCreated(new Date(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000));
        post2.setPhoto("bmw7.jpg");

        Post post3 = Post.of("carBrand 3", "bodyType 3", "desc 3", user1);
        post3.setCreated(new Date(System.currentTimeMillis() - 1 * 12 * 60 * 60 * 1000));

        Post post4 = Post.of("carBrand 4", "bodyType 4", "desc 4", user1);
        post4.setCreated(new Date(System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000));
        post4.setPhoto("renault.jpg");

        Post post5 = Post.of("carBrand 4", "bodyType 4", "desc 5", user1);
        post5.setCreated(new Date(System.currentTimeMillis() - 4 * 24 * 60 * 60 * 1000));

        adRepo.addUser(user1);
        adRepo.addPost(post1);
        adRepo.addPost(post2);
        adRepo.addPost(post3);
        adRepo.addPost(post4);
        adRepo.addPost(post5);

        System.out.println("Last day posts:");
        for (Post post : adRepo.findLastDayPosts()) {
            System.out.println(post);
        }

        System.out.println("Posts with photos:");
        for (Post post : adRepo.findPostsWithPhoto()) {
            System.out.println(post);
        }

        System.out.println("Posts of 'carBrand 4':");
        for (Post post : adRepo.findPostsByCarBrand("carBrand 4")) {
            System.out.println(post);
        }

    }

}