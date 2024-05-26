package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.manager.JamesCliHelper;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;

import static org.openqa.selenium.devtools.v85.debugger.Debugger.pause;


public class UserRegistrationTests extends TestBase{

    DeveloperMailUser user;
    @Test
    void canRegisterUser(){
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());


        // заполняем форму создания и отправляем (браузер)
        app.sessionHelper().fillRegistrationForm(email, user.name());

        // ждем почту (MailHelper)
        app.mail().receive(email, "password", Duration.ofSeconds(20));

        // извлекаем ссылку из письма
        // проходим по ссылке и завершаем регистрацию пользователя (браузер)
        app.sessionHelper().finishRegistration(email);

        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(user.name(), "password");
        app.http().isLoggedIn();
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
