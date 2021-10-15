package ru.job4j.cars.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Init {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            User user1 = User.of("email_1@mail.ru", "user 1", "pass 1");
            User user2 = User.of("email_2@mail.ru", "user 2", "pass 2");

            session.save(user1);
            session.save(user2);

            Post post1 = Post.of("carBrand 1", "bodyType 1", "desc 1", user1);
            Post post2 = Post.of("carBrand 2", "bodyType 2", "desc 2", user1);

            Post post3 = Post.of("carBrand 3", "bodyType 3", "desc 3", user2);
            Post post4 = Post.of("carBrand 4", "bodyType 4", "desc 4", user2);
            Post post5 = Post.of("carBrand 5", "bodyType 5", "desc 5", user2);

            session.save(post1);
            session.save(post2);
            session.save(post3);
            session.save(post4);
            session.save(post5);

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
