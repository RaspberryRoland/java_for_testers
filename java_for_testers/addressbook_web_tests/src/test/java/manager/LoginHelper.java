package manager;

import org.openqa.selenium.By;

public class LoginHelper {

    void login(String user, String password) {
        HelperBase.sendStringByName("user", user);
        HelperBase.sendStringByName("pass", password);
        HelperBase.clickOnTheElementByXpath("//input[@value=\'Login\']");
    }
}
