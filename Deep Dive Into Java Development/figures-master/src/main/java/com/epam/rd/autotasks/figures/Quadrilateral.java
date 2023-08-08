package com.epam.rd.autotasks.figures;

public class Quadrilateral extends Figure {

    private Point a;
    private Point b;
    private Point c;
    private Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double area() {
        Triangle triangle1 = new Triangle(a,b,c);
        Triangle triangle2 = new Triangle(a,d,c);

        return triangle1.area() + triangle2.area();
    }

    @Override
    public String pointsToString() {
        return String.format("(%.1f,%.1f)(%.1f,%.1f)(%.1f,%.1f)(%.1f,%.1f)",
                a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY(), d.getX(),d.getY());
    }

    @Override
    public Point leftmostPoint() {
        return this.leftestPoint(this.leftestPoint(a, b), this.leftestPoint(c, d));
    }

    @Override
    public double distance(Point a, Point b) {
        return 0;
    }
}
