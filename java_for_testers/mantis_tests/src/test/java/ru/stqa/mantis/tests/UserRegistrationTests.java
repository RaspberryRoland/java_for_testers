package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.manager.JamesCliHelper;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser(){
        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
//        var email = String.format("%s@localhost", username);
        var email = String.format("%s@localhost", CommonFunctions.randomString(6));
//        app.jamesCli().addUser(email,"password");

        // заполняем форму создания и отправляем (браузер)
        app.sessionHelper().fillRegistrationForm(email, CommonFunctions.randomString(5));

        // ждем почту (MailHelper)
        // извлекаем ссылку из письма

        // проходим по ссылке и завершаем регистрацию пользователя (браузер)
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)

    }
}
