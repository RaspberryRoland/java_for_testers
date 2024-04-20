package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTests {

    @Test
    void arrayTests(){
        var array = new String[]{"a", "b", "c"};
        Assertions.assertEquals("a", array[0]);
        Assertions.assertEquals(3, array.length);
    }

    @Test
    void listTests(){
        var list = new ArrayList<String>();
        var list2 = List.of("a", "b");
        Assertions.assertEquals(0, list.size());
        list.add("a");


    }
}
