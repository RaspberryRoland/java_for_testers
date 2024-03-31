package ru.stqa.geometry.figures;

public record Triangle (double a, double b, double c) {

    public Triangle{
        if (a < 0 || b < 0 || c < 0){
            throw new IllegalArgumentException("Triangle side should be non-negative!");
        }
        if (!((a + b) < c || (a + c) < b || (b + c) < a)){
            throw new IllegalArgumentException("Incorrect triangle sides");
        }
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
