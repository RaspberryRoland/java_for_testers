package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    protected WebDriver driver;
    private String string;
    private Properties properties;

    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private JamesApiHelper jamesApiHelper;
    private DeveloperMailHelper developerMailHelper;
    private UserHelper userHelper;
    private RestApiHelper restApiHelper;
    private MantisApiHelper mantisApiHelper;

    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;

    }

    public WebDriver driver(){
        if(driver == null){
            if (string.equals("Chrome")) {
                driver = new ChromeDriver();
            } else if (string.equals("Firefox")) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", string));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(825, 990));
//            driver.findElement(By.name("user")).click();
        }
        return driver;
    }

    public SessionHelper sessionHelper(){
        if(sessionHelper == null){
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if(httpSessionHelper == null){
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesCliHelper jamesCli() {
        if(jamesCliHelper == null){
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public MailHelper mail() {
        if(mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesApiHelper jamesApi() {
        if(jamesApiHelper == null){
            jamesApiHelper = new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }

    public MantisApiHelper mantisApi() {
        if(mantisApiHelper == null){
            mantisApiHelper = new MantisApiHelper(this);
        }
        return mantisApiHelper;
    }

    public DeveloperMailHelper developerMail() {
        if(developerMailHelper == null){
            developerMailHelper = new DeveloperMailHelper(this);
        }
        return developerMailHelper;
    }

    public UserHelper user(){
        if(userHelper == null){
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver().findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public String property(String name){
        return properties.getProperty(name);
    }

    public RestApiHelper rest() {
        if(restApiHelper == null){
            restApiHelper = new RestApiHelper(this);
        }
        return restApiHelper;
    }
}
