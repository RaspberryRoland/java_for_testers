package tests;

import model.AddressBookData;
import org.junit.jupiter.api.Test;

public class ContactDeleteTests extends TestBase {

    @Test
    void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new AddressBookData());
        }
        app.contacts().removeContact();
    }
}
