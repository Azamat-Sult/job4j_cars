package ru.job4j.cars.model;

public class CarModelForFront {
    private int id;
    private String carModel;

    public static CarModelForFront of(CarModel carModel) {
        CarModelForFront carModelForFront = new CarModelForFront();
        carModelForFront.id = carModel.getId();
        carModelForFront.carModel = carModel.getCarModel();
        return carModelForFront;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

}
