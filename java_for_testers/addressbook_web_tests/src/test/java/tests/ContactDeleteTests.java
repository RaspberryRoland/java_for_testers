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
        var groups = app.jdbc().getGroupList();
        var contacts = app.jdbc().getContactList();
        String contactId = null;
        String groupId = null;
        var contactsInGroup = app.jdbc().getContactsInGroup();
        GroupData group = new GroupData();
        AddressBookData cont = new AddressBookData();

        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "name", "header", "footer", ""));
            groups = app.jdbc().getGroupList();
            group = groups.get(0);
        }
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new AddressBookData().withFirstname("New contact!"));
            contacts = app.jdbc().getContactList();
            cont = contacts.get(0);
        }

        if (contactsInGroup.size() == 0){
            group = groups.get(0);
            cont = contacts.get(0);
            app.contacts().addContact(cont, group);
            var oldRelated = app.hbm().getContactsInGroup(group);
            app.contacts().deleteContactFromGroup(cont, group);
            var newRelated = app.hbm().getContactsInGroup(group);
            Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());
        }

        else {
            for (int j = 0; j < contacts.size(); j++) {
                for (int k = 0; k < contactsInGroup.size(); k++) {
                    if (contacts.get(j).id().equals(contactsInGroup.get(k).id())) {
                        contactId = contacts.get(j).id();
                        System.out.println("CONTACT ID " + contacts.get(j).id());
                        System.out.println("CONTACT2 ID " + contactsInGroup.get(k).id());
                        System.out.println("HELP" + contactId);
                        cont = contacts.get(j);

                        groupId = contactsInGroup.get(k).groupId();
                        group = groups.get(k);
                        break;
                    }
                }
            }
            if (contactId == null || groupId == null){
                app.contacts().createContact(new AddressBookData().withFirstname("Alone contact without id"));
                app.groups().createGroup(new GroupData().withName("New contact!"));
                contacts = app.jdbc().getContactList();
                cont = app.hbm().getContactList().get(contacts.size() - 1);

                groups = app.jdbc().getGroupList();
                group = app.hbm().getGroupList().get(groups.size() - 1);
                app.contacts().addContact(cont, group);
            }

            var oldRelated = app.hbm().getContactsInGroup(group);
            app.contacts().deleteContactFromGroup(cont, group);
            var newRelated = app.hbm().getContactsInGroup(group);
            Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());
        }
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
