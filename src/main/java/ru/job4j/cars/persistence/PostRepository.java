package ru.job4j.cars.persistence;

import org.hibernate.query.Query;
import ru.job4j.cars.model.Post;

import java.util.Date;
import java.util.List;

public class PostRepository {

    private HbmStore store = HbmStore.instOf();

    private static final class Lazy {
        private static final PostRepository INST = new PostRepository();
    }

    public static PostRepository instOf() {
        return PostRepository.Lazy.INST;
    }

    public Post addPost(Post post) {
        store.tx(
                session -> session.save(post)
        );
        return post;
    }

    public List<Post> findAllActivePosts(int carBrandIdFilter, boolean showTodayPosts) {
        return store.tx(
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
                        hqlQuery.setParameter("carBrand", CarRepository.instOf().getCarBrandById(carBrandIdFilter));
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

    public void changePostStatus(int id) {
        store.tx(
                session -> {
                    String hql = "update Post p set p.sold = true where p.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return hqlQuery.executeUpdate();
                }
        );
    }

}
