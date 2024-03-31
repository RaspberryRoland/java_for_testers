package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCanculateArea(){
        //Входные данные для вычисления
        double first_side = 5.0, second_side = 4.0, third_side = 3.0;
        double expectedArea = 6.0;
        //Результат вычислений
        var s = new Triangle(first_side, second_side, third_side);
        var actualArea = s.area();

        Assertions.assertEquals(expectedArea, actualArea);
    }

    @Test
    void canCalculatePerimeter(){
        //Входные данные для вычисления
        double first_side = 5.0, second_side = 4.0, third_side = 3.0;
        double expectedPerimeter = 12;
        //Результат вычислений
        var s = new Triangle(first_side, second_side, third_side);
        var actualPerimeter = s.perimeter();

        Assertions.assertEquals(expectedPerimeter, actualPerimeter);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide(){
        try {
            new Triangle(-5.0, 4.0, 3.0);
            Assertions.fail();
        }
        catch(IllegalArgumentException exception) {

        }
    }
    @Test
    void cannotCreateTriangleWithIncorrectSides(){
        try {
            new Triangle(17.0, 4.0, 1.0);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception){

        }
    }
    @Test
    void testEquality(){
        var t1 = new Triangle(5.0, 4.0, 3.0);
        var t2 = new Triangle(5.0, 4.0, 3.0);
        Assertions.assertEquals(t1, t2);
    }
    @Test
    void testEquality2(){
        var t1 = new Triangle(5.0, 4.0, 3.0);
        var t2 = new Triangle(4.0, 3.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }
    @Test
    void testEquality3(){
        var t1 = new Triangle(5.0, 4.0, 3.0);
        var t2 = new Triangle(3.0, 5.0, 4.0);
        Assertions.assertEquals(t1, t2);
    }
}
