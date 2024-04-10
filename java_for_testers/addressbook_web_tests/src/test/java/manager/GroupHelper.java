package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper {

    public void openGroupPage() {
        if (!HelperBase.manager.isElementPresent(By.name("new"))) {
            HelperBase.clickOnTheElementByLinkText("groups");
        }
    }

    public boolean isGroupPresent() {
        openGroupPage();
        return HelperBase.manager.isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData group) {
        openGroupPage();
        HelperBase.clickOnTheElementByName("new");
        HelperBase.clickOnTheElementByName("group_name");
        HelperBase.sendGroupNameByName("group_name", group);
        HelperBase.clickOnTheElementByName("group_header");
        HelperBase.sendGroupHeaderByName("group_header", group);
        HelperBase.clickOnTheElementByName("submit");
        HelperBase.clickOnTheElementByLinkText("groups");
    }

    public void removeGroup() {
        openGroupPage();
        HelperBase.clickOnTheElementByName("selected[]");
        HelperBase.clickOnTheElementByName("delete");
        HelperBase.clickOnTheElementByLinkText("group page");
    }
}
