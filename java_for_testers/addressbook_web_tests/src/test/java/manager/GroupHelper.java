package manager;

import model.GroupData;
import org.openqa.selenium.By;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {

        super(manager);
    }

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public void createGroup(GroupData group) {
        openGroupPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPages();
    }

    public void removeGroup(GroupData group) {
        openGroupPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupsPages();
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPages();
    }

    private static void submitGroupCreation() {
        click(By.name("submit"));
    }

    private static void initGroupCreation() {
        click(By.name("new"));
    }

    private static void removeSelectedGroups() {
        click(By.name("delete"));
    }

    private void returnToGroupsPages() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void
    selectGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    public int getCount() {
        openGroupPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        openGroupPage();
        selectAllGroups();
        removeSelectedGroups();
    }

    private static void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes){
            checkbox.click();
        }
    }

    public List<GroupData> getList() {
        openGroupPage();
        var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        for (var span : spans){
            var name = span.getText();
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}
