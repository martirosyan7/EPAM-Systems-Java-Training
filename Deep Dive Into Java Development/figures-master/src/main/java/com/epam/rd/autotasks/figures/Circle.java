package com.epam.rd.autotasks.figures;

import static java.lang.Math.*;

public class Circle extends Figure {

    Point point;
    double r;

    public Circle(Point point, double r) {
        this.point = point;
        this.r = r;
    }

    @Override
    public double area() {
        return PI * pow(r, 2);
    }

    @Override
    public String pointsToString() {
        return String.format("(%.1f,%.1f)", point.getX(), point.getY());
    }

    @Override
    public Point leftmostPoint() {
        return new Point(point.getX() - r, point.getY());
    }

    @Override
    public double distance(Point a, Point b) {
        return 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + pointsToString() + r + "]";
    }
}
