package ru.job4j.cars.persistence;

import ru.job4j.cars.model.*;

import java.util.List;

public interface Store {

    List<Post> findAllActivePosts(int carBrandIdFilter, boolean showTodayPosts);

    User findUserByEmail(String email);

    User addUser(User user);

    Post addPost(Post post);

    void saveCarImage(int postId, String imageFileName);

    List<CarBrand> getAllCarBrands();

    CarBrand getCarBrandById(int id);

    CarModel getCarModelById(int id);

    List<BodyType> getBodyTypes();

    BodyType getBodyTypeById(int id);

    void changePostStatus(int id);

}
