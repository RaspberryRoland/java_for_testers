package tests;

import model.AddressBookData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactDeleteTests extends TestBase {

    @Test
    void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new AddressBookData());
        }
        int contactCount = app.contacts().getCount();
        app.contacts().removeContact();
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount - 1, newContactCount);
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
