package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();

//        for (var name : List.of("", "group name")) {
//            for (var header : List.of("", "group header")) {
//                result.add(new GroupData().withName(name).withHeader(header));
//            }
//        }
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
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>() {} );
        result.addAll(value);
        return result;
    }

    public static Stream<GroupData> randomGroups() {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10));

        return Stream.generate(randomGroup).limit(3);
    }

    @ParameterizedTest
    @MethodSource("randomGroups")
    public void canCreateGroup(GroupData group) {
        var oldGroups = app.jdbc().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.jdbc().getGroupList();

        var extraGroups = newGroups.stream().filter(g -> ! oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();
        
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
//        var newUiGroups = app.groups().getList();

    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(new GroupData("", "group name'", ":", "", "")));
        return result;
    }

//    @ParameterizedTest
//    @MethodSource("negativeGroupProvider")
//    public void cannotCreateGroup(GroupData group) {
//        var oldGroups = app.groups().getList();
//        app.groups().createGroup(group);
//        var newGroups = app.groups().getList();
//        Assertions.assertEquals(newGroups, oldGroups);
//    }
}
