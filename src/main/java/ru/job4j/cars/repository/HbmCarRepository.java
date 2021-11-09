package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.*;

import java.util.List;
import java.util.function.Function;

public class HbmCarRepository implements CarRepository, AutoCloseable {

    private static final class Lazy {
        private static final HbmCarRepository INST = new HbmCarRepository();
    }

    public static HbmCarRepository instOf() {
        return HbmCarRepository.Lazy.INST;
    }

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Car addCar(Car car) {
        this.tx(
                session -> session.save(car)
        );
        return car;
    }

    @Override
    public List<Car> getAllCars() {
        return this.tx(
                session -> {
                    String hql = "select distinct c from Car c"
                            + " join fetch c.carBrand"
                            + " join fetch c.carModel"
                            + " join fetch c.bodyType";
                    return session.createQuery(hql).list();
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
        CarBrand carBrand1 = HbmStore.instOf().getCarBrandById(1);
        CarBrand carBrand2 = HbmStore.instOf().getCarBrandById(2);
        CarModel carModel1 = HbmStore.instOf().getCarModelById(1);
        CarModel carModel2 = HbmStore.instOf().getCarModelById(5);
        BodyType bodyType1 = HbmStore.instOf().getBodyTypeById(1);
        BodyType bodyType2 = HbmStore.instOf().getBodyTypeById(3);
        Car car1 = Car.of(carBrand1, carModel1, bodyType1, "Белый",
                64852, 3, "1.jpg", "Description 1");
        Car car2 = Car.of(carBrand2, carModel2, bodyType2, "Черный",
                34560, 7, "2.jpg", "Description 2");
        HbmCarRepository.instOf().addCar(car1);
        HbmCarRepository.instOf().addCar(car2);

        List<Car> allCars = HbmCarRepository.instOf().getAllCars();
        for (Car car : allCars) {
            System.out.println(car);
        }
    }*/

}
