package com.epam.rd.autotasks.figures;

import static java.lang.Math.*;

public class Triangle extends Figure {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        double AB = distance(a, b);
        double BC = distance(b, c);
        double AC = distance(a, c);
        double p = (AB + BC + AC) / 2;
        return sqrt(p * (p - AB) * (p - BC) * (p - AC));
    }

    @Override
    public String pointsToString() {
        return String.format("(%.1f,%.1f)(%.1f,%.1f)(%.1f,%.1f)",
                a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY());
    }

    @Override
    public Point leftmostPoint() {
        return this.leftestPoint(a, this.leftestPoint(b, c));
    }

    @Override
    public double distance(Point A, Point B) {
        return sqrt(pow(B.getX() - A.getX() , 2) + pow(B.getY() - A.getY(), 2));
    }

}
