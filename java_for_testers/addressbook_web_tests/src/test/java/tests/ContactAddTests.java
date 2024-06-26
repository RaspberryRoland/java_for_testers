package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import common.CommonFunctions;
import io.qameta.allure.Allure;
import model.AddressBookData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactAddTests extends TestBase {

    @Test
    void canCreateContact(){
        var contact = new AddressBookData()
                .withFirstNameAndLastNameOnly(CommonFunctions.randomString(10), CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));

        Allure.step("Create contact", step -> {
            app.contacts().createContact(contact);
        });
    }

    @Test
    void canCreateContactInGroup(){
        var contact = new AddressBookData()
                .withFirstNameAndLastNameOnly(CommonFunctions.randomString(10), CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));

        Allure.step("Check precondition", step -> {
            if (app.hbm().getGroupCount() == 0){
                app.hbm().createGroup(new GroupData("", "name", "header", "footer", ""));
            }
        });

        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);

        Allure.step("Add contact to group", step -> {
            app.contacts().createContact(contact, group);
        });

        var newRelated = app.hbm().getContactsInGroup(group);

        Allure.step("Compare old and new size of contacts in group", step -> {
            Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
        });
    }

    @Test
    void canAddContactToGroup(){
        if(app.jdbc().getGroupList().size() != 0){
            app.groups().removeAllGroups();
        }
        app.hbm().createGroup(new GroupData("", "name", "header", "footer", ""));
        var group = app.jdbc().getGroupList().get(0);
        if (app.jdbc().getContactList().size() == 0){
            app.contacts().createContact(new AddressBookData().withFirstname("New contact!"));
        }
        var cont = app.jdbc().getContactList().get(0);
        var oldRelated = app.jdbc().getContactsInGroup().size();
        app.contacts().addContact(cont, group);
        var newRelated = app.jdbc().getContactsInGroup().size();
        Assertions.assertEquals(oldRelated + 1, newRelated);
    }

    public static List<AddressBookData> contactProvider() throws IOException {
        var result = new ArrayList<AddressBookData>();

//        var json = "";
//        try (var reader = new FileReader("groups.json");
//        var breader = new BufferedReader(reader)
//        ){
//            var line = breader.readLine();
//            while (line != null){
//                json = json + line;
//                line = breader.readLine();
//            }
//        }
//        var json = Files.readString(Paths.get("groups.json"));
        var mapper = new JsonMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<AddressBookData>>() {} );
        result.addAll(value);

//        for (var firstName : List.of("TestFirstName123", "")) {
//            for (var lastName : List.of("", "testLastName123")) {
//                result.add(new AddressBookData("", firstName,
//                        "", lastName, "", "", "", "", "",
//                        "", ""));
//            }
//        }
////        for (int i = 0; i < 5; i++) {
//            result.add(new AddressBookData("", randomString(i * 10), "", "", "", "",
//                    "", "", "", randomString(i * 10)));
//        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(AddressBookData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();

        Comparator<AddressBookData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withUselessFields("", "", "", "", "", "", ""));
        expectedList.sort(compareById);

        Allure.step("Compare old and new size of contacts in group", step -> {
            Assertions.assertEquals(newContacts, expectedList);
        });
    }
}
