package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;

public class UserRegistrationRestApi extends TestBase{

    @Test
    void canCreateUser(){
        var password = "password";
        var user = CommonFunctions.randomString(5);
        var email = String.format("%s@localhost", user);
        app.jamesApi().addUser(email, password);


        // заполняем форму создания и отправляем (браузер)
//        app.sessionHelper().fillRegistrationForm(email, user);
        app.mantisApi().registerNewUser(user, email);

        //Регистрация нового пользователя (сделать по аналогии для Mantis)
//        app.user().startCreation(user, email);

        // ждем почту (MailHelper)
        app.mail().receive(email, "password", Duration.ofSeconds(20));

        // извлекаем ссылку из письма
        // проходим по ссылке и завершаем регистрацию пользователя (браузер)
        app.sessionHelper().finishRegistration(email, user, password);
//
        app.http().login(user, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

}

