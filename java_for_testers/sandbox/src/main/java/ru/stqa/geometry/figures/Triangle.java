package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle (double a, double b, double c) {

    public Triangle{
        if (a < 0 || b < 0 || c < 0){
            throw new IllegalArgumentException("Triangle side should be non-negative!");
        }
        if ((a + b) < c || (a + c) < b || (b + c) < a){
            throw new IllegalArgumentException("Incorrect triangle sides");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.c) == 0)
                || (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.a) == 0)
                || (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.b) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public double area() {
        double half_perimeter = perimeter() / 2;
        double area = Math.sqrt(half_perimeter *
                (half_perimeter - this.a) *
                (half_perimeter - this.b) *
                (half_perimeter - this.c));
        return area;
    }

    public double perimeter() {
        double perimeter = this.a + this.b + this.c;
        return perimeter;
    }
}
