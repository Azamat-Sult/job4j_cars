package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;

import java.util.List;

public interface CarRepository {

    Car addCar(Car car);

    List<Car> getAllCars();

}
