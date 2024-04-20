package tests;

import model.AddressBookData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactAddTests extends TestBase {


    public static List<AddressBookData> contactProvider() {
        var result = new ArrayList<AddressBookData>();

        for (var firstName : List.of("TestFirstName123", "")) {
            for (var email : List.of("", "testemail123")) {
                result.add(new AddressBookData(firstName,
                        "", "", "", "", "", "", "",
                        email));
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new AddressBookData(randomString(i * 10), "", "", "", "",
                    "", "", "", randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(AddressBookData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
}
