package ru.job4j.cars.model;

public class CarBrandForFront {
    private int id;
    private String carBrand;

    public static CarBrandForFront of(CarBrand carBrand) {
        CarBrandForFront carBrandForFront = new CarBrandForFront();
        carBrandForFront.id = carBrand.getId();
        carBrandForFront.carBrand = carBrand.getCarBrand();
        return carBrandForFront;
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

}