package ru.job4j.cars.service;

import ru.job4j.cars.model.*;
import ru.job4j.cars.persistence.CarRepository;
import ru.job4j.cars.persistence.PostRepository;
import ru.job4j.cars.persistence.UserRepository;

import java.util.List;

public class CarsService {

    private static final class Lazy {
        private static final CarsService INST = new CarsService();
    }

    public static CarsService instOf() {
        return CarsService.Lazy.INST;
    }

    public List<Post> findAllActivePosts(User sessionUser, int carBrandIdFilter, boolean showTodayPosts) {
        List<Post> postsForFront = PostRepository.instOf().findAllActivePosts(carBrandIdFilter, showTodayPosts);
        for (Post post : postsForFront) {
            if (sessionUser != null && sessionUser.getEmail().equals(post.getAuthor().getEmail())) {
                post.setShowSoldButton(true);
            }
        }
        return postsForFront;
    }

    public User findUserByEmail(String email) {
        return UserRepository.instOf().findUserByEmail(email);
    }

    public User addUser(User user) {
        return UserRepository.instOf().addUser(user);
    }

    public Post addPost(int carBrandId, int carModelId, int bodyTypeId,
                        String bodyColor, int mileAge, int ageYears,
                        String description, User user) {

        CarBrand carBrand = CarRepository.instOf().getCarBrandById(carBrandId);
        CarModel carModel = CarRepository.instOf().getCarModelById(carModelId);
        BodyType bodyType = CarRepository.instOf().getBodyTypeById(bodyTypeId);

        Post newPost = Post.of(carBrand, carModel, bodyType, bodyColor, mileAge,
                ageYears, "noPhoto.jpg", description, user);

        return PostRepository.instOf().addPost(newPost);
    }

    public void saveCarImage(int postId, String imageFileName) {
        CarRepository.instOf().saveCarImage(postId, imageFileName);
    }

    public List<CarBrand> getAllCarBrands() {
        return CarRepository.instOf().getAllCarBrands();
    }

    public List<CarModel> getModelsByBrandId(int id) {
        return CarRepository.instOf().getCarBrandById(id).getCarModels();
    }

    public List<BodyType> getAllBodyTypes() {
        return CarRepository.instOf().getAllBodyTypes();
    }

    public void changePostStatus(int id) {
        PostRepository.instOf().changePostStatus(id);
    }

}