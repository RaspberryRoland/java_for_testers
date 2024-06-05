package tests;

import io.qameta.allure.Allure;
import model.AddressBookData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactDeleteTests extends TestBase {

    @Test
    void canRemoveContact() {
        Allure.step("Checking precondition", step -> {
            if (app.contacts().getCount() == 0) {
                app.contacts().createContact(new AddressBookData());
            }
        });
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        Allure.step("Remove contact", step -> {
            app.contacts().removeContact(oldContacts.get(index));

        });
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemoveContactFromGroup() {
        if(app.jdbc().getGroupList().size() != 0){
            app.groups().removeAllGroups();
        }
        app.hbm().createGroup(new GroupData("", "name", "header", "footer", ""));
        var group = app.jdbc().getGroupList().get(0);
        if (app.jdbc().getContactList().size() == 0){
            app.contacts().createContact(new AddressBookData().withFirstname("New contact!"));
        }
        var cont = app.jdbc().getContactList().get(0);
        app.contacts().addContact(cont, group);
        var oldRelated = app.jdbc().getContactsInGroup().size();
        app.contacts().deleteContactFromGroup(cont, group);
        var newRelated = app.jdbc().getContactsInGroup().size();
        Assertions.assertEquals(oldRelated - 1, newRelated);
    }

    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new AddressBookData());
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }
}
