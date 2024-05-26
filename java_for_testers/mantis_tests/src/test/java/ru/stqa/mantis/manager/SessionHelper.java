package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

import java.time.Duration;
import java.util.regex.Pattern;

public class SessionHelper extends HelperBase{
    public SessionHelper(ApplicationManager manager){
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    public void fillRegistrationForm(String email, String username){
        openRegistrationPage();
        fillUsernameAndEmail(email, username);
        proceedRegistration();
    }

    public void proceedRegistration() {
        click(By.xpath("//*[@href='login_page.php']"));
    }

    public void fillUsernameAndEmail(String email, String username) {
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.xpath("//*[@type='submit']"));
    }

    public void openRegistrationPage() {
        if (!manager.isElementPresent(By.xpath("//label[@for='username']/following::*[@for='email-field']"))) {
            click(By.linkText("Signup for a new account"));
        }
    }

    public void finishRegistration(String email){
        var messages = manager.mail().receive(email, "password", Duration.ofMillis(10000));
        System.out.println("This is messages " + messages);
        var text = messages.get(0).content();
        System.out.println("This is text " + text);
        var pattern = Pattern.compile("http://\\S+");
        var matcher = pattern.matcher(text);
        String url = "";
        if(matcher.find()){
            url = text.substring(matcher.start(), matcher.end());
        }
//        var url = text.substring(matcher.start(), matcher.end());
        manager.driver.navigate().to(url);
    }

}
