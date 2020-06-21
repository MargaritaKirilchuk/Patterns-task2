package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static ru.netology.data.AuthTest.requestSpec;


public class User {

    private User (){
    }

    public static void makeRegistration(RegistrationDto user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static RegistrationDto activeUser(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        RegistrationDto user = new RegistrationDto(login, password, "active");
        makeRegistration(user);
        return user;
    }

    public static RegistrationDto blockedUser(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        RegistrationDto user = new RegistrationDto(login, password, "blocked");
        makeRegistration(user);
        return user;
    }

    public static RegistrationDto invalidLogin(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String invalidLogin = faker.name().firstName();
        String password = faker.internet().password();
        String status = "active";
        RegistrationDto user = new RegistrationDto(login, password, status);
        return new RegistrationDto(invalidLogin, password, status);
    }

    public static RegistrationDto invalidPassword(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        String invalidPassword = faker.internet().password();
        String status = "active";
        RegistrationDto user = new RegistrationDto(login,password, status);
        return new RegistrationDto(login, invalidPassword, status);
    }

    public static RegistrationDto invalidLoginBlockedUser(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String invalidLogin = faker.name().firstName();
        String password = faker.internet().password();
        String status = "blocked";
        RegistrationDto user = new RegistrationDto(login, password, status);
        return new RegistrationDto(invalidLogin, password, status);
    }

    public static RegistrationDto invalidPasswordBlockedUser(){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        String invalidPassword = faker.internet().password();
        String status = "blocked";
        RegistrationDto user = new RegistrationDto(login,password, status);
        return new RegistrationDto(login, invalidPassword, status);
    }
}
