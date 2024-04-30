package manager;

import model.GroupData;
import org.openqa.selenium.By;

import java.nio.file.Paths;

public class HelperBase {

    public static ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected static void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected static void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected static void attach(By locator, String file) {
        manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }
}
