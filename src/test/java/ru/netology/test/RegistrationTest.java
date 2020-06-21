package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.RegistrationDto;
import ru.netology.data.User;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.User.*;

public class RegistrationTest {

    @BeforeEach
    void setUpAll() {
        open("http://0.0.0.0:7777/");
    }

    @Test
    void shouldRegisterActiveUser(){
        RegistrationDto activeUser = activeUser();
        $("[name='login']").setValue(activeUser.getLogin());
        $("[name='password']").setValue(activeUser.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Личный кабинет")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegisterBlockedUser(){
        RegistrationDto blockedUser = blockedUser();
        $("[name='login']").setValue(blockedUser.getLogin());
        $("[name='password']").setValue(blockedUser.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Ошибка!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegisterWithoutLogin(){
        RegistrationDto activeUser = activeUser();
        $("[name='password']").setValue(activeUser.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        SelenideElement login = $("[data-test-id=login]");
        login.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotRegisterWithoutPassword(){
        RegistrationDto activeUser = activeUser();
        $("[name='login']").setValue(activeUser.getLogin());
        $$("button").find(exactText("Продолжить")).click();
        SelenideElement password = $("[data-test-id=password]");
        password.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotRegisterWithInvalidLogin(){
        RegistrationDto invalidLogin = invalidLogin();
        $("[name='login']").setValue(invalidLogin.getLogin());
        $("[name='password']").setValue(invalidLogin.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegisterWithInvalidPassword(){
        RegistrationDto invalidPassword = invalidPassword();
        $("[name='login']").setValue(invalidPassword.getLogin());
        $("[name='password']").setValue(invalidPassword.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegisterWithInvalidLoginBlockedUser(){
        RegistrationDto invalidLoginBlockedUser = invalidLoginBlockedUser();
        $("[name='login']").setValue(invalidLoginBlockedUser().getLogin());
        $("[name='password']").setValue(invalidLoginBlockedUser().getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotRegisterWithInvalidPasswordBlockedUser(){
        RegistrationDto invalidPasswordBlockedUser = invalidPasswordBlockedUser();
        $("[name='login']").setValue(invalidPasswordBlockedUser.getLogin());
        $("[name='password']").setValue(invalidPasswordBlockedUser.getPassword());
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }



}
