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
}
