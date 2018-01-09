package ru.testAssignment.voting;

import ru.testAssignment.voting.model.Restaurant;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.testAssignment.voting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int RESTAURANT_ID = START_SEQ + 3;


    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID, "FirstRestaurant", "Swedish");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID + 1, "SecondRestaurant", "Asian");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT_ID + 2, "ThirdRestaurant", "Russian");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT_ID + 3, "FourthRestaurant", "Japanese");
    public static final Restaurant RESTAURANT5 = new Restaurant(RESTAURANT_ID + 4, "FifthRestaurant", "Uzbek");

    public static Restaurant getCreated(){
        return new Restaurant(null, "name", "descr");
    }



    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menu");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menu").isEqualTo(expected);
    }
}