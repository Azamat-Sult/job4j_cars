package ru.job4j.cars.service;

import ru.job4j.cars.model.*;
import ru.job4j.cars.persistence.HbmStore;

import java.util.ArrayList;
import java.util.List;

public class CarsService {

    private static final class Lazy {
        private static final CarsService INST = new CarsService();
    }

    public static CarsService instOf() {
        return CarsService.Lazy.INST;
    }

    public List<PostForFront> findAllActivePosts(User sessionUser, int carBrandIdFilter, boolean showTodayPosts) {
        List<PostForFront> postsForFront = new ArrayList<>();
        for (Post post : HbmStore.instOf().findAllActivePosts(carBrandIdFilter, showTodayPosts)) {
            postsForFront.add(PostForFront.of(post, sessionUser));
        }
        return postsForFront;
    }

    public User findUserByEmail(String email) {
        return HbmStore.instOf().findUserByEmail(email);
    }

    public User addUser(User user) {
        return HbmStore.instOf().addUser(user);
    }

    public Post addPost(int carBrandId, int carModelId, int bodyTypeId,
                        String bodyColor, int mileAge, int ageYears,
                        String description, User user) {

        CarBrand carBrand = HbmStore.instOf().getCarBrandById(carBrandId);
        CarModel carModel = HbmStore.instOf().getCarModelById(carModelId);
        BodyType bodyType = HbmStore.instOf().getBodyTypeById(bodyTypeId);

        Post newPost = Post.of(carBrand, carModel, bodyType, bodyColor, mileAge,
                ageYears, "noPhoto.jpg", description, user);

        return HbmStore.instOf().addPost(newPost);
    }

    public void saveCarImage(int postId, String imageFileName) {
        HbmStore.instOf().saveCarImage(postId, imageFileName);
    }

    public List<CarBrandForFront> getAllCarBrands() {
        List<CarBrandForFront> carBrandForFront = new ArrayList<>();
        for (CarBrand carBrand : HbmStore.instOf().getAllCarBrands()) {
            carBrandForFront.add(CarBrandForFront.of(carBrand));
        }
        return carBrandForFront;
    }

    public List<CarModelForFront> getModelsByBrandId(int id) {
        List<CarModelForFront> carModelForFronts = new ArrayList<>();
        for (CarModel carModel : HbmStore.instOf().getCarBrandById(id).getCarModels()) {
            carModelForFronts.add(CarModelForFront.of(carModel));
        }
        return carModelForFronts;
    }

    public List<BodyType> getBodyTypes() {
        return HbmStore.instOf().getBodyTypes();
    }

    public void changePostStatus(int id) {
        HbmStore.instOf().changePostStatus(id);
    }

}