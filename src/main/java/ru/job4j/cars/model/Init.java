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

            User admin = User.of("root@local", "+79271111111", "Admin", "root");
            User user1 = User.of("user_1@list.ru", "+79272222222", "user 1", "user 1");

            session.save(admin);
            session.save(user1);

            CarBrand carBrand1 = CarBrand.of("Audi");
            CarModel model1 = CarModel.of("Q8", carBrand1);

            CarModel model2 = CarModel.of("TT", carBrand1);
            CarModel model3 = CarModel.of("R8", carBrand1);
            CarModel model4 = CarModel.of("A8", carBrand1);
            session.save(model1);
            session.save(model2);
            session.save(model3);
            session.save(model4);
            carBrand1.addModel(model1);
            carBrand1.addModel(model2);
            carBrand1.addModel(model3);
            carBrand1.addModel(model4);
            session.save(carBrand1);

            CarBrand carBrand2 = CarBrand.of("BMW");
            CarModel model5 = CarModel.of("8 Series G15", carBrand2);
            CarModel model6 = CarModel.of("X5 G05", carBrand2);
            CarModel model7 = CarModel.of("X6 E71", carBrand2);
            CarModel model8 = CarModel.of("7 Series 730i", carBrand2);
            session.save(model5);
            session.save(model6);
            session.save(model7);
            session.save(model8);
            carBrand2.addModel(model5);
            carBrand2.addModel(model6);
            carBrand2.addModel(model7);
            carBrand2.addModel(model8);
            session.save(carBrand2);

            CarBrand carBrand3 = CarBrand.of("Mercedes-Benz");
            CarModel model9 = CarModel.of("S 680 GUARD 4MATIC", carBrand3);
            CarModel model10 = CarModel.of("E 350d 4MATIC SportPlus", carBrand3);
            CarModel model11 = CarModel.of("Mercedes-AMG G 63", carBrand3);
            CarModel model12 = CarModel.of("A 200 Sport", carBrand3);
            session.save(model9);
            session.save(model10);
            session.save(model11);
            session.save(model12);
            carBrand3.addModel(model9);
            carBrand3.addModel(model10);
            carBrand3.addModel(model11);
            carBrand3.addModel(model12);
            session.save(carBrand3);

            CarBrand carBrand4 = CarBrand.of("Renault");
            CarModel model13 = CarModel.of("DUSTER", carBrand4);
            CarModel model14 = CarModel.of("KAPTUR", carBrand4);
            CarModel model15 = CarModel.of("LOGAN", carBrand4);
            CarModel model16 = CarModel.of("SANDERO", carBrand4);
            session.save(model13);
            session.save(model14);
            session.save(model15);
            session.save(model16);
            carBrand4.addModel(model13);
            carBrand4.addModel(model14);
            carBrand4.addModel(model15);
            carBrand4.addModel(model16);
            session.save(carBrand4);

            BodyType bodyType1 = BodyType.of("Седан");
            BodyType bodyType2 = BodyType.of("Универсал");
            BodyType bodyType3 = BodyType.of("Хэтчбэк");
            BodyType bodyType4 = BodyType.of("Купе");
            BodyType bodyType5 = BodyType.of("Минивэн");
            BodyType bodyType6 = BodyType.of("Лифтбэк");

            session.save(bodyType1);
            session.save(bodyType2);
            session.save(bodyType3);
            session.save(bodyType4);
            session.save(bodyType5);
            session.save(bodyType6);

            Post post1 = Post.of(carBrand1, model2, bodyType1, "Белый",
                    70000, 4, "1.jpg", "Description 1", admin);
            Post post2 = Post.of(carBrand2, model8, bodyType2, "Синий",
                    50000, 3, "noPhoto.jpg", "Description 2", user1);
            Post post3 = Post.of(carBrand3, model11, bodyType4, "Черный",
                    90000, 6, "noPhoto.jpg", "Description 3", user1);
            post3.setSold(true);
            Post post4 = Post.of(carBrand4, model15, bodyType1, "Синий",
                    140000, 6, "4.jpg", "Description 4", admin);
            Post post5 = Post.of(carBrand4, model13, bodyType3, "Красный",
                    40000, 2, "noPhoto.jpg", "Description 5", user1);

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
