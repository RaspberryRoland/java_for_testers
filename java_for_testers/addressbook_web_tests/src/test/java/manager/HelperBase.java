package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class HelperBase {
    public static ApplicationManager manager;
    public HelperBase(ApplicationManager manager){

        this.manager = manager;
    }

    public static void clickOnTheElementByName(String ElementName) {
        manager.driver.findElement(By.name(ElementName)).click();
    }

    public static void clickOnTheElementByLinkText(String ElementText) {
        manager.driver.findElement(By.linkText(ElementText)).click();
    }

    static void clickOnTheElementByXpath(String Xpath) {
        manager.driver.findElement(By.xpath(Xpath)).click();
    }

    static void sendStringByName(String name, String send) {
        manager.driver.findElement(By.name(name)).sendKeys(send);
    }

    static void sendGroupNameByName(String name, GroupData data) {
        manager.driver.findElement(By.name(name)).sendKeys(data.name());
    }
    static void sendGroupHeaderByName(String name, GroupData data) {
        manager.driver.findElement(By.name(name)).sendKeys(data.header());
    }
}
