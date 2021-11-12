package ru.job4j.cars.persistence;

import org.hibernate.query.Query;
import ru.job4j.cars.model.BodyType;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.model.CarModel;

import java.util.List;

public class CarRepository {

    private HbmStore store = HbmStore.instOf();

    private static final class Lazy {
        private static final CarRepository INST = new CarRepository();
    }

    public static CarRepository instOf() {
        return CarRepository.Lazy.INST;
    }

    public List<CarBrand> getAllCarBrands() {
        return store.tx(
                session -> {
                    String hql = "select distinct cb from CarBrand cb"
                            + " join fetch cb.carModels";
                    return session.createQuery(hql).list();
                });
    }

    public CarBrand getCarBrandById(int id) {
        return store.tx(
                session -> {
                    String hql = "select distinct cb from CarBrand cb"
                            + " join fetch cb.carModels"
                            + " where cb.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return (CarBrand) hqlQuery.uniqueResult();
                });
    }

    public CarModel getCarModelById(int id) {
        return store.tx(
                session -> {
                    String hql = "select distinct cm from CarModel cm"
                            + " join fetch cm.carBrand"
                            + " where cm.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return (CarModel) hqlQuery.uniqueResult();
                });
    }

    public List<BodyType> getAllBodyTypes() {
        return store.tx(
                session -> {
                    String hql = "from BodyType";
                    return session.createQuery(hql).list();
                });
    }

    public BodyType getBodyTypeById(int id) {
        return store.tx(
                session -> {
                    String hql = "from BodyType where id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return (BodyType) hqlQuery.uniqueResult();
                });
    }

    public void saveCarImage(int postId, String imageFileName) {
        store.tx(
                session -> {
                    String hql = "update Post p set p.photo = :photo where p.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("photo", imageFileName);
                    hqlQuery.setParameter("id", postId);
                    return hqlQuery.executeUpdate();
                });
    }

}
