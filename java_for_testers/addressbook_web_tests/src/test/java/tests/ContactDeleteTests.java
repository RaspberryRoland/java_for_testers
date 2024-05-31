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
        GroupData group = new GroupData();
        AddressBookData cont = new AddressBookData();

        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "name", "header", "footer"));
        }

        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new AddressBookData().withFirstname("New contact!"));
        }

        for (int j = 0; j < contacts.size(); j++) {
            for (int k = 0; k < groups.size(); k++){
                System.out.println("Grp size: " + groups.size());
                System.out.println("Cnt size: " + contacts.size());
                if (contacts.get(j).groupId() == groups.get(k).id()){
                    contactId = contacts.get(j).id();
                    cont = contacts.get(j);
                    System.out.println("THIS IS CONTACT ID: " + contactId);

                    groupId = groups.get(k).id();
                    group = groups.get(k);
                    System.out.println("THIS IS GROUPID: " + groupId);
                    break;
                }
            }
        }
        if (contactId == null || groupId == null){
            app.contacts().createContact(new AddressBookData().withFirstname("Alone contact without id"));
            app.groups().createGroup(new GroupData().withName("New contact!"));

            cont = app.hbm().getContactList().get(contacts.size() - 1);
            group = app.hbm().getGroupList().get(groups.size() - 1);

            app.contacts().addContact(cont, group);
        }

//        if(groupId == null){
//            app.groups().createGroup(new GroupData().withName("New contact!"));
//            group = app.hbm().getGroupList().get(groups.size() - 1);
//        }

//        app.contacts().addContact(cont, group);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().deleteContactFromGroup(cont, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());






//        var oldContacts = app.hbm().getContactList();
//        var rnd = new Random();
//        var index = rnd.nextInt(oldContacts.size());
//        app.contacts().deleteContactFromGroup(oldContacts.get(index), group);
//        var newContacts = app.hbm().getContactList();
//        var expectedList = new ArrayList<>(oldContacts);
//        expectedList.remove(index);
//        Assertions.assertEquals(newContacts, expectedList);
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
