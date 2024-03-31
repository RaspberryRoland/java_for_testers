package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    void canCalculateArea(){
        var s = new Square(5.0);
        var result = s.area();
        Assertions.assertEquals(25.0, result);
    }
    @Test
    void canCanculatePerimeter(){
        var s = new Square(5.0);
        var result = s.perimeter();
        Assertions.assertEquals(20.0, result);
    }

    @Test
    void cannotCreateSquareWithNegativeSide(){
        try {
            new Square(-5.0);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception){

        }
    }

    @Test
    void testEquality (){
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertEquals(s1,s2);
    }

    @Test
    void testNonEquality (){
        var s1 = new Square(5.0);
        var s2 = new Square(6.0);
        Assertions.assertNotEquals(s1,s2);
    }

    @Test
    void testPass (){
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertTrue(s1.equals(s2));
    }
}
