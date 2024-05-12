package manager;

import model.AddressBookData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    private void fillContactForm(AddressBookData contact) {
        addFirstName(contact);
        addMiddleName(contact);
        addLastName(contact);
        addNickName(contact);
        addTitle(contact);
        addAddress(contact);
        addHome(contact);
        addMobile(contact);
        addEmail(contact);
        if (contact.photo() != "") {
            attach(By.name("photo"),contact.photo());
        }
    }

    private void editContactForm(AddressBookData contact) {
        addFirstName(contact);
        addLastName(contact);
    }

    private static void addEmail(AddressBookData contact) {
        click(By.name("email"));
        type(By.name("email"), contact.email());
    }

    private static void addMobile(AddressBookData contact) {
        click(By.name("mobile"));
        type(By.name("mobile"), contact.mobile());
    }

    private static void addHome(AddressBookData contact) {
        click(By.name("home"));
        type(By.name("home"), contact.home());
    }

    private static void addAddress(AddressBookData contact) {
        click(By.name("address"));
        type(By.name("address"), contact.address());
    }

    private static void addTitle(AddressBookData contact) {
        click(By.name("title"));
        type(By.name("title"), contact.title());
    }

    private static void addNickName(AddressBookData contact) {
        click(By.name("nickname"));
        type(By.name("nickname"), contact.nickName());
    }

    private static void addLastName(AddressBookData contact) {
        click(By.name("lastname"));
        type(By.name("lastname"), contact.lastName());
    }

    private static void addMiddleName(AddressBookData contact) {
        click(By.name("middlename"));
        type(By.name("middlename"), contact.middleName());
    }

    private static void addFirstName(AddressBookData contact) {
        click(By.name("firstname"));
        type(By.name("firstname"), contact.firstName());
    }

    public void createContact(AddressBookData addressbookdata) {
        openContactPage();
        fillContactForm(addressbookdata);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContact(AddressBookData addressbookdata, GroupData group) {
        openContactPage();
        fillContactForm(addressbookdata);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void openContactPage() {
        if (!manager.isElementPresent(By.name("middlename"))) {
            click(By.linkText("add new"));
        }
    }

    public static void returnToHomePage() {
        if (!manager.isElementPresent(By.linkText("Send e-Mail"))) {
            click(By.linkText("home"));
        }
    }

    private static void submitContactCreation() {
        click(By.name("submit"));
    }


    public void removeContact(AddressBookData contact) {
        returnToHomePage();
        selectContact(contact);
        removeSelectedContacts();
        returnToHomePage();
    }

    private void removeSelectedContacts() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectContact(AddressBookData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public int getCount() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        returnToHomePage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<AddressBookData> getList() {
        var contacts = new ArrayList<AddressBookData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : trs){
            var firstName = tr.findElement(By.xpath(".//td[3]")).getText();
            var lastName = tr.findElement(By.xpath(".//td[2]")).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new AddressBookData().withId(id).withFirstNameAndLastNameOnly(firstName, lastName));
        }
        return contacts;
    }

    public void modifyContact(AddressBookData contact, AddressBookData modifiedContact) {
        returnToHomePage();
        initContactModification(contact);
        editContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void submitContactModification() {
        click(By.xpath("//*[@id='content']//input[@value='Update']"));
    }

    private void initContactModification(AddressBookData contact) {
        click(By.xpath(String.format("//tr/td/a[@href=\"edit.php?id=%s\"]", contact.id())));
    }

    public void addContact(AddressBookData cont, GroupData group) {
        returnToHomePage();
        selectContact(cont);
        selectGroupToAdd(group);
        addContactToSelectedGroup();
        returnToHomePage();
    }

    private void addContactToSelectedGroup() {
        click(By.xpath("//*[@name='add'][@value='Add to']"));
    }

    private void selectGroupToAdd(GroupData group) {
        click(By.xpath("//*[@name='to_group']/option[1]"));
    }

    public void deleteContactFromGroup( AddressBookData contact, GroupData group){
        selectGroupFromAbove(group);
        selectContact(contact);
        removeSelectedContact();
        returnToHomePage();
    }

    private void removeSelectedContact() {
        click(By.xpath("//*[@type='submit'][@name='remove']"));
    }

    private void selectGroupFromAbove(GroupData group) {
        click(By.xpath("//*[@name='group']/option[3]"));
    }

    public String getPhones(AddressBookData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getAddress() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var address = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, address);
        }
        return result;
    }

    public Map<String, String> getEmails() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, emails);
        }
        return result;
    }
}
