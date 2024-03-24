package ru.stqa.geometry.figures;

public record Triangle (double a, double b, double c) {

    public double area() {
        double half_perimeter = (this.a + this.b + this.c) / 2;
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
