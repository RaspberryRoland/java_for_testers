package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.manager.JamesCliHelper;

import java.time.Duration;

import static org.openqa.selenium.devtools.v85.debugger.Debugger.pause;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser(){
        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
//        var email = String.format("%s@localhost", username);
        var username = CommonFunctions.randomString(6);
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUser(email,"password");

        // заполняем форму создания и отправляем (браузер)
        app.sessionHelper().fillRegistrationForm(email, username);

        // ждем почту (MailHelper)
        app.mail().receive(email, "password", Duration.ofSeconds(20));

        // извлекаем ссылку из письма
        // проходим по ссылке и завершаем регистрацию пользователя (браузер)
        app.sessionHelper().finishRegistration(email);

        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, "password");
        app.http().isLoggedIn();
    }
}
