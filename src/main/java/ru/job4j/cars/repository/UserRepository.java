package ru.job4j.cars.repository;

import ru.job4j.cars.model.User;

import java.util.List;

public interface UserRepository {

    User findUserByEmail(String email);

    User addUser(User user);

    List<User> getAllUsers();

}
