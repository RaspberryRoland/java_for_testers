package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

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
        type(By.name("password"), email);
        click(By.xpath("//*[@type='submit']"));
    }

    public void openRegistrationPage() {
        if (!manager.isElementPresent(By.xpath("//label[@for='username']/following::*[@for='email-field']"))) {
            click(By.linkText("Signup for a new account"));
        }
    }

    public void finishRegistration(String email){

    }
}
