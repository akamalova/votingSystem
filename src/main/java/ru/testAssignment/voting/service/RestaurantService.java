package ru.testAssignment.voting.service;

import org.springframework.stereotype.Service;
import ru.testAssignment.voting.model.Restaurant;
import ru.testAssignment.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;


public interface RestaurantService {

    Restaurant update(Restaurant restaurant, int userId) throws NotFoundException;

    Restaurant create(Restaurant restaurant, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    List<Restaurant> getAll();

    List<Restaurant> getByDate(LocalDate date) ;
}
