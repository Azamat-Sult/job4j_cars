package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {
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
    private boolean sold;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "author_id")
    private User author;
    private boolean showSoldButton = false;

    public static Post of(CarBrand carBrand, CarModel carModel, BodyType bodyType,
                          String bodyColor, int mileAge, int ageYears,
                          String photo, String description, User author) {
        Post post = new Post();
        post.carBrand = carBrand;
        post.carModel = carModel;
        post.bodyType = bodyType;
        post.bodyColor = bodyColor;
        post.mileAge = mileAge;
        post.ageYears = ageYears;
        post.photo = photo;
        post.description = description;
        post.sold = false;
        post.created = new Date(System.currentTimeMillis());
        post.author = author;
        return post;
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

    public boolean getSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean getShowSoldButton() {
        return showSoldButton;
    }

    public void setShowSoldButton(boolean showSoldButton) {
        this.showSoldButton = showSoldButton;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post { " + "id=" + id + ", carBrand=" + carBrand + ", carModel=" + carModel
                + ", bodyType=" + bodyType + ", bodyColor='" + bodyColor
                + "', mileAge=" + mileAge + ", ageYears=" + ageYears + ", photo='" + photo
                + "', description='" + description + "', sold=" + sold
                + ", created=" + created + ", author=" + author + " }";
    }
}