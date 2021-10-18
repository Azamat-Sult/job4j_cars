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

            CarBrand carBrand1 = CarBrand.of("carBrand 1");
            BodyType bodyType1 = BodyType.of("bodyType 1");
            Post post1 = Post.of(carBrand1, bodyType1, "desc 1", user1);
            CarBrand carBrand2 = CarBrand.of("carBrand 2");
            BodyType bodyType2 = BodyType.of("bodyType 2");
            Post post2 = Post.of(carBrand2, bodyType2, "desc 2", user1);

            CarBrand carBrand3 = CarBrand.of("carBrand 3");
            BodyType bodyType3 = BodyType.of("bodyType 3");
            Post post3 = Post.of(carBrand3, bodyType3, "desc 3", user2);
            CarBrand carBrand4 = CarBrand.of("carBrand 4");
            BodyType bodyType4 = BodyType.of("bodyType 4");
            Post post4 = Post.of(carBrand4, bodyType4, "desc 4", user2);
            CarBrand carBrand5 = CarBrand.of("carBrand 5");
            BodyType bodyType5 = BodyType.of("bodyType 5");
            Post post5 = Post.of(carBrand5, bodyType5, "desc 5", user2);

            session.save(carBrand1);
            session.save(carBrand2);
            session.save(carBrand3);
            session.save(carBrand4);
            session.save(carBrand5);

            session.save(bodyType1);
            session.save(bodyType2);
            session.save(bodyType3);
            session.save(bodyType4);
            session.save(bodyType5);

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
