package tests;

import model.AddressBookData;
import org.junit.jupiter.api.Test;

public class ContactAddTests extends TestBase {

    @Test
    void canAddContactWithBaseStrings() {
        app.contacts().createContact(new AddressBookData("TestfirstName123", "", "TestlastName123",
                "", "", "testaddress123", "", "testmobile123", "testemail123"));
    }

    @Test
    void canAddContactWithEmptyStrings() {
        app.contacts().createContact(new AddressBookData());
    }
}
