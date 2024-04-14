package manager;

import model.AddressBookData;
import org.openqa.selenium.By;

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
        addContact(contact);
        addHome(contact);
        addMobile(contact);
        addEmail(contact);
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

    private static void addContact(AddressBookData contact) {
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

    private void openContactPage() {
        if (!manager.isElementPresent(By.name("middlename"))) {
            click(By.linkText("add new"));
        }
    }

    private static void returnToHomePage() {
        if (!manager.isElementPresent(By.linkText("Send e-Mail"))) {
            click(By.linkText("home"));
        }
    }

    private static void submitContactCreation() {
        click(By.name("submit"));
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact() {
        returnToHomePage();
        selectContact();
        removeSelectedContact();
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }
}
