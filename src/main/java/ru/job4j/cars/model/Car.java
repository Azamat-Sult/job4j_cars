package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "carbrand_id")
    private CarBrand carBrand;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "carmodel_id")
    private CarModel carModel;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "bodytype_id")
    private BodyType bodyType;
    private String bodyColor;
    private int mileAge;
    private int ageYears;
    private String photo;
    private String description;

    public static Car of(CarBrand carBrand, CarModel carModel, BodyType bodyType,
                         String bodyColor, int mileAge, int ageYears,
                         String photo, String description) {
        Car car = new Car();
        car.carBrand = carBrand;
        car.carModel = carModel;
        car.bodyType = bodyType;
        car.bodyColor = bodyColor;
        car.mileAge = mileAge;
        car.ageYears = ageYears;
        car.photo = photo;
        car.description = description;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car post = (Car) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car { " + "id=" + id + ", carBrand=" + carBrand + ", carModel=" + carModel
                + ", bodyType=" + bodyType + ", bodyColor='" + bodyColor
                + "', mileAge=" + mileAge + ", ageYears=" + ageYears + ", photo='" + photo
                + "', description='" + description +  " }";
    }
}