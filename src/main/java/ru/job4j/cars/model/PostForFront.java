package ru.job4j.cars.model;

import java.util.Date;

public class PostForFront {
    private int id;
    private String carBrand;
    private String carModel;
    private String bodyType;
    private String bodyColor;
    private int mileAge;
    private int ageYears;
    private String photo;
    private String description;
    private Date created;
    private String phone;
    private String email;
    private boolean showSoldButton = false;

    public static PostForFront of(Post post, User sessionUser) {
        PostForFront postForFront = new PostForFront();
        postForFront.id = post.getId();
        postForFront.carBrand = post.getCarBrand().getCarBrand();
        postForFront.carModel = post.getCarModel().getCarModel();
        postForFront.bodyType = post.getBodyType().getBodyType();
        postForFront.bodyColor = post.getBodyColor();
        postForFront.mileAge = post.getMileAge();
        postForFront.ageYears = post.getAgeYears();
        postForFront.photo = post.getPhoto();
        postForFront.description = post.getDescription();
        postForFront.created = post.getCreated();
        postForFront.phone = post.getAuthor().getPhone();
        postForFront.email = post.getAuthor().getEmail();
        if (sessionUser != null && sessionUser.getEmail().equals(postForFront.email)) {
            postForFront.showSoldButton = true;
        }
        return postForFront;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
    }

    public int getMileAge() {
        return mileAge;
    }

    public void setMileAge(int mileAge) {
        this.mileAge = mileAge;
    }

    public int getAgeYears() {
        return ageYears;
    }

    public void setAgeYears(int ageYears) {
        this.ageYears = ageYears;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getShowSoldButton() {
        return showSoldButton;
    }

    public void setShowSoldButton(boolean showSoldButton) {
        this.showSoldButton = showSoldButton;
    }

}