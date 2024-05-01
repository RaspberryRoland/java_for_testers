package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
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
        app.contacts().createContact(contact);
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
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();

        Comparator<AddressBookData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withUselessFields("", "", "", "", "", "", ""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
